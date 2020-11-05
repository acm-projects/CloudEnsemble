package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class FileController extends HttpController {

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
    @RequestMapping(value = "/upload/clip", method = RequestMethod.POST, produces = "application/json")
    public String handleClipUpload(MultipartHttpServletRequest request, @RequestParam(value="clip_name") String clipName) {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        HttpSession session = request.getSession();
        if(isInputInValid(clipName)) {
            return Status.INVALID.getJson();
        }
        try {
            if(multiFile != null) {
                String userName = getStringAttribute(session, USERNAME_ATTRIBUTE);
                if(userName == null || userName.equals("")) {
                    return Status.DENIED.getJson();
                }
                if(!clipService.clipExists(userName, clipName)) {
                    fileService.uploadClip(userName, clipName, multiFile.getBytes());
                } else {
                    return Status.TAKEN.getJson();
                }
            } else {
                return Status.FAIL.getJson();
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
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
    @RequestMapping(value = "/upload/pic", method = RequestMethod.POST, produces = "application/json")
    public String handleProfilePicUpload(MultipartHttpServletRequest request) {
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        HttpSession session = request.getSession();
        try {
            if(multiFile != null) {
                String userName = getStringAttribute(session,USERNAME_ATTRIBUTE);
                if(userName == null || userName.equals("")) {
                    return Status.DENIED.getJson();
                }
                fileService.uploadPic(userName, multiFile.getBytes());
            } else {
                return Status.FAIL.getJson();
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Retrieve any given clip as a wav
     * @param clipKey to get clip
     * @return clip
     */
    @ResponseBody
    @RequestMapping(value = "/clips/{clipKey}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public HttpEntity<byte[]> retrieveClip(@PathVariable String clipKey) {
        byte[] data = fileService.retrieveClipData(clipKey);
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
     * @return profile pic
     */
    @ResponseBody
    @RequestMapping(value = "/{userName}/profile/pic", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public HttpEntity<byte[]> retrievePic(@PathVariable String userName) {
        byte[] data = fileService.retrievePicData(userName);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("image", "png"));
        if(data == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        header.setContentLength(data.length);
        return new HttpEntity<>(data, header);
    }
}
