package com.log515.lambda.wherework.utils;

import org.joda.time.DateTime;

/**
 * Created by ttauveron on 10/11/17.
 */

public class Utils {

    public static String getCurrentSession() {
        DateTime now = DateTime.now();
        String codeSession = "";
        switch (now.getMonthOfYear()) {
            case 1:
            case 2:
            case 3:
            case 4:
                codeSession = "H";
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                codeSession = "É";
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                codeSession = "A";
                break;
        }
        codeSession += now.getYear();
        return codeSession;
    }
}
