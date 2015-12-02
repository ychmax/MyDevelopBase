package cn.edu.zstu.app.sample_storage.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2015/11/26.
 */
public class SharePreferenceUtil {
    public static  String getSharePreStr(Context mContext,String whichSp,String field){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp,
                Context.MODE_PRIVATE);
        return sp.getString(field, "");//如果该字段没对应值，则取出空字符串
    }
    public static long getSharePreLong(Context mContext,String whichSp,String field){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp,
                Context.MODE_PRIVATE);
        return sp.getLong(field, 0);//如果该字段没对应值，则取出0
    }
    public static void putSharePre(Context mContext,String whichSp,String field,String value){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp,
                Context.MODE_PRIVATE);
        sp.edit().putString(field, value).commit();
    }
    public static void putSharePre(Context mContext,String whichSp,String field,long value){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp,
                Context.MODE_PRIVATE);
        sp.edit().putLong(field, value).commit();
    }
}
