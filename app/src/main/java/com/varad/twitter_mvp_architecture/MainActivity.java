package com.varad.twitter_mvp_architecture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.varad.twitter_mvp_architecture.data.Tweet;
import com.varad.twitter_mvp_architecture.ui.TweetAdapter;
import com.varad.twitter_mvp_architecture.ui.TweetPresenter;
import com.varad.twitter_mvp_architecture.ui.TweetPresenterImpl;
import com.varad.twitter_mvp_architecture.ui.TweetView;
import com.varad.twitter_mvp_architecture.util.GlideApp;
import com.varad.twitter_mvp_architecture.util.SimpleDividerItemDecoration;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TweetView {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TweetPresenter<TweetView> mTweetPresenter;

    private ArrayList<Tweet> tweetArrayList = new ArrayList<>();

    private TweetAdapter tweetAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigationIconImageView)
    ImageView navigationIconImageView;

    @BindView(R.id.toolbarTitleTextView)
    TextView toolbarTitleTextView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

        mTweetPresenter = new TweetPresenterImpl<>();
        mTweetPresenter.onAttach(this);

        String tweetTextContent = getString(R.string.dummy_tweet_text_content);
        tweetArrayList = mTweetPresenter.generateTweetsData(tweetTextContent);

        tweetAdapter = new TweetAdapter(this, tweetArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(recyclerView.getContext()));
        recyclerView.setAdapter(tweetAdapter);
    }

    public void initView() {
        GlideApp.with(this)
                .load(R.mipmap.ic_launcher_round)
                .fitCenter()
                .circleCrop()
                .into(navigationIconImageView);
    }

    private void setData() {

        for (int i = 0; i < 20; i++) {

            Tweet tweet = new Tweet();

            int itemPosition = i + 1;

            tweet.setId(itemPosition);

            if (itemPosition % 3 == 0) {
                tweet.setIsTextContent(0);
            } else {
                tweet.setIsTextContent(1);
            }

            tweet.setTextContent(getString(R.string.dummy_tweet_text_content));

            int mod = itemPosition % 5;

            ArrayList<String> photos = new ArrayList<>();
            ArrayList<String> videos = new ArrayList<>();

            switch (mod) {
                case 1:
                    tweet.setUserName("Varad Mondkar");
                    tweet.setUserHandle("@varadmondkar");

                    tweet.setUserDpUrl("https://pbs.twimg.com/profile_images/874269731187965952/fg5yxzXP_400x400.jpg");

                    tweet.setMediaType(1);
                    photos.add("https://pbs.twimg.com/media/DpWsF6HUYAAo9us.jpg");
                    photos.add("https://pbs.twimg.com/media/DpWsGxyUcAEjxA2.jpg");
                    photos.add("https://pbs.twimg.com/media/DpWsHgpU4AARQK_.jpg");
                    break;

                case 2:
                    tweet.setUserName("Viraj Khatavkar");
                    tweet.setUserHandle("@virajkhatavkar");

                    tweet.setUserDpUrl("https://pbs.twimg.com/profile_images/924896616011546624/uRFuVmw-_400x400.jpg");

                    tweet.setMediaType(1);
                    photos.add("https://pbs.twimg.com/media/DpcgO95U8AAvl4d.jpg");
                    photos.add("https://pbs.twimg.com/media/DpcgRR_UcAE9kh3.jpg");
                    photos.add("https://pbs.twimg.com/media/DpcgTS-UcAIUuNo.jpg");
                    photos.add("https://pbs.twimg.com/media/DpcgVksUYAErtPL.jpg");
                    break;

                case 3:
                    tweet.setUserName("Mike Penz");
                    tweet.setUserHandle("@mikepenz");

                    tweet.setUserDpUrl("https://pbs.twimg.com/profile_images/560193481688104960/_ycSgnFs_400x400.jpeg");

                    tweet.setMediaType(1);
                    photos.add("https://pbs.twimg.com/media/DpYiz82VAAA_Tx7.jpg");
                    photos.add("https://pbs.twimg.com/media/DpYi1WgUUAAnyHS.jpg");
                    break;

                case 0:
                    tweet.setUserName("Yatin Deokar");
                    tweet.setUserHandle("@yatindeokar");

                    tweet.setUserDpUrl("https://pbs.twimg.com/profile_images/1035125494960680962/0QapHQK7_400x400.jpg");

                    tweet.setMediaType(2);
                    videos.add("https://video.twimg.com/ext_tw_video/1037980876598964225/pu/vid/952x720/FYRyGdVWRnHytFuF.mp4?tag=5");
                    break;

                default:
                    tweet.setUserName("Gaurav Dhaimodkar");
                    tweet.setUserHandle("@gauravdhaimodkar123456");

                    tweet.setUserDpUrl("https://pbs.twimg.com/profile_images/1051130590760787968/cHnuyGwK_400x400.jpg");

                    tweet.setMediaType(1);
                    photos.add("https://pbs.twimg.com/media/DpYhmNqVAAEqKV1.jpg");
                    break;
            }

            if (tweet.getMediaType() == 1) {
                tweet.setMedia(photos);
            }

            if (tweet.getMediaType() == 2) {
                tweet.setMedia(videos);
            }

            tweetArrayList.add(i, tweet);
        }

        Type tweetType = new TypeToken<ArrayList<Tweet>>() {
        }.getType();

        Gson gson = new Gson();
        String jsonData = gson.toJson(tweetArrayList, tweetType);
//        Log.d(TAG, "setData: " + jsonData);
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        mTweetPresenter.onDetach();
        super.onDestroy();
    }
}
