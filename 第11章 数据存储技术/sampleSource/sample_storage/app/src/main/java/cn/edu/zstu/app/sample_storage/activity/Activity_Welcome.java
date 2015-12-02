package cn.edu.zstu.app.sample_storage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import cn.edu.zstu.app.sample_storage.R;
import cn.edu.zstu.app.sample_storage.utils.SharePreferenceUtil;

/**
 * Created by lenovo on 2015/11/27.
 */
public class Activity_Welcome extends Activity implements View.OnClickListener{
    private EditText et_username,et_password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取用户登录信息
        String spUser= SharePreferenceUtil.getSharePreStr(this,"userinfo","username");
        long spLastLogin=SharePreferenceUtil.getSharePreLong(this, "userinfo", "logintime");
        //检查用户登录信息有效性，username不为空，logintime不超过24小时
        long now=Calendar.getInstance().getTimeInMillis();
        if(now-spLastLogin<24*3600*1000 && spUser!=""){
            //更新用户登录信息
            SharePreferenceUtil.putSharePre(this,"userinfo","logintime",now);
            //启动主界面
            Intent intent=new Intent(this,Sample_Storage_main.class);
            startActivity(intent);
            this.finish();
        }
        setContentView(R.layout.activity_welcome);
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //写入SharedPreferences
                String username=et_username.getText().toString();
                Long logintime=Calendar.getInstance().getTimeInMillis();
                SharePreferenceUtil.putSharePre(this,"userinfo","username",username);
                SharePreferenceUtil.putSharePre(this,"userinfo","logintime",logintime);

                //启动主界面
                Intent intent=new Intent(this,Sample_Storage_main.class);
                startActivity(intent);
                this.finish();
                break;
            default:break;
        }
    }
}
