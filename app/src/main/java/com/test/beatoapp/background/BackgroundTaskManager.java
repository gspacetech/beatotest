package com.test.beatoapp.background;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class BackgroundTaskManager {



    public static void scheduleWorkSendUserTestEmail(long test_id){

        Data data = new Data.Builder()
                .putLong(WorkEmailSendToUser.KEY_TEST_ID, test_id)
                .build();

        Constraints.Builder constraintsBuilder = new Constraints.Builder();
        constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED);
        Constraints constraints = constraintsBuilder.build();

        final OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(WorkEmailSendToUser.class)
                        .setInputData(data)
                        .setConstraints(constraints)
                        .build();
        WorkManager.getInstance().enqueue(workRequest);
    }

    public static void scheduleWorkSendLast15MinTest(Context context){

        Constraints.Builder constraintsBuilder = new Constraints.Builder();
        constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED);
        Constraints constraints = constraintsBuilder.build();

        final OneTimeWorkRequest workRequest =
                new OneTimeWorkRequest.Builder(WorkEmailSend.class)
                        .setConstraints(constraints)
                        .build();
        WorkManager.getInstance().enqueue(workRequest);


//        startForgroundForSendingEmail(context);
    }


    public  static void startForgroundForSendingEmail(Context context){
//        Intent i=new Intent(context, ForgroundEmailSend.class);
//
////        i.setDataAndType(Uri.parse("https://commonsware.com/Android/Android-1_0-CC.pdf"),
////                "application/pdf");
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForstartForeground(i);
//        }else{
//            context.startService(i);
//        }


//        ForgroundEmailSend mYourService = new ForgroundEmailSend();
//        Intent mServiceIntent = new Intent(context, mYourService.getClass());
////        if (!isMyServiceRunning(mYourService.getClass())) {
//            context.startService(mServiceIntent);
////        }
    }

}
