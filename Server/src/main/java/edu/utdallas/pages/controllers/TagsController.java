package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.IAccessService;
import edu.utdallas.pages.services.ITagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TagsController extends HttpController {

    private final ITagsService tagsService;
    private final IAccessService accessService;

    @Autowired
    public TagsController(@Qualifier("TagsService") ITagsService tagsService,
                          @Qualifier("AccessService") IAccessService accessService) {
        this.tagsService = tagsService;
        this.accessService = accessService;
    }

    /**
     * Retrieve all tags for a clip
     * @param clipKey retrieve for
     * @return as json
     */
    @ResponseBody
    @RequestMapping(value="/clips/{clipKey}/tags", method= RequestMethod.GET, produces = "application/json")
    public String retrieveClipTags(@PathVariable String clipKey) {
        return tagsService.retrieveTags(clipKey);
    }

    /**
     * Remove a tag from a clip
     * @param request http request
     * @param clipKey to remove from
     * @param tagId to remove
     * @return status
     */
    @ResponseBody
    @RequestMapping(value="/clips/tags/remove", method= RequestMethod.POST, produces = "application/json")
    public String removeTagFromClip(HttpServletRequest request,
                                    @RequestParam(value="clip_key") String clipKey,
                                    @RequestParam(value="tag_id") String tagId) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(!accessService.canModifyClip(clipKey,userName)) {
            return Status.DENIED.getJson();
        }
        tagsService.removeTag(clipKey, tagId);
        return Status.SUCCESS.getJson();
    }

    /**
     * Add a tag to a clip
     * @param request http request
     * @param clipKey to add to
     * @param tagId name of tag
     * @return status
     */
    @ResponseBody
    @RequestMapping(value="/clips/tags/add", method= RequestMethod.POST, produces = "application/json")
    public String addTagToClip(HttpServletRequest request,
                                     @RequestParam(value="clip_key") String clipKey,
                                     @RequestParam(value="tag_id") String tagId) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(!accessService.canModifyClip(clipKey,userName)) {
            return Status.DENIED.getJson();
        }
        if(isInputInValid(tagId)) {
            return Status.INVALID.getJson();
        }
        if(!tagsService.objectHasTag(clipKey, tagId)) {
            tagsService.addTag(clipKey, tagId);
        } else {
            return Status.TAKEN.getJson();
        }
        return Status.SUCCESS.getJson();
    }

    /**
     * Retrieve all tags for a track
     * @param trackKey retrieve for
     * @return as json
     */
    @ResponseBody
    @RequestMapping(value="/tracks/{trackKey}/tags", method= RequestMethod.GET, produces = "application/json")
    public String retrieveTrackTags(@PathVariable String trackKey) {
        return tagsService.retrieveTags(trackKey);
    }

    /**
     * Remove a tag from a track
     * @param request http request
     * @param trackKey to remove from
     * @param tagId to remove
     * @return status
     */
    @ResponseBody
    @RequestMapping(value="/tracks/tags/remove", method= RequestMethod.POST, produces = "application/json")
    public String removeTagFromTrack(HttpServletRequest request,
                                    @RequestParam(value="track_key") String trackKey,
                                    @RequestParam(value="tag_id") String tagId) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(!accessService.canEditTrack(trackKey,userName)) {
            return Status.DENIED.getJson();
        }
        tagsService.removeTag(trackKey, tagId);
        return Status.SUCCESS.getJson();
    }

    /**
     * Add a tag to a track
     * @param request http request
     * @param trackKey to add to
     * @param tagId name of tag
     * @return status
     */
    @ResponseBody
    @RequestMapping(value="/tracks/tags/add", method= RequestMethod.POST, produces = "application/json")
    public String addTagToTrack(HttpServletRequest request,
                               @RequestParam(value="track_key") String trackKey,
                               @RequestParam(value="tag_id") String tagId) {
        String userName = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(!accessService.canEditTrack(trackKey,userName)) {
            return Status.DENIED.getJson();
        }
        if(isInputInValid(tagId)) {
            return Status.INVALID.getJson();
        }
        if(!tagsService.objectHasTag(trackKey, tagId)) {
            tagsService.addTag(trackKey, tagId);
        } else {
            return Status.TAKEN.getJson();
        }
        return Status.SUCCESS.getJson();
    }
}
