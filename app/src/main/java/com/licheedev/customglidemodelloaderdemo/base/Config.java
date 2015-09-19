package com.licheedev.customglidemodelloaderdemo.base;

/**
 * Created by John on 2015/9/19.
 */
public class Config {
    /**
     * 获取fid对应的url时，所用的模拟api，实际上为固定的json:
     * <br/>{
     "prefix": "http://7xlwmc.com1.z0.glb.clouddn.com/"
     }<br/>
     里面的内容拼接上fid即为对应的url，然而为了模拟二次http请求，这个api会被请求多次
     */
    public static final String IMAGE_REQUEST_URL = "http://7xlwmc.com1.z0.glb.clouddn.com/prefix.json";
}
