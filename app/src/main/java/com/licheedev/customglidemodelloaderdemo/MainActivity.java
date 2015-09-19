package com.licheedev.customglidemodelloaderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.licheedev.customglidemodelloaderdemo.adapter.ItemAdapter;
import com.licheedev.customglidemodelloaderdemo.model.ImageFid;

public class MainActivity extends AppCompatActivity {

    private android.widget.Button mBtnLoadImages;
    private android.widget.ListView mLvImages;
    private ImageFid[] mImageFids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews() {
        this.mLvImages = (ListView) findViewById(R.id.lvImages);
        this.mBtnLoadImages = (Button) findViewById(R.id.btnLoadImages);
    }

    private void initData() {
        // 模拟ImageFid，fid格式为 SAMPLE_IMG_ddd.jpg，ddd为数字
        mImageFids = new ImageFid[100];
        for (int i = 0; i < mImageFids.length; i++) {
            mImageFids[i] = new ImageFid(String.format("SAMPLE_IMG_%03d.jpg", i + 1));
        }
    }

    public void loadImages(View view) {
        ItemAdapter adapter = new ItemAdapter(this, mImageFids);
        mLvImages.setAdapter(adapter);
    }
}
