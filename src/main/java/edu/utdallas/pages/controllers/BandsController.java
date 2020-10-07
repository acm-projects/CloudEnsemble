package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.IBandsService;
import edu.utdallas.pages.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class BandsController {

    private final IBandsService bandsService;

    @Autowired
    public BandsController(@Qualifier("BandsService") IBandsService bandsService) {
        this.bandsService = bandsService;
    }

    /**
     * Create a band
     * @param request request
     * @param bandName to create
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/new", method = RequestMethod.POST)
    public String createBand(HttpServletRequest request, @RequestParam(value="band_name") String bandName) {
        try {
            bandsService.newBand(bandName);
        } catch (SQLException ex) {
            return Status.TAKEN.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Join a band
     * @param request request
     * @param bandName to join
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/join", method = RequestMethod.POST)
    public String joinBand(HttpServletRequest request, @RequestParam(value="band_name") String bandName) {
        HttpSession session = request.getSession();
        String userName = SpringUtils.getStringAttribute(session, LoginController.USERNAME_ATTRIBUTE);
        if(!bandsService.inBand(userName, bandName)) {
            bandsService.joinBand(userName, bandName);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Leave a band
     * @param request request
     * @param bandName to leave
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/leave", method = RequestMethod.POST)
    public String leaveBand(HttpServletRequest request, @RequestParam(value="band_name") String bandName) {
        HttpSession session = request.getSession();
        String userName = SpringUtils.getStringAttribute(session, LoginController.USERNAME_ATTRIBUTE);
        if(bandsService.inBand(userName, bandName)) {
            bandsService.leaveBand(SpringUtils.getStringAttribute(session, LoginController.USERNAME_ATTRIBUTE), bandName);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }
}
