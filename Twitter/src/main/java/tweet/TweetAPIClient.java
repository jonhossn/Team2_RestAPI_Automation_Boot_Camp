package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {
    // OAuth
    // https://www.programcreek.com/java-api-examples/?api=com.github.scribejava.core.model.OAuthRequest



// https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview

    // https://api.twitter.com/1.1/statuses/update.json
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    //
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    private final String RETWEET_USER_TWEET_ENDPOINT = "/statuses/retweets.json";
    private final String UPDATE_USER_TWEET_ENDPOINT = "/statuses/update.json";
    private final String FAVORITES_LIST_USER_TWEET_ENDPOINT = "/Favorite/List.json";
    private final String FAVORITES_POST_USER_ENDPOINT = "/favorites/create.json";
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String FAVORITES_DESTROY_POST_USER_ENDPOINT = "/favorites/destroy.json";
    private final String FAVORITES_LIST_USER_ENDPOINT = "/favorites/list.json";
    private final String STATUS_LOOKUP_GET_USER_ENDPOINT = "/statuses/lookup.json";
    private final String GET_RETWEETS_USER_ENDPOINT = "/statuses/retweets.json";
    private final String GET_HOME_TIMELINE_USER_ENDPOINT = "/statuses/home_timeline.json";


    // GET ALL Tweet Information
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    // Create a Tweet from user twitter


    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }

    // Retweet a tweet from users twitter
    public ValidatableResponse createReTweet(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", reTweetId)
                .when().post(this.baseUrl+this.CREATE_RETWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse favoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT)
                .then();
    }

    public ValidatableResponse createTweetWithWrongFavoritesInvalidEndPoint(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT + "1234")
                .then();
    }
    public ValidatableResponse unlikeFavoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_DESTROY_POST_USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse favoriteListTweet(int count, String favoriteList) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("count",count)
                .params("screen_name",favoriteList)
                .when().get(this.baseUrl + this.FAVORITES_LIST_USER_ENDPOINT)
                .then();
    }
    /**
     * create Status LokUp
     * @param tweetId
     * @return
     */
    public ValidatableResponse getStatusLookUp(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT)
                .then();
    }
    /**
     * Get Status Retweets
     * @param tweetId
     * @return
     */
    public ValidatableResponse getRetweets(long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEETS_USER_ENDPOINT )
                .then();
    }
    public ValidatableResponse getHomeTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_USER_ENDPOINT )
                .then();
    }




}
