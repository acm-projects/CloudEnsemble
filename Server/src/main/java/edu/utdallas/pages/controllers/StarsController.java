package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.IClipsService;
import edu.utdallas.pages.services.IStarsService;
import edu.utdallas.pages.services.ITracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StarsController extends HttpController {

    private final IStarsService starsService;
    private final IClipsService clipsService;
    private final ITracksService tracksService;

    @Autowired
    public StarsController (@Qualifier("StarsService") IStarsService starsService,
                            @Qualifier("ClipsService") IClipsService clipsService,
                            @Qualifier("TracksService") ITracksService tracksService) {
        this.starsService = starsService;
        this.clipsService = clipsService;
        this.tracksService = tracksService;
    }

    /**
     * Get all stars a user has given
     * @param userName user
     * @return stars in json
     */
    @ResponseBody
    @RequestMapping(value="/{userName}/stars", method= RequestMethod.GET, produces = "application/json")
    public String retrieveUserStars(@PathVariable String userName) {
        return starsService.retrieveUserStars(userName);
    }

    /**
     * Add a star
     * @param request http request
     * @param objKey to add to
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/stars/add", method= RequestMethod.POST, produces = "application/json")
    public String addStar(HttpServletRequest request, @RequestParam("object_key") String objKey) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(!clipsService.clipKeyExists(objKey) && !tracksService.trackKeyExists(objKey)) {
            return Status.FAIL.getJson();
        }
        if(!starsService.hasStarred(userName,objKey)) {
            starsService.addStar(userName,objKey);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Remove a star
     * @param request http request
     * @param objKey to remove from
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/stars/remove", method= RequestMethod.POST, produces = "application/json")
    public String removeStar(HttpServletRequest request, @RequestParam("object_key") String objKey) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(starsService.hasStarred(userName,objKey)) {
            starsService.removeStar(userName,objKey);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Checks if a track is starred for rendering on browser
     * @param userName of star giver
     * @param objKey that may have star
     * @return true or false wrapped in json
     */
    @ResponseBody
    @RequestMapping(value="/{userName}/has-starred", method= RequestMethod.GET, produces = "application/json")
    public String hasStarred(@RequestParam("user_name") String userName,
                             @RequestParam("object_key") String objKey) {
        String[] pair = {"has_starred","False"};
        pair[1] = Boolean.toString(starsService.hasStarred(userName,objKey));
        return JsonUtils.createJson(pair);
    }


}
