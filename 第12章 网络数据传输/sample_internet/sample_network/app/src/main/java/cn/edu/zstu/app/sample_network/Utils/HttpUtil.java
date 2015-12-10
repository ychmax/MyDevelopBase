package cn.edu.zstu.app.sample_network.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import cn.edu.zstu.app.sample_network.R;

/**
 * Created by lenovo on 2015/12/8.
 */
public class HttpUtil {
    Context context;
    private RequestQueue mQueue;
    private static HttpUtil httpUtil;

    public HttpUtil(Context context) {
        this.context = context.getApplicationContext();
        mQueue= Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized HttpUtil getInstance(Context context){
        if(httpUtil==null){
            httpUtil=new HttpUtil(context);
        }
        return httpUtil;
    }

    public  void doStringRequest(String url,RequestListener listener){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,listener,listener);
        mQueue.add(stringRequest);
    }

    public void doJsonRequest(String url,JSONObject jsonObject,RequestListener listener){

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,jsonObject,listener,listener);
        mQueue.add(jsonObjectRequest);
    }

    public void doImageRequest(String url,JSONObject jsonObject,RequestListener listener){

        ImageRequest imageRequest=new ImageRequest(url,listener,0,0, ImageView.ScaleType.CENTER,Bitmap.Config.RGB_565,listener);
        mQueue.add(imageRequest);
    }

    public void displayImg(ImageView view,String url){

        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> cache=new LruCache<String,Bitmap>(8*1024*1024){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes()*value.getHeight();
                }
            };
            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,bitmap);
            }
        });

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(view, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, listener);
        //指定图片允许的最大宽度和高度
        //imageLoader.get(url,listener, 200, 200);
    }

}
