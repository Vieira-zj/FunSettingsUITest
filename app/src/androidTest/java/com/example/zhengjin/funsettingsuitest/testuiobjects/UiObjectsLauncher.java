package com.example.zhengjin.funsettingsuitest.testuiobjects;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;

/**
 * Created by zhengjin on 2017/4/21.
 * <p>
 * Include UI objects for launcher home page.
 */

public class UiObjectsLauncher {

    private static UiObjectsLauncher ourInstance = new UiObjectsLauncher();

    public static UiObjectsLauncher getInstance() {
        return ourInstance;
    }

    private UiObjectsLauncher() {
    }

    public void destroyInstance() {
        if (ourInstance != null) {
            ourInstance = null;
        }
    }

    public BySelector getAllLauncherTabsSelector() {
        return By.res("com.bestv.ott:id/tab_title");
    }

    public BySelector getLauncherTopBarSelector() {
        return By.res("com.bestv.ott:id/container");
    }

    public BySelector getQuickAccessBtnSettingsSelector() {
        return By.res("com.bestv.ott:id/setting");
    }

    public BySelector getQuickAccessBtnWeatherSelector() {
        return By.res("com.bestv.ott:id/weather");
    }

    @SuppressWarnings("unused")
    public BySelector getQuickAccessBtnNetworkSelector() {
        return By.res("com.bestv.ott:id/network");
    }

    public BySelector getSettingsEntrySelector() {
        return By.res("com.bestv.ott:id/setting_entry");
    }

    public BySelector getLoadingCircleSelector() {
        return By.res("com.bestv.ott:id/progressBar");
    }

}
