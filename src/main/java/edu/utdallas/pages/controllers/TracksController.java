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

    @ResponseBody
    @RequestMapping(value="/tracks/add", method= RequestMethod.POST, produces = "application/json")
    public String newTrack(HttpServletRequest request, @RequestParam("track_name") String trackName) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        tracksService.newTrack(userName,trackName);
        return Status.SUCCESS.getJson();
    }

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

    @ResponseBody
    @RequestMapping(value="/tracks/{trackKey}/samples", method= RequestMethod.GET, produces = "application/json")
    public String retrieveSamples(HttpServletResponse response, HttpServletRequest request, @PathVariable String trackKey) {
        if(!accessService.canViewTrack(trackKey,getStringAttribute(request.getSession(),USERNAME_ATTRIBUTE))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return tracksService.retrieveSamples(trackKey);
    }

    @ResponseBody
    @RequestMapping(value="/tracks/sharing/set-visibility", method = RequestMethod.POST, produces = "application/json")
    public String setVisibility(HttpServletRequest request,
                                      @RequestParam("track_key") String trackKey,
                                      @RequestParam("access_level") String accessLevel) {
        if(accessService.isTrackOwner(trackKey,getStringAttribute(request.getSession(),USERNAME_ATTRIBUTE))) {
            accessService.setAccessTrack(trackKey, accessLevel);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/sharing/share", method = RequestMethod.POST, produces = "application/json")
    public String shareTrack(HttpServletRequest request,
                                      @RequestParam("track_key") String trackKey,
                                      @RequestParam("accessor") String accessor,
                                      @RequestParam("access_level") String level,
                                      @RequestParam("accessor_type") String type) {
        if(accessService.isTrackOwner(trackKey,getStringAttribute(request.getSession(),LoginController.USERNAME_ATTRIBUTE))) {
            accessService.addAccessor(trackKey,level,accessor,type);
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
