package cn.edu.zstu.app.sample_network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zstu.app.sample_network.Utils.HttpUtil;
import cn.edu.zstu.app.sample_network.Utils.JSonUtil;
import cn.edu.zstu.app.sample_network.Utils.RequestListener;
import cn.edu.zstu.app.sample_network.bean.Book;

public class MainActivity extends AppCompatActivity {

    ListView listbook;
    TextView bookowner;
    List<Book> bookList;
    String strBookOwner;
    BookAdapter simpleAdapter;
    RequestListener<String> jsondatalistner=new RequestListener<String>() {
        @Override
        public void onSuccess(String o) {
            strBookOwner=JSonUtil.toObjectString(o, "name");
            String books= JSonUtil.getJsonElementString(o, "books");
            bookList=JSonUtil.toObjectList(books);
            simpleAdapter.setList(bookList);
            simpleAdapter.notifyDataSetChanged();
            bookowner.setText(strBookOwner);
        }

        @Override
        public void onError(String error, String detail) {
            Log.e("Error",error+detail);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listbook= (ListView) findViewById(R.id.listbook);
        bookowner= (TextView) findViewById(R.id.bookowner);

        bookList=new ArrayList<>();
        simpleAdapter=new BookAdapter(this,bookList);
        listbook.setAdapter(simpleAdapter);

        HttpUtil.getInstance(this).doStringRequest(
                "http://192.168.1.106:8080/sample_internet_server/bookJsonData?name=Alice",
                jsondatalistner);
    }
}
