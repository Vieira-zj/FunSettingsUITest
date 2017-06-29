package com.example.zhengjin.funsettingsuitest.testuitasks;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import com.example.zhengjin.funsettingsuitest.testuiactions.UiActionsManager;
import com.example.zhengjin.funsettingsuitest.testuiobjects.UiObjectsNetworkConfigs;
import com.example.zhengjin.funsettingsuitest.testutils.ShellUtils;
import com.example.zhengjin.funsettingsuitest.testutils.TestConstants;

import java.util.List;

/**
 * Created by zhengjin on 2017/6/29.
 * <p>
 * Include general tasks for network configs.
 */
public final class TaskNetworkConfigs {

    private static TaskNetworkConfigs instance = null;

    private UiDevice device;
    private UiActionsManager action;
    private UiObjectsNetworkConfigs funUiObjects;

    private TaskNetworkConfigs() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        action = UiActionsManager.getInstance();
        funUiObjects = UiObjectsNetworkConfigs.getInstance();
    }

    public static TaskNetworkConfigs getInstance() {
        if (instance == null) {
            synchronized (TaskNetworkConfigs.class) {
                if (instance == null) {
                    instance = new TaskNetworkConfigs();
                }
            }
        }
        return instance;
    }

    public static synchronized void destroyInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    private final String DEFAULT_NULL_IP = "0.0.0.0";

    public String getIpAddressFromSystemProperties() {
        return this.getPropValueFromSystemProperties("dhcp.eth0.ipaddress");
    }

    public String getGatewayIpFromSystemProperties() {
        return this.getPropValueFromSystemProperties("dhcp.eth0.gateway");
    }

    private String getPropValueFromSystemProperties(String propName) {
        final String CMD = "getprop | grep " + propName;
        ShellUtils.CommandResult cr = ShellUtils.execCommand(CMD, false, true);
        return cr.mReturnCode == 0 ?
                this.getValueFromPropertyKeyValuePair(cr.mSuccessMsg) : DEFAULT_NULL_IP;
    }

    private String getValueFromPropertyKeyValuePair(String results) {
        String propValue = results.split(":")[1].trim();
        return propValue.substring(1, propValue.length() - 1);
    }

    public String getDnsIpAddressFromSystemConfigs() {
        final String CMD = "cat /etc/resolv.conf";
        ShellUtils.CommandResult cr = ShellUtils.execCommand(CMD, false, true);
        return cr.mReturnCode == 0 ? cr.mSuccessMsg.split(" ")[1].trim() : DEFAULT_NULL_IP;
    }

    public List<UiObject2> getWifiHotSpotsList() {
        UiObject2 container = device.findObject(
                funUiObjects.getContainerOfWifiHotSpotsListSelector());
        return container.findObjects(By.clazz(TestConstants.CLASS_RELATIVE_LAYOUT).depth(1));
    }

}
