package com.test.beatoapp.pages;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.beatoapp.R;
import com.test.beatoapp.background.BackgroundTaskManager;
import com.test.beatoapp.base_components.BaseFragment;
import com.test.beatoapp.storage.pref.PrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends BaseFragment {

    public static String FRAGMENT_TAG = "FragmentLogin";
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        send15minRecords();
    }

    private void initViews(){
        setSubmitBtn();
    }

    private void setSubmitBtn(){
        Button btnSubmit = (Button)view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });
    }

    private EditText getUserNameField(){
        EditText edtUserName = (EditText)view.findViewById(R.id.edtUserName);
        return edtUserName;
    }

    private EditText getPasswordField(){
        EditText edtpassword = (EditText)view.findViewById(R.id.edtPassword);
        return edtpassword;
    }

    private void validateLogin(){
        if(! isValidUserName()){
            getUserNameField().setError("User Name can not be empty");
        }else if(! isValidPassWord()){
            getPasswordField().setError("Password can not be empty");
        }else{
            saveUserNameToPref();
            navigateToRegistration();
        }
    }

    private boolean isValidUserName(){
        return ! getUserNameField().getText().toString().isEmpty();
    }

    private boolean isValidPassWord(){
        return ! getPasswordField().getText().toString().isEmpty();
    }

    private void navigateToRegistration(){
        addFragment(new FragmentRegistration(), FragmentRegistration.FRAGMENT_TAG, false);
    }

    private void saveUserNameToPref(){
        PrefManager.getInstance().setPrefUsername(getUserNameField().getText().toString());
    }

    private void send15minRecords(){
        BackgroundTaskManager.scheduleWorkSendLast15MinTest(getContext());
    }

}
