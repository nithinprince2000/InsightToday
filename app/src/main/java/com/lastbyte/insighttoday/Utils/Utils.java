package com.lastbyte.insighttoday.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Date;

public class Utils {

    private static String[] dayNames = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static String[] monthNames = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @SuppressLint("DefaultLocale")
    public static String getDateAndDay() {
        long timestamp = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int month = calendar.get(Calendar.MONTH);

        return String.format("%d %s, %s", calendar.get(Calendar.DAY_OF_MONTH), monthNames[month],  dayNames[dayOfWeek - 1]);
    }
}
