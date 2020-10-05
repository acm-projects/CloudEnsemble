package edu.utdallas.pages.controllers;

import edu.utdallas.pages.models.*;
import edu.utdallas.pages.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ClipsController {

    private final IClipsService clipsService;
    private final IFileService fileService;

    @Autowired
    public ClipsController(@Qualifier("ClipsService") IClipsService clipsService,
                           @Qualifier("FileService") IFileService fileService) {
        this.clipsService = clipsService;
        this.fileService = fileService;
    }

    /**
     * Retrieve user clips as a json
     * @param request request
     * @return json containing clip names and ids
     */
    @ResponseBody
    @RequestMapping(value ="/clips/{userName}", method = RequestMethod.GET)
    public String retrieveClips(HttpServletRequest request, @PathVariable String userName) {
        return clipsService.retrieveClips(userName);
    }

    /**
     * Delete a clip from the database
     * @param request request
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/clips/delete", method = RequestMethod.POST)
    public String deleteClip(HttpServletRequest request,
                             @RequestParam(value="clip_name") String clipName) {
        HttpSession session = request.getSession();
        String userName = SpringUtils.getStringAttribute(session, LoginController.USERNAME_ATTRIBUTE);
        if(clipsService.clipExists(userName,clipName)) {
            fileService.deleteClip(userName, clipName);
        } else {
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }
}
