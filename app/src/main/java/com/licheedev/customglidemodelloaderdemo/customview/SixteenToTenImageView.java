package com.licheedev.customglidemodelloaderdemo.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by John on 2015/9/19.
 */
public class SixteenToTenImageView extends ImageView {

    public SixteenToTenImageView(Context context) {
        super(context);
    }

    public SixteenToTenImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SixteenToTenImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 锁定宽高位16:10
        int height = (int) (width * 10f / 16);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
