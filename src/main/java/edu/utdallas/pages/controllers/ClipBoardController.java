package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.IAccessService;
import edu.utdallas.pages.services.IClipBoardService;
import edu.utdallas.pages.services.IClipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ClipBoardController extends HttpController {

    private final IClipBoardService clipBoardService;
    private final IAccessService accessService;
    private final IClipsService clipsService;

    @Autowired
    public ClipBoardController(@Qualifier("ClipBoardService") IClipBoardService cbService,
                               @Qualifier("AccessService") IAccessService accessService,
                               @Qualifier("ClipsService") IClipsService clipsService) {
        this.clipBoardService = cbService;
        this.accessService = accessService;
        this.clipsService = clipsService;
    }

    @ResponseBody
    @RequestMapping(value="/tracks/clipboard/add", method= RequestMethod.POST, produces = "application/json")
    public String addToClipBoard(HttpServletRequest request,
                                 @RequestParam(value="track_key") String trackKey,
                                @RequestParam(value="clip_key") String clipKey) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if (!accessService.canEditTrack(trackKey,userName)) {
            return Status.DENIED.getJson();
        }
        if(clipsService.clipExists(userName,clipKey)) {
            return Status.FAIL.getJson();
        }
        try {
            String uuid = clipBoardService.addToClipBoard(trackKey, clipKey);
            String[][] pairs = {{"uuid",uuid},{"status","success"}};
            return JsonUtils.createJson(pairs);
        } catch(SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
            return Status.FAIL.getJson();
        }
    }

    @ResponseBody
    @RequestMapping(value="/tracks/clipboard/remove",method= RequestMethod.POST, produces = "application/json")
    public String removeFromClipBoard(HttpServletRequest request,
                                 @RequestParam(value="track_key") String trackKey,
                                 @RequestParam(value="element_key") String elementKey) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if (!accessService.canEditTrack(trackKey,userName)) {
            return Status.DENIED.getJson();
        }
        clipBoardService.removeFromClipBoard(elementKey);
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/{trackKey}/clipboard",method= RequestMethod.GET, produces = "application/json")
    public String retrieveClipBoard(HttpServletRequest request, @PathVariable String trackKey) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if (!accessService.canEditTrack(trackKey,userName)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return clipBoardService.getFromClipBoard(trackKey);
    }

    @ResponseBody
    @RequestMapping(value="/tracks/clipboard/move",method= RequestMethod.POST, produces = "application/json")
    public String moveElement(HttpServletRequest request,
                                @RequestParam(value="track_key") String trackKey,
                                @RequestParam(value="element_key") String elementKey,
                                @RequestParam(value="after_key") String afterKey) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if (!accessService.canEditTrack(trackKey,userName)) {
            return Status.DENIED.getJson();
        }
        try {
            clipBoardService.moveClipBoardElement(trackKey,elementKey,afterKey);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

}
