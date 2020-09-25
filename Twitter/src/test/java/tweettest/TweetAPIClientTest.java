package tweettest;


import com.google.common.base.Verify;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @Test(enabled = false)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="We are learning RestAPI Automation"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
       // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
      response= this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was unsuccessful
       response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    @Test(enabled = false)
    public void testDelete(){
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1305277715365404674l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
//    @Test(enabled = true)
//    public void testRetweet(){
//        String retweet="He had an AK15!";
//        ValidatableResponse response=this.tweetAPIClient.createReTweet(1308916219936935936l);
//// Verify that the tweet was successfully retweeted
//        response.statusCode(200);
//        String actualTweet=response.extract().body().path("text");
//        Assert.assertEquals(retweet,actualTweet);
//    }
    @Test(enabled = false)
    public void FavoritesTweetID(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308874571995664386L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test(enabled = false)
    public void TestCreateTweetWithWrongFavoritesEndPoint(){
        String tweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.createTweetWithWrongFavoritesInvalidEndPoint(1308829567298285568l);
        int actualCode = response.extract().statusCode();
    // String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(404, actualCode);
    }
    /**
     * Create reTweet successfully
     */
    @Test(enabled = true)
    public void testCreateRetweet(){
        String retweet="Aweesomeee! I got $196.46 this week so far just taking some surveys.http://apps.facebook.com/531202076932753";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(324236500424335362l);
    // Verify that the tweet was successfully retweeted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }

    /**
     * Favorites Tweet destroy (unLike)
     */
    @Test(enabled = true)
    public void testUnLikeFavoritesTweet(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308874571995664386L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = true)
    public void testFavoritesListTweet(){
        String tweet="favoriteList";
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweet(9,"favoriteList");
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
//        String actualTweet=response.extract().body().path("text");
//        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = true)
    public void testGetStatusLookUp(){
        String expectedTweet="HELLO hello";
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(1308910218299006976l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(expectedTweet,actualTweet);
    }

    @Test(enabled = true)
    public void testGetRetweets(){
        String tweet="mustang";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1308836276163022851l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = true)
    public void testGetHomeTimeLineTweets(){
        String tweet="timeline";
        ValidatableResponse response=this.tweetAPIClient.getHomeTimeLineTweet();
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


}
