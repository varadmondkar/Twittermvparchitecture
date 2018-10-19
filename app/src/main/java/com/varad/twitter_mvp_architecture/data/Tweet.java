package com.varad.twitter_mvp_architecture.data;

import java.util.ArrayList;

/**
 * Created by varad on 11/10/18.
 */
public class Tweet {

    public final static int MEDIA_TYPE_NULL = 0; // 0 - No media
    public final static int MEDIA_TYPE_IMAGE = 1; // 1 - Image
    public final static int MEDIA_TYPE_VIDEO = 2; // 2 - Video
    public final static int MEDIA_TYPE_GIF = 3; // 3 - GIFs

    private int id;
    private String userDpUrl;
    private String userName;
    private String userHandle;
    private String textContent;
    private int isTextContent;
    private int mediaType;
    private ArrayList<String> media;

    public Tweet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserDpUrl() {
        return userDpUrl;
    }

    public void setUserDpUrl(String userDpUrl) {
        this.userDpUrl = userDpUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getIsTextContent() {
        return isTextContent;
    }

    public void setIsTextContent(int isTextContent) {
        this.isTextContent = isTextContent;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public ArrayList<String> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<String> media) {
        this.media = media;
    }
}
