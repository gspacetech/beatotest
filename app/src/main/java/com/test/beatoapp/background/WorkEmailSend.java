package com.test.beatoapp.background;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.beatoapp.models.TestUserModel;
import com.test.beatoapp.storage.database.DatabaseManager;
import com.test.beatoapp.utils.CommanUtils;
import com.test.beatoapp.utils.Config;
import com.test.beatoapp.utils.Mail;

import java.util.List;

import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class WorkEmailSend extends Worker {


    public WorkEmailSend(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String emailBody = getEmailBody(getApplicationContext());
        if(emailBody.equalsIgnoreCase("")){
            CommanUtils.showToastOnUi(getApplicationContext(), "No last 15 min records");
            return Result.success();
        }else{
            Mail m = new Mail(Config.SENDER_EMAIL_ID, Config.SENDER_EMAIL_PASSWORD);
            String[] toArr = {Config.RECEIVER_EMAIL_ID};
            m.setTo(toArr);
            m.setFrom(Config.SENDER_EMAIL_ID);
            m.setSubject(Config.EMAIL_SUB);
            m.setBody(emailBody);
            try {
                if(m.send()) {
                    CommanUtils.showToastOnUi(getApplicationContext(), "Sent Email.");
                    return Result.success();
                } else {
                    CommanUtils.showToastOnUi(getApplicationContext(),"Email was not sent.");
                    return Result.retry();
                }
            } catch(Exception e) {
                Log.e("email exception", "Could not send email.", e);
                return Result.retry();
            }
        }

    }


    public static String getEmailBody(Context context){

        String emailBody = "";
        for (TestUserModel testUserModel: getLast15MinRecords(context)) {
            emailBody = emailBody+"" +
                    "<br>" +
                    " Name : "+testUserModel.getName()+
                    ", Phone : "+testUserModel.getPhone()+
                    ", Email id : "+testUserModel.getEmail()+
                    ", Gender : "+testUserModel.getGender()+
                    ", Year of birth : "+testUserModel.getYob()+
                    ", Height : "+testUserModel.getHeight()+
                    ", Weight : "+testUserModel.getWeight()+
                    ", Your Body Mass index is : "+ testUserModel.getBmi()+
                    ", Reading : "+ testUserModel.getReading()+
                    ", Reading_type : "+ testUserModel.getReadingType()+
                    ", Created : "+ testUserModel.getCreated()+
                    ", Created_by : "+ testUserModel.getCreated_by()+"<br>";
            ;
        }

        return emailBody;
    }

    private static List<TestUserModel> getLast15MinRecords(Context context){
        return DatabaseManager.getInstance(context).getUserTestTable().getLast15MinRecords();
    }
}
