package edu.utdallas.pages.controllers;

import edu.utdallas.pages.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {

    public final ISearchService searchService;

    @Autowired
    public SearchController(@Qualifier("SearchService") ISearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Send a search query to get back the first 5 results for preview
     * @param input query
     * @return searched results as a json
     */
    @ResponseBody
    @RequestMapping(value = "/search/preview", method = RequestMethod.GET, produces = "application/json")
    public String search(@RequestParam("query") String input) {
        return searchService.getSearchResults(input, 0, 5);
    }

    /**
     * Send a search query
     * @param input query
     * @param pageNum the number
     * @return searched results as as json
     */
    @ResponseBody
    @RequestMapping(value = "/search/{pageNum}", method = RequestMethod.GET, produces = "application/json")
    public String search(@RequestParam("query") String input, @PathVariable int pageNum) {
        return searchService.getSearchResults(input, pageNum);
    }

}
