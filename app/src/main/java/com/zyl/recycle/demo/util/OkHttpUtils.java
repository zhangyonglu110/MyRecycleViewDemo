package com.zyl.recycle.demo.util;



import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhangyonglu on 2016/5/30.
 */
public class OkHttpUtils {
    private static OkHttpClient httpClient=new OkHttpClient();
    private static OkHttpClient loginhttpClient;
    private  static  Request request;
    private  static RequestBody requestBody;
    private static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json;charset=UTF-8");
    private static OkHttpClient.Builder builder;

    public static void get(String url, Callback callback){
        request =new Request.Builder().url(url).build();

        excute(request,callback);
    }

    public static void post(String url, RequestBody body, Callback callback){
      request=new Request.Builder().url(url).post(body).build();

        excute(request,callback);
    }
    public static void excute(Request request,Callback callback) {
        httpClient.newCall(request).enqueue(callback);

    }

}
