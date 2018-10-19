package com.varad.twitter_mvp_architecture.ui;

import com.varad.twitter_mvp_architecture.data.Tweet;

import java.util.ArrayList;

/**
 * Created by varad on 11/10/18.
 */
public interface TweetPresenter<V extends TweetView> {

    public void onAttach(V tweetView);

    public void onDetach();

    public void showTweets();

    public ArrayList<Tweet> generateTweetsData(String tweetTextContent);
}
