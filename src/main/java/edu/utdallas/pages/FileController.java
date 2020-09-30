package edu.utdallas.pages;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@Controller
public class FileController {

    /**
     * Post request to upload a new clip on user's account
     * @param request multi part request that includes file
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/upload/clip", method = RequestMethod.POST)
    public String handleClipUpload(MultipartHttpServletRequest request) {
        String[] success = {"status","success"};
        String[] fail = {"status","fail"};
        String[] taken = {"status","taken"};
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        HttpSession session = request.getSession();
        try {
            if(multiFile != null) {
                String userName = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
                if(!DbUtils.clipExists(userName, multiFile.getOriginalFilename())) {
                    DbUtils.uploadClip(userName, multiFile.getOriginalFilename(), multiFile.getBytes());
                } else {
                    return JsonUtils.createJsonAsString(taken);
                }
            } else {
                return JsonUtils.createJsonAsString(fail);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return JsonUtils.createJsonAsString(fail);
        }

        return JsonUtils.createJsonAsString(success);
    }

    /**
     * Post request to upload a new profile picture for user
     * @param request multi part request that includes file
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/upload/pic", method = RequestMethod.POST)
    public String handleProfilePicUpload(MultipartHttpServletRequest request) {
        String[] success = {"status","success"};
        String[] fail = {"status","failed"};
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        HttpSession session = request.getSession();
        try {
            if(multiFile != null) {
                String userName = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
                DbUtils.uploadPic(userName, multiFile.getBytes());
            } else {
                return JsonUtils.createJsonAsString(fail);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return JsonUtils.createJsonAsString(fail);
        }
        return JsonUtils.createJsonAsString(success);
    }

    /**
     * Retrieve any given clip as a wav
     * @param request request
     * @param userName owner of clip
     * @param clipName name of clip
     * @return clip
     */
    @ResponseBody
    @RequestMapping(value = "/clips/{userName}/{clipName}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public HttpEntity<byte[]> retrieveClip(HttpServletRequest request,
                                           @PathVariable String userName, @PathVariable String clipName) {
        byte[] data = DbUtils.retrieveClipData(userName,clipName);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("audio", "wav"));
        if(data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        header.setContentLength(data.length);
        return new HttpEntity<>(data, header);
    }

    /**
     * Retrieve any given pic as a png
     * @param request request
     * @return profile pic
     */
    @ResponseBody
    @RequestMapping(value = "/profile/pic", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public HttpEntity<byte[]> retrievePic(HttpServletRequest request) {
        HttpSession session = request.getSession();
        byte[] data = DbUtils.retrievePicData(SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("image", "png"));
        if(data != null) {
            header.setContentLength(data.length);
        }
        return new HttpEntity<>(data, header);
    }
}
