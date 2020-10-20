package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.ITracksService;
import edu.utdallas.pages.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TracksController {

    private final ITracksService tracksService;

    @Autowired
    public TracksController(@Qualifier("TracksService") ITracksService tracksService) {
        this.tracksService = tracksService;
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
        tracksService.addSample(trackKey,clipKey,time,rack);
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/samples/delete", method= RequestMethod.POST)
    public String deleteSample(HttpServletRequest request,
                             @RequestParam("sample_key") String sampleKey) {
        tracksService.removeSample(sampleKey);
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/samples/move", method= RequestMethod.POST)
    public String moveSample(HttpServletRequest request,
                            @RequestParam("sample_key") String sampleKey,
                            @RequestParam("time") String time,
                            @RequestParam("rack") String rack) {
        tracksService.moveSample(sampleKey,time,rack);
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/tracks/{trackName}/samples", method= RequestMethod.GET)
    public String retrieve(HttpServletRequest request, @PathVariable String trackName) {
        return tracksService.retrieveSamples(trackName);
    }


}
