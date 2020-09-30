package edu.utdallas.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FollowingController {

    /**
     * Follows a user
     * @param request http request
     * @param following user to follow
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/users/follow", method= RequestMethod.POST)
    public String followUser(HttpServletRequest request,
                            @RequestParam(value="user") String following) {
        String[] success = {"status","success"};
        String[] fail = {"status","fail"};
        HttpSession session = request.getSession();
        String follower = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
        if(!DbUtils.isFollowing(follower,following)) {
            DbUtils.followUser(follower,following);
        } else {
            return JsonUtils.createJsonAsString(fail);
        }
        return JsonUtils.createJsonAsString(success);
    }

    /**
     * Unfollows a user
     * @param request http request
     * @param following user to unfollow
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/users/unfollow", method= RequestMethod.POST)
    public String unFollowUser(HttpServletRequest request,
                               @RequestParam(value="user") String following) {
        String[] success = {"status","success"};
        String[] fail = {"status","fail"};
        HttpSession session = request.getSession();
        String follower = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
        if(DbUtils.isFollowing(follower,following)) {
            DbUtils.unFollowUser(follower,following);
        } else {
            return JsonUtils.createJsonAsString(fail);
        }
        return JsonUtils.createJsonAsString(success);
    }

    /**
     * Retrieve following users as a json
     * @param request http request
     * @return json containing followers
     */
    @ResponseBody
    @RequestMapping(value="/users/following", method= RequestMethod.GET)
    public String retrieveFollowing(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
        return DbUtils.retrieveFollowing(user);
    }

    /**
     * Retrieve following users as a json
     * @param request http request
     * @return json containing followers
     */
    @ResponseBody
    @RequestMapping(value="/users/followers", method= RequestMethod.GET)
    public String retrieveFollowers(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
        return DbUtils.retrieveFollowers(user);
    }

}
