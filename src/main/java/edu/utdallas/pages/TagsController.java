package edu.utdallas.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TagsController {

    @ResponseBody
    @RequestMapping(value="/clips/{clipKey}/tags", method= RequestMethod.GET)
    public static String retrieveClipTags(HttpServletRequest request,
                                     @PathVariable String clipKey) {
        return DbUtils.retrieveTags(clipKey);
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/remove", method= RequestMethod.POST)
    public static String removeTagFromClip(HttpServletRequest request,
                                            @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        String[] success = {"status","success"};
        DbUtils.removeTag(clipKey, tagId);
        return JsonUtils.createJson(success);
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/artist", method= RequestMethod.POST)
    public static String addArtistTagToClip(HttpServletRequest request,
                                           @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        String[] success = {"status","success"};
        String[] fail = {"status","failed"};
        DbUtils.newTag(tagId, TagType.Artist);
        if(!DbUtils.clipHasTag(clipKey, tagId)) {
            DbUtils.addTag(clipKey, tagId);
        } else {
            return JsonUtils.createJson(fail);
        }
        return JsonUtils.createJson(success);
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/instrument", method= RequestMethod.POST)
    public static String addInstrumentTagToClip(HttpServletRequest request,
                                            @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        String[] success = {"status","success"};
        String[] fail = {"status","failed"};
        DbUtils.newTag(tagId, TagType.Instrument);
        if(!DbUtils.clipHasTag(clipKey, tagId)) {
            DbUtils.addTag(clipKey, tagId);
        } else {
            return JsonUtils.createJson(fail);
        }
        return JsonUtils.createJson(success);
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/genre", method= RequestMethod.POST)
    public static String addGenreTagToClip(HttpServletRequest request,
                                      @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        String[] success = {"status","success"};
        String[] fail = {"status","failed"};
        String result = JsonUtils.createJson(success);
        DbUtils.newTag(tagId, TagType.Genre);
        if(!DbUtils.clipHasTag(clipKey, tagId)) {
            DbUtils.addTag(clipKey, tagId);
        } else {
            return JsonUtils.createJson(fail);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/other", method= RequestMethod.POST)
    public static String addOtherTagToClip(HttpServletRequest request,
                                                @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        String[] success = {"status","success"};
        String[] fail = {"status","failed"};
        DbUtils.newTag(tagId, TagType.Other);
        if(!DbUtils.clipHasTag(clipKey, tagId)) {
            DbUtils.addTag(clipKey, tagId);
        } else {
            return JsonUtils.createJson(fail);
        }
        return JsonUtils.createJson(success);
    }

}
