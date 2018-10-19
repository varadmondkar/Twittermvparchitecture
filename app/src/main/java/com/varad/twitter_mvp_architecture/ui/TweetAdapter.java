package com.varad.twitter_mvp_architecture.ui;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varad.twitter_mvp_architecture.R;
import com.varad.twitter_mvp_architecture.data.Tweet;
import com.varad.twitter_mvp_architecture.util.GlideApp;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by varad on 11/10/18.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.VH> {

    private static final String TAG = TweetAdapter.class.getSimpleName();

    private Activity activity;
    private ArrayList<Tweet> tweetArrayList;

    public TweetAdapter(ArrayList<Tweet> tweetArrayList) {
        this.tweetArrayList = tweetArrayList;
    }

    public TweetAdapter(Activity activity, ArrayList<Tweet> tweetArrayList) {
        this.activity = activity;
        this.tweetArrayList = tweetArrayList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tweet, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final VH vh, int i) {

        Tweet tweet = tweetArrayList.get(i);

        GlideApp.with(vh.itemView)
                .load(tweet.getUserDpUrl())
                .fitCenter()
                .circleCrop()
                .into(vh.userDpImageView);

        vh.userNameTextView.setText(tweet.getUserName());
        vh.userHandleTextView.setText(tweet.getUserHandle());

        if (tweet.getIsTextContent() == 1) {
            vh.tweetTextContentLinearLayout.setVisibility(View.VISIBLE);
        } else {
            vh.tweetTextContentLinearLayout.setVisibility(View.GONE);
        }

        ArrayList<String> mediaList = tweet.getMedia();
        int photoSize = 0;
        switch (tweet.getMediaType()) {
            case Tweet.MEDIA_TYPE_NULL:
                vh.hideMedia();
                break;

            case Tweet.MEDIA_TYPE_IMAGE:
                vh.mediaTypeImage();
                photoSize = mediaList.size();
                if (photoSize > 0) {
                    vh.addPhotoLayout(mediaList, photoSize);
                }
                break;

            case Tweet.MEDIA_TYPE_VIDEO:
                vh.mediaTypeVideo();
                final String finalVideoUrl = mediaList.get(0);
                vh.videoPlayerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vh.mVideoPlayerManager.playNewVideo(null, vh.videoPlayerView,
                                finalVideoUrl);
                    }
                });
                break;

            default:
                vh.hideMedia();
                break;
        }

        vh.videoPlayerView.addMediaPlayerListener(new SimpleMainThreadMediaPlayerListener() {
            @Override
            public void onVideoSizeChangedMainThread(int width, int height) {
                super.onVideoSizeChangedMainThread(width, height);
            }

            @Override
            public void onVideoPreparedMainThread() {
                super.onVideoPreparedMainThread();
            }

            @Override
            public void onVideoCompletionMainThread() {
                super.onVideoCompletionMainThread();
            }

            @Override
            public void onErrorMainThread(int what, int extra) {
                super.onErrorMainThread(what, extra);
            }

            @Override
            public void onBufferingUpdateMainThread(int percent) {
                super.onBufferingUpdateMainThread(percent);
            }

            @Override
            public void onVideoStoppedMainThread() {
                super.onVideoStoppedMainThread();
            }
        });

////        String videoUrl = "https://video.twimg.com/amplify_video/1050733835103686656/vid/720x720/F6plZID3x0ZtRtYj.mp4?tag=8";
//        String videoUrl = "https://video.twimg.com/ext_tw_video/1037980876598964225/pu/vid/952x720/FYRyGdVWRnHytFuF.mp4?tag=5";
//        if (i % 2 == 1) {
//            videoUrl = "https://video.twimg.com/ext_tw_video/1050804944293584901/pu/vid/1280x720/u-UDIF-XKDYjMz1M.mp4?tag=5";
//        }
    }

    @Override
    public int getItemCount() {
        return tweetArrayList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.userDpLinearLayout)
        LinearLayout userDpLinearLayout;

        @BindView(R.id.userDpImageView)
        ImageView userDpImageView;

        @BindView(R.id.tweetDetailLinearLayout)
        LinearLayout tweetDetailLinearLayout;

        @BindView(R.id.userNameTextView)
        TextView userNameTextView;

        @BindView(R.id.userHandleTextView)
        TextView userHandleTextView;

        @BindView(R.id.tweetTextContentLinearLayout)
        LinearLayout tweetTextContentLinearLayout;

        @BindView(R.id.tweetTextContentTextView)
        TextView tweetTextContentTextView;

        @BindView(R.id.tweetVideoContentLinearLayout)
        LinearLayout tweetVideoContentLinearLayout;

        @BindView(R.id.videoPlayerView)
        VideoPlayerView videoPlayerView;

        @BindView(R.id.tweetPhotoContentLinearLayout)
        LinearLayout tweetPhotoContentLinearLayout;

        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;

        VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void showMedia() {
            tweetPhotoContentLinearLayout.setVisibility(View.VISIBLE);
            tweetVideoContentLinearLayout.setVisibility(View.VISIBLE);
        }

        private void hideMedia() {
            tweetPhotoContentLinearLayout.setVisibility(View.GONE);
            tweetVideoContentLinearLayout.setVisibility(View.GONE);
        }

        private void mediaTypeImage() {
            tweetPhotoContentLinearLayout.setVisibility(View.VISIBLE);
            tweetVideoContentLinearLayout.setVisibility(View.GONE);
        }

        private void mediaTypeVideo() {
            tweetPhotoContentLinearLayout.setVisibility(View.GONE);
            tweetVideoContentLinearLayout.setVisibility(View.VISIBLE);
        }

        private void addPhotoLayout(ArrayList<String> photos, int photoSize) {

            int layoutToBeInflated;
            switch (photoSize) {
                case 1:
                    layoutToBeInflated = R.layout.tweet_photo_gallery_one;
                    break;

                case 2:
                    layoutToBeInflated = R.layout.tweet_photo_gallery_two;
                    break;

                case 3:
                    layoutToBeInflated = R.layout.tweet_photo_gallery_three;
                    break;

                case 4:
                    layoutToBeInflated = R.layout.tweet_photo_gallery_four_and_plus;
                    break;

                default:
                    layoutToBeInflated = R.layout.tweet_photo_gallery_one;
                    break;
            }

            View photoGalleryView = activity.getLayoutInflater().inflate(layoutToBeInflated, tweetPhotoContentLinearLayout, false);
            tweetPhotoContentLinearLayout.addView(photoGalleryView);

            // For border radius and clipping children of main LinearLayout
            tweetPhotoContentLinearLayout.setBackground(ContextCompat.getDrawable(activity, R.drawable.media_section_background));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tweetPhotoContentLinearLayout.setClipToOutline(true);
            }

            imageView1 = itemView.findViewById(R.id.imageView1);
            imageView2 = itemView.findViewById(R.id.imageView2);
            imageView3 = itemView.findViewById(R.id.imageView3);
            imageView4 = itemView.findViewById(R.id.imageView4);

            setPhotos(photos, photoSize);
        }

        private void setPhotos(ArrayList<String> photos, int photoSize) {
            switch (photoSize) {
                case 1:
                    GlideApp.with(itemView)
                            .load(photos.get(0))
                            .centerCrop()
                            .into(imageView1);
                    break;

                case 2:
                    GlideApp.with(itemView)
                            .load(photos.get(0))
                            .centerCrop()
                            .into(imageView1);

                    GlideApp.with(itemView)
                            .load(photos.get(1))
                            .centerCrop()
                            .into(imageView2);
                    break;

                case 3:
                    GlideApp.with(itemView)
                            .load(photos.get(0))
                            .centerCrop()
                            .into(imageView1);

                    GlideApp.with(itemView)
                            .load(photos.get(1))
                            .centerCrop()
                            .into(imageView2);

                    GlideApp.with(itemView)
                            .load(photos.get(2))
                            .centerCrop()
                            .into(imageView3);
                    break;

                case 4:
                    GlideApp.with(itemView)
                            .load(photos.get(0))
                            .centerCrop()
                            .into(imageView1);

                    GlideApp.with(itemView)
                            .load(photos.get(1))
                            .centerCrop()
                            .into(imageView2);

                    GlideApp.with(itemView)
                            .load(photos.get(2))
                            .centerCrop()
                            .into(imageView3);

                    GlideApp.with(itemView)
                            .load(photos.get(3))
                            .centerCrop()
                            .into(imageView4);
                    break;
            }
        }

        private VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
            @Override
            public void onPlayerItemChanged(MetaData metaData) {

            }
        });
    }
}
