package cn.edu.zstu.app.sample_storage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import cn.edu.zstu.app.sample_storage.R;
import cn.edu.zstu.app.sample_storage.bean.User;
import cn.edu.zstu.app.sample_storage.db.OpUser;

public class Sample_Storage_main extends AppCompatActivity {

    EditText et_name,et_sex,et_age;
    Button btn_insert,btn_show;
    ListView list_subject;
    ArrayList<HashMap<String,String>> listfresher=new ArrayList<>();

    OpUser op;
    SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample__storage_main);

        et_name= (EditText) findViewById(R.id.et_name);
        et_sex= (EditText) findViewById(R.id.et_sex);
        et_age= (EditText) findViewById(R.id.et_age);
        btn_insert= (Button) findViewById(R.id.btn_Insert);
        btn_show= (Button) findViewById(R.id.btnshowdata);
        list_subject= (ListView) findViewById(R.id.lst_subject);


        op=new OpUser(this);
        ArrayList<User> listData=op.getUserList();
        for(User u:listData){
            HashMap map=new HashMap();
            map.put("userid",u.getId());
            map.put("username",u.getName());
            map.put("usersex",u.getSex());
            map.put("userage",u.getAge());
            listfresher.add(map);
        }

        simpleAdapter=new SimpleAdapter(this,listfresher,R.layout.item,new String[]{"userid","username","usersex","userage"},
                new int[]{R.id.userid,R.id.username,R.id.usersex,R.id.userage});
        list_subject.setAdapter(simpleAdapter);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setName(et_name.getText().toString());
                user.setSex(et_sex.getText().toString());
                user.setAge(Integer.valueOf(et_age.getText().toString()));
                op.insertUser(user);
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> listData=op.getUserList();
                listfresher.clear();
                for(User u:listData){
                    HashMap map=new HashMap();
                    map.put("userid",u.getId());
                    map.put("username",u.getName());
                    map.put("usersex",u.getSex());
                    map.put("userage",u.getAge());
                    listfresher.add(map);
                }
                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        op.close();
        op=null;
    }
}
