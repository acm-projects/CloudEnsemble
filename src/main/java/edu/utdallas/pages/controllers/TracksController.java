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

@Controller
public class TracksController {

    private final ITracksService tracksService;
    private final IAccessService accessService;

    @Autowired
    public TracksController(@Qualifier("TracksService") ITracksService tracksService,
                            @Qualifier("AccessService") IAccessService accessService) {
        this.tracksService = tracksService;
        this.accessService = accessService;
    }

    @ResponseBody
    @RequestMapping(value="/tracks/add", method= RequestMethod.POST)
    public String newTrack(HttpServletRequest request, @RequestParam("track_name") String trackName) {
        String userName = SpringUtils.getStringAttribute(request.getSession(), LoginController.USERNAME_ATTRIBUTE);
        tracksService.newTrack(userName,trackName);
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/samples/add", method= RequestMethod.POST)
    public String addSample(HttpServletRequest request,
                            @RequestParam("track_key") String trackKey,
                            @RequestParam("clip_key") String clipKey,
                            @RequestParam("time") String time,
                            @RequestParam("rack") String rack) {
        if(accessService.canEditTrack(trackKey,SpringUtils.getStringAttribute(request.getSession(),
                LoginController.USERNAME_ATTRIBUTE))) {
            tracksService.addSample(trackKey,clipKey,time,rack);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/samples/delete", method= RequestMethod.POST)
    public String deleteSample(HttpServletRequest request,
                             @RequestParam("track_key") String trackKey,
                             @RequestParam("sample_key") String sampleKey) {
        if(accessService.canEditTrack(trackKey,SpringUtils.getStringAttribute(request.getSession(),
                LoginController.USERNAME_ATTRIBUTE))) {
            tracksService.removeSample(sampleKey);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/samples/move", method= RequestMethod.POST)
    public String moveSample(HttpServletRequest request,
                             @RequestParam("track_key") String trackKey,
                            @RequestParam("sample_key") String sampleKey,
                            @RequestParam("time") String time,
                            @RequestParam("rack") String rack) {
        if(accessService.canEditTrack(trackKey,SpringUtils.getStringAttribute(request.getSession(),
                LoginController.USERNAME_ATTRIBUTE))) {
            tracksService.moveSample(sampleKey,time,rack);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/{trackKey}/samples", method= RequestMethod.GET)
    public String retrieveSamples(HttpServletRequest request, @PathVariable String trackKey) {
        if(!accessService.canViewTrack(trackKey,SpringUtils.getStringAttribute(request.getSession(),
                LoginController.USERNAME_ATTRIBUTE))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return tracksService.retrieveSamples(trackKey);
    }

    @ResponseBody
    @RequestMapping(value="/tracks/sharing/set-visibility", method = RequestMethod.POST)
    public String setVisibility(HttpServletRequest request,
                                      @RequestParam("track_key") String trackKey,
                                      @RequestParam("access_level") String accessLevel) {
        if(accessService.isTrackOwner(trackKey,SpringUtils.getStringAttribute(request.getSession(),
                LoginController.USERNAME_ATTRIBUTE))) {
            accessService.setAccessTrack(trackKey, accessLevel);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/sharing/share", method = RequestMethod.POST)
    public String shareTrack(HttpServletRequest request,
                                      @RequestParam("track_key") String trackKey,
                                      @RequestParam("accessor") String accessor,
                                      @RequestParam("access_level") String level,
                                      @RequestParam("accessor_type") String type) {
        if(accessService.isTrackOwner(trackKey,SpringUtils.getStringAttribute(request.getSession(),
                LoginController.USERNAME_ATTRIBUTE))) {
            accessService.addAccessor(trackKey,level,accessor,type);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

}
