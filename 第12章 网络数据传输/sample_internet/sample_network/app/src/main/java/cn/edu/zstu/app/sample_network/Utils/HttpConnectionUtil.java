package cn.edu.zstu.app.sample_network.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lenovo on 2015/12/8.
 */
public class HttpConnectionUtil {
    public static String doGetURLConnection(String strUrl) throws Exception {
        URL url=new URL(strUrl);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        InputStream inputStream=httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader reader=new BufferedReader(inputStreamReader);
        String line=null;
        StringBuffer buffer=new StringBuffer();
        while ((line=reader.readLine())!=null){
            buffer.append(line);
        }
        reader.close();
        inputStreamReader.close();
        inputStream.close();

        return buffer.toString();
    }

    /**
     *
     * @param strUrl
     * @param param wd=URLConnection
     * @return
     * @throws Exception
     */
    public static String doPostURLConnection(String strUrl,String param) throws Exception {
        URL url=new URL(strUrl);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestMethod("POST");
        OutputStream outputStream=httpURLConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
        outputStreamWriter.write(param);
        outputStreamWriter.flush();

        InputStream inputStream = httpURLConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String tempLine=null;
        StringBuffer resultBuffer=new StringBuffer();
        while ((tempLine = reader.readLine()) != null) {
            resultBuffer.append(tempLine);
        }
        reader.close();
        inputStreamReader.close();
        inputStream.close();
        outputStreamWriter.close();
        outputStream.close();
        return resultBuffer.toString();
    }



    public static void main(String[] args){

        try {
            String str= HttpConnectionUtil.doGetURLConnection("http://www.baidu.com");
            System.out.println(str);
            str= HttpConnectionUtil.doPostURLConnection("http://www.baidu.com", "wd=URLConnection");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
