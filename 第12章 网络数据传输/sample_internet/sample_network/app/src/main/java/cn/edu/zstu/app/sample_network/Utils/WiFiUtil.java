package cn.edu.zstu.app.sample_network.Utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by lenovo on 2015/12/4.
 */
public class WiFiUtil {
    private WifiManager wifiManager = null;
    private WifiInfo wifiInfo = null;
    private List<ScanResult> scanResultList = null;// 扫描出的网络连接列表
    private List<WifiConfiguration> wifiConfigurationList = null;// 网络连接列表
    private WifiManager.WifiLock wifiLock = null;

    // 构造函数
    public WiFiUtil(Context context) {
        wifiManager = (WifiManager) context
                .getSystemService(context.WIFI_SERVICE);// 从系统服务中得到WifiManager
        // 得到WiFi相关信息，包括MAX地址、接入点BSSID、IP地址、连接的ID
        wifiInfo = wifiManager.getConnectionInfo();
    }

    // 打开WiFi
    public void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    // 关闭WiFi
    public void closeWife() {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    // 锁定WiFi
    public void lockWifi() {
        wifiLock.acquire();
    }

    // 解锁WiFi
    public void rlockWifi() {
        if (wifiLock.isHeld()) {
            wifiLock.acquire();
        }
    }

    // 创建一个wifilock
    public void createWifiLock() {
        wifiLock = wifiManager.createWifiLock("Machao");
    }

    public List<WifiConfiguration> getConfingurationList() {
        return wifiConfigurationList;// 得到配置好的网络
    }

    public void connectConfiguration(int index) {
        if (index > wifiConfigurationList.size()) {
            return;
        }
        wifiManager.enableNetwork(wifiConfigurationList.get(index).networkId,
                true);// 连接配置好的指定ID的网络
    }

    public void startScan() {
        wifiManager.startScan();
        // 得到扫描结果
        scanResultList = wifiManager.getScanResults();
        // 得到配置好的网络连接
        wifiConfigurationList = wifiManager.getConfiguredNetworks();
    }

    // 得到网络列表
    public List<ScanResult> getScanResultList() {
        return scanResultList;
    }

    // 查看扫描结果
    public StringBuilder lookUpScan() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < scanResultList.size(); i++) {
            stringBuilder
                    .append("Index_" + new Integer(i + 1).toString() + ":");
            // 将ScanResult信息转换成一个字符串包
            // 其中包括：BSSID、SSID、capabilities、frequency、level
            stringBuilder.append((scanResultList.get(i)).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    // 得到MAC地址
    public String getMacAddress() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.getMacAddress();
    }

    // 得到接入点的BSSID
    public String getBSSID() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.getBSSID();
    }

    // 得到IP地址
    public int getIPAddress() {
        return (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
    }

    // 得到连接的ID
    public int getNetworkId() {
        return (wifiInfo == null) ? 0 : wifiInfo.getNetworkId();
    }

    // 得到WifiInfo的所有信息包
    public String getWifiInfo() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.toString();
    }

    // 添加一个网络并连接
    public void addNetwork(WifiConfiguration wcg) {
        int wcgID = wifiManager.addNetwork(wcg);
        wifiManager.enableNetwork(wcgID, true);
    }

    // 断开指定ID的网络
    public void disconnectWifi(int netId) {
        wifiManager.disableNetwork(netId);
        wifiManager.disconnect();
    }
}