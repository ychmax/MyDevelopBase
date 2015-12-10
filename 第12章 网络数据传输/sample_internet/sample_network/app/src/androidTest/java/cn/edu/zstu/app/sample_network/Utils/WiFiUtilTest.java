package cn.edu.zstu.app.sample_network.Utils;

import android.net.wifi.ScanResult;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cn.edu.zstu.app.sample_network.MainActivity;


/**
 * Created by lenovo on 2015/12/4.
 */
public class WiFiUtilTest extends ActivityInstrumentationTestCase2<MainActivity> {

    WiFiUtil wiFiUtil;
    private MainActivity mActivity;

    public WiFiUtilTest() {
        super( MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        mActivity=getActivity();
        wiFiUtil=new WiFiUtil(mActivity);
        wiFiUtil.openWifi();
        wiFiUtil.startScan();
    }


    @Test
    public void testGetScanResultList() throws Exception {
        List<ScanResult>list=wiFiUtil.getScanResultList();
        assertNotNull(list);
    }

    @Test
    public void testlookUpScan() throws Exception {
       StringBuilder str=wiFiUtil.lookUpScan();
        assertNotNull(str);
    }

    public void testGetInfo(){
        String str=wiFiUtil.getWifiInfo();
        System.out.println(str);
        assertNotNull(str);
    }

}