package com.test.beatoapp.pages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.test.beatoapp.R;
import com.test.beatoapp.background.BackgroundTaskManager;
import com.test.beatoapp.base_components.BaseFragment;
import com.test.beatoapp.models.TestUserModel;
import com.test.beatoapp.storage.database.DatabaseManager;
import com.test.beatoapp.storage.database.UserTestTable;
import com.test.beatoapp.storage.pref.PrefManager;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTestInput extends BaseFragment {

    public static String FRAGMENT_TAG = "FragmentTestInput";
    private static String TAG = "test user";
    private View view;
    private static FragmentTestInput instance;
    private TestUserModel testUserModel;
    private int reading = 0;
    private String readingType;

    public static FragmentTestInput getInstance() {
        if (instance == null) {
            instance = new FragmentTestInput();
        }
        return instance;
    }

    public void setData(TestUserModel testUserModel){
        this.testUserModel = testUserModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test_input, container, false);
        initView();

        return view;
    }

    private void initView(){
        setSeekBar();
        setDropdown();
        setBtnSaveTest();
    }

    private void setDropdown(){
        AppCompatSpinner spinner = (AppCompatSpinner)view.findViewById(R.id.spnReadingType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.reading_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("spinnser", String.valueOf(parent.getAdapter().getItem(position)));
                readingType = String.valueOf(parent.getAdapter().getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSeekBar(){

        IndicatorSeekBar seekBar = (IndicatorSeekBar)view.findViewById(R.id.seekbar);
        seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.i(TAG, String.valueOf(seekParams.progress));
                reading = seekParams.progress;
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
            }
        });
    }

    private void setBtnSaveTest(){
        Button btnSaveTest = (Button)view.findViewById(R.id.btnSaveTest);
        btnSaveTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUserData();
            }
        });
    }

    private void validateUserData(){
        if(! isValidReading()){
            showToast("Scroll to select your Reading level");
        }else if(! isValidReadingType()){
            showToast("Select your Reading Type");
        }else {
            long lastInsertedId = saveToDb();
            BackgroundTaskManager.scheduleWorkSendUserTestEmail(lastInsertedId);
            navigateToResultPage();
        }
    }

    private boolean isValidReading(){
        if(reading > 30){
            return true;
        }else{
            return false;
        }
    }

    private boolean isValidReadingType() {
        //Hardcoded
//        readingType = "Fasting";
        if (readingType.equalsIgnoreCase("Choose Reading Type") || readingType.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void navigateToResultPage(){
        FragmentReadingResult fragmentReadingResult = FragmentReadingResult.getInstance();
        fragmentReadingResult.setData(testUserModel);
        addFragment(fragmentReadingResult, FragmentReadingResult.FRAGMENT_TAG, false);
    }

    private void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    private long saveToDb(){
        testUserModel.setReading(String.valueOf(reading));
        testUserModel.setReadingType(readingType);

        Log.d(TAG, testUserModel.getName());
        Log.d(TAG, String.valueOf(testUserModel.getPhone()));
        Log.d(TAG, testUserModel.getEmail());
        Log.d(TAG, testUserModel.getGender());
        Log.d(TAG, String.valueOf(testUserModel.getYob()));
        Log.d(TAG, testUserModel.getHeight());
        Log.d(TAG, testUserModel.getWeight());
        Log.d(TAG, testUserModel.getBmi());
        Log.d(TAG, testUserModel.getReading());
        Log.d(TAG, testUserModel.getReadingType());

        Log.d(TAG, PrefManager.getInstance().getPrefUsername());

        UserTestTable userTestTable = DatabaseManager.getInstance(getContext()).getUserTestTable();
        long lastInsertedId = userTestTable.insert(testUserModel);
        return lastInsertedId;
    }

}
