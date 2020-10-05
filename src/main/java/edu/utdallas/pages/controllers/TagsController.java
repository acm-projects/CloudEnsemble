package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.ITagsService;
import edu.utdallas.pages.services.MySqlDataSource;
import edu.utdallas.pages.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TagsController {

    private ITagsService tagsService = new TagsService(new MySqlDataSource());

    @Autowired
    public TagsController(@Qualifier("TagsService") ITagsService tagsService) {
        this.tagsService = tagsService;
    }

    @ResponseBody
    @RequestMapping(value="/clips/{clipKey}/tags", method= RequestMethod.GET)
    public String retrieveClipTags(HttpServletRequest request,
                                     @PathVariable String clipKey) {
        return tagsService.retrieveTags(clipKey);
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/remove", method= RequestMethod.POST)
    public String removeTagFromClip(HttpServletRequest request,
                                            @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        tagsService.removeTag(clipKey, tagId);
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/artist", method= RequestMethod.POST)
    public String addArtistTagToClip(HttpServletRequest request,
                                           @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        tagsService.newTag(tagId, TagType.Artist);
        if(!tagsService.clipHasTag(clipKey, tagId)) {
            tagsService.addTag(clipKey, tagId);
        } else {
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/instrument", method= RequestMethod.POST)
    public String addInstrumentTagToClip(HttpServletRequest request,
                                            @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        tagsService.newTag(tagId, TagType.Instrument);
        if(!tagsService.clipHasTag(clipKey, tagId)) {
            tagsService.addTag(clipKey, tagId);
        } else {
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/genre", method= RequestMethod.POST)
    public String addGenreTagToClip(HttpServletRequest request,
                                      @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        tagsService.newTag(tagId, TagType.Genre);
        if(!tagsService.clipHasTag(clipKey, tagId)) {
            tagsService.addTag(clipKey, tagId);
        } else {
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    @ResponseBody
    @RequestMapping(value="/clips/tags/add/other", method= RequestMethod.POST)
    public String addOtherTagToClip(HttpServletRequest request,
                                                @RequestParam(value="clip_key") String clipKey, @RequestParam(value="tag_id") String tagId) {
        tagsService.newTag(tagId, TagType.Other);
        if(!tagsService.clipHasTag(clipKey, tagId)) {
            tagsService.addTag(clipKey, tagId);
        } else {
            return Status.FAIL.getJson();
        }
        return Status.SUCCESS.getJson();
    }

}
