package com.licheedev.customglidemodelloaderdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.licheedev.customglidemodelloaderdemo.R;
import com.licheedev.customglidemodelloaderdemo.model.ImageFid;

/**
 * Created by John on 2015/9/19.
 */
public class ItemAdapter extends ArrayAdapter<ImageFid> {

    private final DrawableRequestBuilder<ImageFid> mGlideBuilder;

    public ItemAdapter(Context context, ImageFid[] images) {
        super(context, 0, images);
        mGlideBuilder = Glide.with(context)
            .from(ImageFid.class) // 设置数据源类型为我们的ImageFid
            .fitCenter().crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL) // 设置本地缓存,缓存源文件和目标图像
            .placeholder(R.mipmap.ic_launcher);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView =
                LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageFid fid = getItem(position);
        // 加载fid
        mGlideBuilder.load(fid).into(holder.ivImage);
        holder.tvName.setText(fid.getFid());
        return convertView;
    }

    private static class ViewHolder {
        public TextView tvName;
        public ImageView ivImage;

        public ViewHolder(View view) {
            this.tvName = (TextView) view.findViewById(R.id.tvName);
            this.ivImage = (ImageView) view.findViewById(R.id.ivImage);
        }
    }

}
