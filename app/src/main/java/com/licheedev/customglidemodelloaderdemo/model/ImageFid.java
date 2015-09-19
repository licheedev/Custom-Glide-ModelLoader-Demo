package com.licheedev.customglidemodelloaderdemo.model;

/**
 * Created by John on 2015/9/19.
 */
public class ImageFid {
    // fid
    private String mFid;
    // fid对应的url
    private String mUrl;

    public ImageFid(String fid) {
        mFid = fid;
    }

    // 需要重写equals和hashCode，用于从缓存中取出ImageFid
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImageFid imageFid = (ImageFid) o;

        return mFid.equals(imageFid.mFid);

    }

    @Override
    public int hashCode() {
        return mFid.hashCode();
    }

    public String getFid() {
        return mFid;
    }

    public void setFid(String fid) {
        mFid = fid;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
