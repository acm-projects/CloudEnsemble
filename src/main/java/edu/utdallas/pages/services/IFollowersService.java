package edu.utdallas.pages.services;

public interface IFollowersService {

    /**
     * Makes a user follow another user
     * @param follower going to follow
     * @param following user to follow
     */
    void followUser(String follower, String following);

    /**
     * Makes a user unfollow another user
     * @param follower going to unfollow
     * @param following user to unfollow
     */
    void unFollowUser(String follower, String following);

    /**
     * Checks if a user if following another
     * @param follower might be follower
     * @param following might be followed
     */
    boolean isFollowing(String follower, String following);

    /**
     * Gets all the followers for a certain member
     * @param member to get for
     * @return followers in a json arr
     */
    String retrieveFollowers(String member);

    /**
     * Gets who the member is following
     * @param member to get for
     * @return following in a json arr
     */
    String retrieveFollowing(String member);

}
