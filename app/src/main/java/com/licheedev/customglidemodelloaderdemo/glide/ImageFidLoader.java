package com.licheedev.customglidemodelloaderdemo.glide;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.licheedev.customglidemodelloaderdemo.model.ImageFid;

import java.io.InputStream;

/**
 * Created by John on 2015/9/19.
 */
public class ImageFidLoader implements ModelLoader<ImageFid,InputStream> {

    private final ModelCache<ImageFid, ImageFid> mModelCache;

    public ImageFidLoader() {
        this(null);
    }

    public ImageFidLoader(ModelCache<ImageFid, ImageFid> modelCache) {
        mModelCache = modelCache;
    }

    @Override
    public DataFetcher<InputStream> getResourceFetcher(ImageFid model, int width, int height) {
        ImageFid imageFid = model;
        // 从缓存中取出ImageFid,ImgeFid已重写equals()和hashCode()方法
        // 缓存中ImgeFid对象的url，有可能还没被初始化
        if (mModelCache != null) {
            imageFid = mModelCache.get(model, 0, 0);
            if (imageFid == null) {
                mModelCache.put(model, 0, 0, model);
                imageFid = model;
            }
        }
        return new ImageFidFetcher(imageFid);
    }

    // ModelLoader工厂，在向Glide注册自定义ModelLoader时使用到
    public static class Factory implements ModelLoaderFactory<ImageFid, InputStream> {
        // 缓存
        private final ModelCache<ImageFid, ImageFid> mModelCache = new ModelCache<>(500);
        
        @Override
        public ModelLoader<ImageFid, InputStream> build(Context context,
            GenericLoaderFactory factories) {
            // 返回ImageFidLoader对象
            return new ImageFidLoader(mModelCache);
        }

        @Override
        public void teardown() {

        }
    }
    
}
