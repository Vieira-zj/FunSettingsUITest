package com.example.zhengjin.funsettingsuitest.testuiactions;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;

import com.example.zhengjin.funsettingsuitest.testutils.ShellUtils;

import static com.example.zhengjin.funsettingsuitest.testutils.TestConstants.SHORT_WAIT;

/**
 * Created by zhengjin on 2016/6/2.
 *
 * Include the UI actions invoked by test tasks and test cases.
 */
public final class UiActionsManager {

    private static UiActionsManager instance = null;
    private UiDevice mDevice = null;

    private UiActionsManager() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    public static synchronized UiActionsManager getInstance() {
        if (instance == null) {
            instance = new UiActionsManager();
        }

        return instance;
    }

    public boolean doDeviceActionAndWait(DeviceAction action, long wait) {
        boolean ret = action.doDeviceAction(mDevice);
        ShellUtils.systemWaitByMillis(wait);
        return ret;
    }

    // default wait time is 1 second
    public boolean doDeviceActionAndWait(DeviceAction action) {
        return doDeviceActionAndWait(action, SHORT_WAIT);
    }

    public boolean doDeviceActionAndWaitForIdle(DeviceAction action, long wait) {
        boolean ret = action.doDeviceAction(mDevice);

        if (wait > 0) {
            mDevice.waitForIdle(wait);
        } else {
            mDevice.waitForIdle();
        }
        return ret;
    }

    public boolean doDeviceActionAndWaitForIdle(DeviceAction action) {
        long defaultWait = 0;
        return doDeviceActionAndWaitForIdle(action, defaultWait);
    }

    public void doRepeatDeviceActionAndWait(DeviceAction action, int repeatTimes, long wait) {
        for (int i = 0; i < repeatTimes; ++i) {
            action.doDeviceAction(mDevice);
            ShellUtils.systemWaitByMillis(wait);
        }
    }

    public void doRepeatDeviceActionAndWait(DeviceAction action, int repeatTimes) {
        doRepeatDeviceActionAndWait(action, repeatTimes, SHORT_WAIT);
    }

    public UiActionsManager doMultipleDeviceActionAndWait(DeviceAction action, long wait) {
        action.doDeviceAction(mDevice);
        ShellUtils.systemWaitByMillis(wait);
        return this;
    }

    public UiActionsManager doMultipleDeviceActionAndWait(DeviceAction action) {
        return doMultipleDeviceActionAndWait(action, SHORT_WAIT);
    }

    public void doClickActionAndWait(UiObject2 uiObj) {
        uiObj.click();
        ShellUtils.systemWaitByMillis(SHORT_WAIT);
    }

}
