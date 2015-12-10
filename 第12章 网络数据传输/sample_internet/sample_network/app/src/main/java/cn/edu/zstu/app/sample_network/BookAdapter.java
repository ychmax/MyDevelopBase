package cn.edu.zstu.app.sample_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cn.edu.zstu.app.sample_network.Utils.HttpUtil;
import cn.edu.zstu.app.sample_network.bean.Book;

/**
 * Created by lenovo on 2015/12/10.
 */
public class BookAdapter extends BaseAdapter {
    List<Book> list;
    Context context;
    LayoutInflater layoutInflater;

    public BookAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookItem bookItem=null;
        if(convertView==null){
            bookItem=new BookItem();
            convertView=layoutInflater.inflate(R.layout.item,null);
            bookItem.bookdescribe= (TextView) convertView.findViewById(R.id.tx_book_describe);
            bookItem.bookname= (TextView) convertView.findViewById(R.id.tx_book_name);
            bookItem.bookPic= (ImageView) convertView.findViewById(R.id.im_book_pic);
            convertView.setTag(bookItem);
        }else{
            bookItem= (BookItem) convertView.getTag();
        }
        HashMap<String,String> obj= (HashMap<String, String>) getItem(position);
        Book book= new Book();
        book.setBookdescrible(obj.get("bookdescrible"));
        book.setBookname(obj.get("bookname"));
        book.setBookurl(obj.get("bookurl"));
        bookItem.bookname.setText(book.getBookname());
        bookItem.bookdescribe.setText(book.getBookdescrible());
        HttpUtil.getInstance(context).displayImg(bookItem.bookPic,book.getBookurl());
        return convertView;
    }

    public static class BookItem{
        ImageView bookPic;
        TextView bookname;
        TextView bookdescribe;
    }
}
