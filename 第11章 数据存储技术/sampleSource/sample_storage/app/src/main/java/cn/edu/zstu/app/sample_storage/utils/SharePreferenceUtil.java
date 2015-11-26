package cn.edu.zstu.app.sample_storage.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2015/11/26.
 */
public class SharePreferenceUtil {
    //取出whichSp中field字段对应的string类型的值
    static public String getSharePreStr(Context mContext,String whichSp,String field){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp,Context.MODE_PRIVATE);
        String s=sp.getString(field,"");//如果该字段没对应值，则取出字符串0
        return s;
    }
    //取出whichSp中field字段对应的int类型的值
    public static int getSharePreInt(Context mContext,String whichSp,String field){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp, Context.MODE_PRIVATE);
        int i=sp.getInt(field, 0);//如果该字段没对应值，则取出0
        return i;
    }
    //保存string类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext,String whichSp,String field,String value){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp, Context.MODE_PRIVATE);
        sp.edit().putString(field, value).commit();
    }
    //保存int类型的value到whichSp中的field字段
    public static void putSharePre(Context mContext,String whichSp,String field,int value){
        SharedPreferences sp=(SharedPreferences) mContext.getSharedPreferences(whichSp, Context.MODE_PRIVATE);
        sp.edit().putInt(field, value).commit();
    }

}
