package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.IBandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class BandsController extends HttpController{

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
    @RequestMapping(value ="/band/new", method = RequestMethod.POST, produces = "application/json")
    public String createBand(HttpServletRequest request, @RequestParam(value="band_name") String bandName) {
        HttpSession session = request.getSession();
        String userName = getStringAttribute(session, USERNAME_ATTRIBUTE);
        if(isInputInValid(bandName)) {
            return Status.INVALID.getJson();
        }
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        try {
            bandsService.newBand(userName, bandName);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, null, ex);
            return Status.TAKEN.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Join a band
     * @param request request
     * @param bandId to join
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/join", method = RequestMethod.POST, produces = "application/json")
    public String joinBand(HttpServletRequest request, @RequestParam(value="band_id") String bandId) {
        HttpSession session = request.getSession();
        String userName = getStringAttribute(session, USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(!bandsService.inBand(userName, bandId)) {
            bandsService.joinBand(userName, bandId);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Leave a band
     * @param request request
     * @param bandId to leave
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/leave", method = RequestMethod.POST, produces = "application/json")
    public String leaveBand(HttpServletRequest request, @RequestParam(value="band_id") String bandId) {
        HttpSession session = request.getSession();
        String userName = getStringAttribute(session, USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(bandsService.inBand(userName, bandId) && !bandsService.isFounder(userName,bandId)) {
            bandsService.leaveBand(getStringAttribute(session, USERNAME_ATTRIBUTE), bandId);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Leave a band
     * @param request request
     * @param bandId to leave
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/delete", method = RequestMethod.POST, produces = "application/json")
    public String deleteBand(HttpServletRequest request, @RequestParam(value="band_id") String bandId) {
        HttpSession session = request.getSession();
        String userName = getStringAttribute(session, USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(bandsService.isFounder(userName,bandId)) {
            bandsService.deleteBand(bandId);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Leave a band
     * @param request request
     * @param bandId to leave
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value ="/band/kick", method = RequestMethod.POST, produces = "application/json")
    public String kickMember(HttpServletRequest request,
                             @RequestParam(value="band_id") String bandId,
                             @RequestParam(value="band_id") String member) {
        HttpSession session = request.getSession();
        String userName = getStringAttribute(session, USERNAME_ATTRIBUTE);
        if(userName == null || userName.equals("")) {
            return Status.DENIED.getJson();
        }
        if(bandsService.isFounder(userName,bandId)) {
            bandsService.kickMember(bandId,userName);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }
}
