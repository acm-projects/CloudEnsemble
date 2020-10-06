package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.*;
import edu.utdallas.pages.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final IFileService fileService;
    private final IClipsService clipService;

    @Autowired
    public FileController(@Qualifier("ClipsService") IClipsService clipService,
                           @Qualifier("FileService") IFileService fileService) {
        this.clipService = clipService;
        this.fileService = fileService;
    }

    /**
     * Post request to upload a new clip on user's account
     * @param request multi part request that includes file
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/upload/clip", method = RequestMethod.POST)
    public String handleClipUpload(MultipartHttpServletRequest request) {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        HttpSession session = request.getSession();
        try {
            if(multiFile != null) {
                String userName = SpringUtils.getStringAttribute(session, LoginController.USERNAME_ATTRIBUTE);
                if(userName == null || userName.equals("")) {
                    return Status.DENIED.getJson();
                }
                if(!clipService.clipExists(userName, multiFile.getOriginalFilename())) {
                    fileService.uploadClip(userName, multiFile.getOriginalFilename(), multiFile.getBytes());
                } else {
                    return Status.TAKEN.getJson();
                }
            } else {
                return Status.FAIL.getJson();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Post request to upload a new profile picture for user
     * @param request multi part request that includes file
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/upload/pic", method = RequestMethod.POST)
    public String handleProfilePicUpload(MultipartHttpServletRequest request) {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        HttpSession session = request.getSession();
        try {
            if(multiFile != null) {
                String userName = SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE);
                if(userName == null || userName.equals("")) {
                    return Status.DENIED.getJson();
                }
                fileService.uploadPic(userName, multiFile.getBytes());
            } else {
                return Status.FAIL.getJson();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
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
        byte[] data = fileService.retrieveClipData(userName,clipName);
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
        byte[] data = fileService.retrievePicData(SpringUtils.getStringAttribute(session,LoginController.USERNAME_ATTRIBUTE));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("image", "png"));
        if(data != null) {
            header.setContentLength(data.length);
        }
        return new HttpEntity<>(data, header);
    }
}
