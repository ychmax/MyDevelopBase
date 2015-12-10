package cn.edu.zstu.app.sample_network.Utils;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.android.volley.VolleyLog;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import cn.edu.zstu.app.sample_network.MainActivity;

/**
 * Created by lenovo on 2015/12/8.
 */
public class HttpUtilTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mActivity;
    RequestListener<Object> requestListener=new RequestListener<Object>() {
        @Override
        public void onSuccess(Object o) {
            Log.d("test",o.toString());
        }

        @Override
        public void onError(String error, String detail) {
            VolleyLog.d("ych",error,detail);
        }
    };

    public HttpUtilTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        mActivity=getActivity();
    }

    @Test
    public void testDoStringRequest() throws Exception {

        HttpUtil.getInstance(mActivity).doStringRequest("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?appid=379020&bk_key=android", requestListener);
    }

    @Test
    public void testDoJsonRequest() throws Exception {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("appid","379020");
        jsonObject.put("bk_key","android");
        HttpUtil.getInstance(mActivity).doJsonRequest("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi",jsonObject, requestListener);
    }


}