package edu.utdallas.pages;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

@Controller
public class FileController {

    @ResponseBody
    @RequestMapping(value = "/clip-upload", method = RequestMethod.POST)
    public String handleClipUpload(MultipartHttpServletRequest request) {
        String[] success = {"status","Clip upload successful."};
        String[] fail = {"status","Clip upload failed."};
        String[] taken = {"status","Clip name is taken."};
        Iterator<String> iterator = request.getFileNames();
        MultipartFile multiFile = request.getFile(iterator.next());
        try {
            if(multiFile != null) {
                String userName = "username_test";
                if(!SqlAccess.clipExists(userName, multiFile.getOriginalFilename())) {
                    SqlAccess.uploadClip(userName, multiFile.getOriginalFilename(), multiFile.getBytes());
                } else {
                    return createJson(taken);
                }
            } else {
                return createJson(fail);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return createJson(fail);
        }

        return createJson(success);
    }

    @ResponseBody
    @GetMapping(value = "/retrieve-clip", produces = MediaType.TEXT_PLAIN_VALUE)
    public byte[] retrieveClip() {
        return SqlAccess.retrieveClipData("username_test","testing");
    }

    public String createJson(String[]... pairs)
    {
        JSONObject jsonObject = new JSONObject();
        for(String[] pair : pairs) {
            jsonObject.put(pair[0], pair[1]);
        }
        return jsonObject.toString();
    }

}
