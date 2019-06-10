package com.test.beatoapp.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class CommanUtils {



    public static long getCurrentTimeStemp(){
       return System.currentTimeMillis();
    }

    public static String calculateBMI(float Height, float Weight){
//        (16.9 kg / 105.4 cm / 105.4 cm ) x 10,000 = 15.2
        float bmi = (Weight/Height/Height)*10000;
       return String.format("%.2f", bmi) ;
    }

    public static void showToastOnUi(final Context context, final String msg){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
