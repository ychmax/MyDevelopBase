package cn.edu.zstu.app.sample_storage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cn.edu.zstu.app.sample_storage.bean.User;

/**
 * Created by lenovo on 2015/11/26.
 */
public class OpUser {
    Context context;

    DBHelper dbHelper;
    SQLiteDatabase db;

    public OpUser(Context context) {
        this.context = context;
        dbHelper=DBHelper.getInstance(context);
        db=dbHelper.getReadableDatabase();
    }

    public ArrayList<User> getUserList(){
        ArrayList<User> list=new ArrayList<User> ();

        Cursor c=db.query("t_user",null,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            User u=new User();
            u.setId(c.getInt(0));
            u.setName(c.getString(1));
            u.setSex(c.getString(2));
            u.setAge(c.getInt(3));
            list.add(u);
            c.moveToNext();
        }
        return list;
    }

    public void insertUser(User u){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",u.getName());
        contentValues.put("sex",u.getSex());
        contentValues.put("age",u.getAge());
        db.insert("t_user",null,contentValues);
    }

    public void close(){
        db.close();
    }
}
