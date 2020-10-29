package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class FollowingController extends HttpController {

    private final IFollowersService followersService;

    @Autowired
    public FollowingController(@Qualifier("FollowersService") IFollowersService followersService) {
        this.followersService = followersService;
    }

    /**
     * Follows a user
     * @param request http request
     * @param following user to follow
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/users/follow", method = RequestMethod.POST, produces = "application/json")
    public String followUser(HttpServletRequest request,
                            @RequestParam(value="user") String following) {
        HttpSession session = request.getSession();
        String follower = getStringAttribute(session, USERNAME_ATTRIBUTE);
        if(!followersService.isFollowing(follower,following)) {
            followersService.followUser(follower,following);
        } else {
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Unfollows a user
     * @param request http request
     * @param following user to unfollow
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/users/unfollow", method= RequestMethod.POST, produces = "application/json")
    public String unFollowUser(HttpServletRequest request,
                               @RequestParam(value="user") String following) {
        HttpSession session = request.getSession();
        String follower = getStringAttribute(session,USERNAME_ATTRIBUTE);
        if(followersService.isFollowing(follower,following)) {
            followersService.unFollowUser(follower,following);
        } else {
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Retrieve following users as a json
     * @param userName name
     * @return json containing followers
     */
    @ResponseBody
    @RequestMapping(value="/users/{userName}/following", method= RequestMethod.GET, produces = "application/json")
    public String retrieveFollowing(@PathVariable String userName) {
        return followersService.retrieveFollowing(userName);
    }

    /**
     * Retrieve following users as a json
     * @param userName name
     * @return json containing followers
     */
    @ResponseBody
    @RequestMapping(value="/users/{userName}/followers", method= RequestMethod.GET, produces = "application/json")
    public String retrieveFollowers(@PathVariable String userName) {
        return followersService.retrieveFollowers(userName);
    }

}
