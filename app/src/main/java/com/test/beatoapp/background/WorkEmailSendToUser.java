package com.test.beatoapp.background;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.beatoapp.models.TestUserModel;
import com.test.beatoapp.storage.database.DatabaseManager;
import com.test.beatoapp.utils.CommanUtils;
import com.test.beatoapp.utils.Config;
import com.test.beatoapp.utils.Mail;

import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class WorkEmailSendToUser extends Worker {

    public static String KEY_TEST_ID = "test_id";

    public WorkEmailSendToUser(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public Result doWork() {

        int test_id = (int)getInputData().getLong(KEY_TEST_ID, 0);
        TestUserModel testUserModel = DatabaseManager.getInstance(getApplicationContext())
                .getUserTestTable().getUserTestData(test_id);

        String emailBody =
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

        Log.d("WorkEmailSendToUser", "doWork: "+emailBody);

        Mail m = new Mail(Config.SENDER_EMAIL_ID, Config.SENDER_EMAIL_PASSWORD);

        String[] toArr = {testUserModel.getEmail()};
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
