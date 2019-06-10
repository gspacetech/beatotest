package com.test.beatoapp.pages;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test.beatoapp.R;
import com.test.beatoapp.base_components.BaseFragment;
import com.test.beatoapp.models.TestUserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReadingResult extends BaseFragment {

    public static String FRAGMENT_TAG = "FragmentReadingResult";
    private View view;
    private static FragmentReadingResult instance;
    private TestUserModel testUserModel;
    private static int IS_NORMAL = 1;
    private static int IS_LOW = 2;
    private static int IS_HIGH = 3;

    public FragmentReadingResult() {
    }

    public static FragmentReadingResult getInstance() {
        if (instance == null) {
            instance = new FragmentReadingResult();
        }
        return instance;
    }

    public void setData(TestUserModel testUserModel){
        this.testUserModel = testUserModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reading_result, container, false);
        initViews();
        showResult();
        showBmi();
        return view;
    }

    private void initViews(){
        setBtnClose();
    }

    private void setBtnClose(){
        Button btnClose = (Button)view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private TextView getReadingTextView(){
        return (TextView)view.findViewById(R.id.tvReadingResult);
    }

    private TextView getBmiTextView(){
        return (TextView)view.findViewById(R.id.tvBmi);
    }

    private void showResult(){
        getReadingTextView().setText(testUserModel.getReading());
        if(getReadingIndicator(testUserModel) == IS_LOW){
            getReadingTextView().setTextColor(Color.parseColor("#ffeb3b"));
        }else if(getReadingIndicator(testUserModel) == IS_HIGH){
            getReadingTextView().setTextColor(Color.parseColor("#ff3d00"));
        }else{
            getReadingTextView().setTextColor(Color.parseColor("#76ff03"));
        }
    }

    private void showBmi(){
        getBmiTextView().setText(testUserModel.getBmi());
    }


    private int getReadingIndicator(TestUserModel testUserModel){

        int reading = Integer.parseInt(testUserModel.getReading());
        if(isPreMeal(testUserModel)){
            if(reading < 70){
                // low
                return IS_LOW;
            }else if(reading > 130){
                // high
                return IS_HIGH;
            }else{
                return IS_NORMAL;
                // normal
            }
        }else{
            if(reading < 80){
                // low
                return IS_LOW;
            }else if(reading > 180){
                // high
                return IS_HIGH;
            }else{
                // normal
                return IS_NORMAL;
            }
        }

    }

    private boolean isPreMeal(TestUserModel testUserModel){
        String readingType = testUserModel.getReadingType();
        if(readingType.equalsIgnoreCase("Fasting") ||
                readingType.equalsIgnoreCase("Pre Lunch")||
                readingType.equalsIgnoreCase("Pre Dinner")){
            return true;
        }else{
            return false;
        }
    }

}
