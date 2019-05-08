package com.magicfrost.knowledgecomb.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Created by MagicFrost.
 */
public class Utils {

    public static String getSSIDByNetWorkId(Context context){
        String ssid = "";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null){
            WifiInfo info = wifiManager.getConnectionInfo();
            int networkId = info.getNetworkId();
            List<WifiConfiguration> netConfigList = wifiManager.getConfiguredNetworks();
            for (WifiConfiguration configuration:netConfigList){
                Log.e("dsdsd",""+configuration.networkId);
                if (configuration.networkId == networkId){
                    ssid = configuration.SSID;
                }
            }
        }
        return ssid;
    }
}
