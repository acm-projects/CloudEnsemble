package edu.utdallas.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ClipsController {

    /**
     * Retrieve user clips as a json
     * @param request request
     * @return json containing clip names and ids
     */
    @ResponseBody
    @RequestMapping(value ="/clips/{userName}", method = RequestMethod.GET)
    public String retrieveClips(HttpServletRequest request, @PathVariable String userName) {
        return DbUtils.retrieveClips(userName);
    }

    /**
     * Delete a clip from the database
     * @param request request
     * @return json determining success or failure
     */
    @ResponseBody
    @RequestMapping(value="/clips/delete", method = RequestMethod.POST)
    public String deleteClip(HttpServletRequest request,
                             @RequestParam(value="clip_name") String clipName) {
        String[] success = {"status","success"};
        String[] fail = {"status","fail"};
        HttpSession session = request.getSession();
        String userName = SpringUtils.getStringAttribute(session, LoginController.USERNAME_ATTRIBUTE);
        if(DbUtils.clipExists(userName,clipName)) {
            DbUtils.deleteClip(userName, clipName);
        } else {
            return JsonUtils.createJson(fail);
        }
        return JsonUtils.createJson(success);
    }
}
