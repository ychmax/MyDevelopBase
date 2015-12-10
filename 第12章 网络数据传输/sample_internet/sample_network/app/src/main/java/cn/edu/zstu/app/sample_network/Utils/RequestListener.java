package cn.edu.zstu.app.sample_network.Utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by lenovo on 2015/12/8.
 */
public abstract class RequestListener<T> implements Response.Listener<T>,Response.ErrorListener {
    public abstract void onSuccess(T o);
    public abstract void onError(String error,String detail);

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        onError(volleyError.getMessage(),volleyError.networkResponse.toString());
    }

    @Override
    public void onResponse(T o) {
        onSuccess(o);
    }
}
