
package com.kienle.monxaovn.util;

import com.kienle.monxaovn.CustomApplication;


public class StringUtils {

    public static String getString(int resourceId) {
        return CustomApplication.getAppContext().getResources().getString(resourceId);
    }
}
