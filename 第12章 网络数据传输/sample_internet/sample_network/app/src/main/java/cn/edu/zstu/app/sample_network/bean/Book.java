package cn.edu.zstu.app.sample_network.bean;

import java.util.HashMap;

/**
 * Created by lenovo on 2015/12/10.
 */
public class Book {
    public Book() {
    }
    public Book(HashMap<String,Object> hashMap) {
        this.bookname= (String) hashMap.get("bookname");
        this.bookurl= (String) hashMap.get("bookurl");
        this.bookdescrible= (String) hashMap.get("bookdescrible");
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    String bookname;

    public String getBookdescrible() {
        return bookdescrible;
    }

    public void setBookdescrible(String bookdescrible) {
        this.bookdescrible = bookdescrible;
    }

    String bookdescrible;
    String bookurl;
}
