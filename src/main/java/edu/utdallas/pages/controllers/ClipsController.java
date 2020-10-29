package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ClipsController extends HttpController {

    private final IClipsService clipsService;
    private final IFileService fileService;
    private final IAccessService accessService;

    @Autowired
    public ClipsController(@Qualifier("ClipsService") IClipsService clipsService,
                           @Qualifier("FileService") IFileService fileService,
                           @Qualifier("AccessService") IAccessService accessService) {
        this.clipsService = clipsService;
        this.fileService = fileService;
        this.accessService = accessService;
    }

    /**
     * Retrieve user clips as a json
     * @return json containing clip names and ids
     */
    @ResponseBody
    @RequestMapping(value ="/{userName}/clips", method = RequestMethod.GET, produces = "application/json")
    public String retrieveClips(@PathVariable String userName) {
        return clipsService.retrieveClips(userName);
    }

    /**
     * Delete a clip from the database
     * @param request request
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/clips/delete", method = RequestMethod.POST, produces = "application/json")
    public String deleteClip(HttpServletRequest request,
                             @RequestParam(value="clip_key") String clipKey) {
        if(accessService.canDeleteClip(clipKey,
                getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE))) {
            return Status.FAIL.getJson();
        }
        fileService.deleteClip(clipKey);
        return Status.SUCCESS.getJson();
    }
}
