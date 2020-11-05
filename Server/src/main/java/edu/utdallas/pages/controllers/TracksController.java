package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.IAccessService;
import edu.utdallas.pages.services.ITracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TracksController extends HttpController {

    private final ITracksService tracksService;
    private final IAccessService accessService;

    @Autowired
    public TracksController(@Qualifier("TracksService") ITracksService tracksService,
                            @Qualifier("AccessService") IAccessService accessService) {
        this.tracksService = tracksService;
        this.accessService = accessService;
    }

    /**
     * Adds a new track to a user's profile
     * @param request http request
     * @param trackName name of track
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/tracks/add", method= RequestMethod.POST, produces = "application/json")
    public String newTrack(HttpServletRequest request, @RequestParam("track_name") String trackName) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(isInputInValid(trackName)) {
            return Status.INVALID.getJson();
        }
        tracksService.newTrack(userName,trackName);
        return Status.SUCCESS.getJson();
    }

    /**
     * Adds a new sample to a track
     * @param request http request
     * @param trackKey to add to
     * @param clipKey of sample
     * @param time location of sample
     * @param rack rack of sample
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/tracks/samples/add", method= RequestMethod.POST, produces = "application/json")
    public String addSample(HttpServletRequest request,
                            @RequestParam("track_key") String trackKey,
                            @RequestParam("clip_key") String clipKey,
                            @RequestParam("time") String time,
                            @RequestParam("rack") String rack) {
        if(accessService.canEditTrack(trackKey,getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE))) {
            tracksService.addSample(trackKey,clipKey,time,rack);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    /**
     * Delete a sample from a track
     * @param request http request
     * @param trackKey to remove from
     * @param sampleKey to remove
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/tracks/samples/delete", method= RequestMethod.POST, produces = "application/json")
    public String deleteSample(HttpServletRequest request,
                             @RequestParam("track_key") String trackKey,
                             @RequestParam("sample_key") String sampleKey) {
        if(accessService.canEditTrack(trackKey,getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE))) {
            tracksService.removeSample(sampleKey);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    /**
     * Move a sample in a track
     * @param request http request
     * @param trackKey to move on
     * @param sampleKey to move
     * @param time to move to
     * @param rack to move to
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/tracks/samples/move", method= RequestMethod.POST, produces = "application/json")
    public String moveSample(HttpServletRequest request,
                             @RequestParam("track_key") String trackKey,
                             @RequestParam("sample_key") String sampleKey,
                             @RequestParam("time") String time,
                             @RequestParam("rack") String rack) {
        if(accessService.canEditTrack(trackKey,getStringAttribute(request.getSession(),USERNAME_ATTRIBUTE))) {
            tracksService.moveSample(sampleKey,time,rack);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    /**
     * Get all samples for a track
     * @param request http request
     * @param trackKey to get for
     * @return as json
     */
    @ResponseBody
    @RequestMapping(value="/tracks/{trackKey}/samples", method= RequestMethod.GET, produces = "application/json")
    public String retrieveSamples(HttpServletRequest request, @PathVariable String trackKey) {
        if(!accessService.canViewTrack(trackKey,getStringAttribute(request.getSession(),USERNAME_ATTRIBUTE))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return tracksService.retrieveSamples(trackKey);
    }

    /**
     * Sets the visibility for a track
     * @param request http request
     * @param trackKey to set for
     * @param accessLevel to set
     *                    0 - Only creator can see
     *                    1 - Anyone can see
     *                    2 - Anyone can edit
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/tracks/sharing/set-visibility", method = RequestMethod.POST, produces = "application/json")
    public String setVisibility(HttpServletRequest request,
                                      @RequestParam("track_key") String trackKey,
                                      @RequestParam("access_level") int accessLevel) {
        if(accessLevel < 0 || accessLevel > 2) {
            return Status.FAIL.getJson();
        }
        if(accessService.isTrackOwner(trackKey,getStringAttribute(request.getSession(),USERNAME_ATTRIBUTE))) {
            accessService.setAccessTrack(trackKey, Integer.toString(accessLevel));
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    /**
     * Share a track with an accessor
     * @param request http request
     * @param trackKey to share
     * @param accessor id to share with (band or user)
     * @param level of sharing
     *                    1 - Accessor can see
     *                    2 - Accessor can edit
     * @param type of accessor
     *                    0 - user
     *                    1 - band
     * @return as json
     */
    @ResponseBody
    @RequestMapping(value="/tracks/sharing/share", method = RequestMethod.POST, produces = "application/json")
    public String shareTrack(HttpServletRequest request,
                                      @RequestParam("track_key") String trackKey,
                                      @RequestParam("accessor") String accessor,
                                      @RequestParam("access_level") int level,
                                      @RequestParam("accessor_type") int type) {
        if(level < 0 || level > 2) {
            return Status.FAIL.getJson();
        }
        if(type < 0 || type > 1) {
            return Status.FAIL.getJson();
        }
        if(accessService.isTrackOwner(trackKey,getStringAttribute(request.getSession(), UserController.USERNAME_ATTRIBUTE))) {
            accessService.addAccessor(trackKey,Integer.toString(level),accessor,Integer.toString(type));
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    /**
     * Retrieve user tracks as a json
     * @return json containing clip names and ids
     */
    @ResponseBody
    @RequestMapping(value ="/{userName}/tracks", method = RequestMethod.GET, produces = "application/json")
    public String retrieveTracks(@PathVariable String userName) {
        return tracksService.retrieveTracks(userName);
    }

}
