package com.licheedev.customglidemodelloaderdemo.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.licheedev.customglidemodelloaderdemo.base.Config;
import com.licheedev.customglidemodelloaderdemo.model.ImageFid;
import com.licheedev.customglidemodelloaderdemo.okhttp.OkHttpManager;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by John on 2015/9/19.
 */
public class ImageFidFetcher implements DataFetcher<InputStream> {

    // 检查是否取消任务的标识
    private volatile boolean mIsCanceled;

    private final ImageFid mImageFid;
    private Call mFetchUrlCall;
    private Call mFetchStreamCall;
    private InputStream mInputStream;

    public ImageFidFetcher(ImageFid imageFid) {
        mImageFid = imageFid;
    }

    /**
     * 在后台线程中调用，用于获取图片的数据流，给Glide处理
     *
     * @param priority
     * @return
     * @throws Exception
     */
    @Override
    public InputStream loadData(Priority priority) throws Exception {
        // mImageFid有可能是来自缓存的，先从此对象获取url
        String url = mImageFid.getUrl();
        if (url == null) {
            if (mIsCanceled) {
                return null;
            }
            // 建立http请求，从网络上获取fid对应的的url
            url = fetchImageUrl();
            if (url == null) {
                return null;
            }
            // 存储获取到的url，以供缓存使用
            System.out.println("从网上获取" + url);
            mImageFid.setUrl(url);
        }
        if (mIsCanceled) {
            return null;
        }
        // 再次建立http请求，获取url的流
        mInputStream = fetchStream(url);
        return mInputStream;
    }

    /**
     * 获取图片fid对应的url
     *
     * @return
     */
    private String fetchImageUrl() {
        // 缓存请求，用来及时取消连接
        mFetchUrlCall = syncGet(Config.IMAGE_REQUEST_URL);
        try {
            String json = mFetchUrlCall.execute().body().string();
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString("prefix") + mImageFid.getFid();
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return null;
    }

    private InputStream fetchStream(String url) {
        // 缓存请求，用来及时取消连接
        mFetchStreamCall = syncGet(url);
        try {
            return mFetchStreamCall.execute().body().byteStream();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * 同步的http get请求
     *
     * @param url 要访问的url
     * @return
     */
    private Call syncGet(String url) {
        Request request = new Request.Builder().url(url).get().build();
        return OkHttpManager.getClient().newCall(request);
    }


    /**
     * 在后台线程中调用，在Glide处理完{@link #loadData(Priority)}返回的数据后，进行清理和回收资源
     */
    @Override
    public void cleanup() {
        if (mInputStream != null) {
            try {
                mInputStream.close();
            } catch (IOException e) {
                //e.printStackTrace();
            } finally {
                mInputStream = null;
            }
        }
    }

    /**
     * 在UI线程中调用，返回用于区别数据的唯一id
     *
     * @return
     */
    @Override
    public String getId() {
        return mImageFid.getFid();
    }

    /**
     * 在UI线程中调用，取消加载任务
     */
    @Override
    public void cancel() {
        mIsCanceled = true;
        // 取消获取url
        if (mFetchUrlCall != null) {
            mFetchUrlCall.cancel();
        }
        // 取消下载文件
        if (mFetchStreamCall != null) {
            mFetchStreamCall.cancel();
        }
    }
}
