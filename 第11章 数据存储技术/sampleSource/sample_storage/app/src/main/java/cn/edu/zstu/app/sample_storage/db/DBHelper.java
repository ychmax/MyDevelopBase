package cn.edu.zstu.app.sample_storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by lenovo on 2015/11/26.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static String sdcardpath= Environment.getExternalStorageDirectory().getAbsolutePath();
    private static  String DBNAME="db_subject.db";//sdcardpath+ File.separator+"sample_storage\\db\\db_subject";
    private final static String createuser=" create table t_user(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,sex VARCHAR(2), age INTEGER);";
    private static DBHelper dbHelper=null;


    public DBHelper(Context context,int version){
        super(context,DBNAME,null,1);
    }

    public static  synchronized DBHelper getInstance(Context context){
        if(dbHelper==null){
            dbHelper=new DBHelper(context,1);
        }
        return dbHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createuser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
