package edu.utdallas.pages.services;

public interface ISearchService {

    /**
     * Returns a heavy search on the entire database
     * @param query the input query
     * @param page number
     * @return search results as a json
     */
    String getSearchResults(String query, int page);

    /**
     * Get the search results between a range
     * @param query for search
     * @param first number for search
     * @param last number for search
     * @return the json file
     */
    String getSearchResults(String query, int first, int last);

}
