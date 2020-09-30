package edu.utdallas.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PagesController {

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/upload-clip")
    public String uploadClip() {
        return "upload-clip.html";
    }

    @RequestMapping("/upload-pic")
    public String uploadPic() {
        return "upload-pic.html";
    }

    @RequestMapping("/follow")
    public String follow() {
        return "follow.html";
    }

}
