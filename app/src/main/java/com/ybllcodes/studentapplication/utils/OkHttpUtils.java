package com.ybllcodes.studentapplication.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.ybllcodes.studentapplication.pojo.HraControl;
import com.ybllcodes.studentapplication.pojo.ResultBean;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {

    public static HttpCallback httpCallback;
    public ResultBean rb;

    public static void setHttpCallback(HttpCallback httpCallback) {
        OkHttpUtils.httpCallback = httpCallback;
    }

    public static Response response;
    public static OkHttpClient okHttpClient = new OkHttpClient();
    public static MediaType mediaType = MediaType.parse("application/json");

    public static final Integer MSG_EMPTY = 0;
    public static final Integer MSG_VALUE = 1;
    public static final Integer MSG_P_CONTROL = 10;
    public static final Integer MSG_S_CONTROL = 5;
    public static Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            ResultBean rb = (ResultBean) msg.obj;
            System.out.println(rb);
            switch (what){
                case 0:
                    //返回为空
                    break;
                case 1:
                    httpCallback.setMain(rb);
                    break;
                case 10:
                    httpCallback.setMainForPControl(rb);
                    break;
                case 5:
                    httpCallback.setMainForSControl(rb);
                    break;
            }
        }
    };

    public static String sendMapHttp(String url, Map<String,String> map){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求详情
                response = post(url, map);

                if(response == null){
                    System.out.println("=====sendMapHttp====01");
                    myHandler.sendMessage(myHandler.obtainMessage(MSG_EMPTY));
                }else {
                    System.out.println("=====sendMapHttp====02");
                    ResponseBody body = response.body();
                    String bodyStr = null;
                    try {
                        bodyStr = body.string();
                        ResultBean resultBean = JSON.parseObject(bodyStr, ResultBean.class);
                        if(resultBean != null){
                            Integer msgType = resultBean.getMsgType();
                            myHandler.sendMessage(myHandler.obtainMessage(msgType,resultBean));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
        }).start();
        return "123";
    }

    public static Response post(String url, Map<String,String> map){
        //1.获取封装请求体requestBody
        String s = JSON.toJSONString(map);
        System.out.println(s);
        RequestBody body = RequestBody.create(mediaType, s);

        Request build = new Request.Builder()
                .post(body)
                .url(url)
                .addHeader("content-type", "application/json")
                .build();
        try {
            Response response = okHttpClient.newCall(build).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendHttp(String url, HraControl hc){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求详情

                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json");


                //1.获取封装请求体requestBody
                String s = JSON.toJSONString(hc);
                System.out.println(s);
                RequestBody body = RequestBody.create(mediaType, s);

                //
                Request build = new Request.Builder()
                        .post(body)
                        .url(url)
                        .addHeader("content-type", "application/json")
                        .build();
                try {
                    okHttpClient.newCall(build).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            };
        }).start();


        return "123";

    }
}
