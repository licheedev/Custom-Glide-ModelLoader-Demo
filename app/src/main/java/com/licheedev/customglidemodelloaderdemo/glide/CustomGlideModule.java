package com.licheedev.customglidemodelloaderdemo.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.licheedev.customglidemodelloaderdemo.R;
import com.licheedev.customglidemodelloaderdemo.model.ImageFid;

import java.io.InputStream;

/**
 * Created by John on 2015/9/19.
 */
public class CustomGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id); // 设置别的get/set tag id，以免占用View默认的
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888); // 设置图片质量为高质量
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // 注册我们的ImageFidLoader
        glide.register(ImageFid.class, InputStream.class, new ImageFidLoader.Factory());
    }
}
