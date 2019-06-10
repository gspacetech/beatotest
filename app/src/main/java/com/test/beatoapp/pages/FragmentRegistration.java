package com.test.beatoapp.pages;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.beatoapp.R;
import com.test.beatoapp.base_components.BaseFragment;
import com.test.beatoapp.models.TestUserModel;
import com.test.beatoapp.utils.CommanUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistration extends BaseFragment {

    public static String FRAGMENT_TAG = "FragmentRegistration";
    private View view;

    public FragmentRegistration() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registration, container, false);
        initViews();
        return view;
    }

    private void initViews(){
        setSubmitBtn();
    }

    private void setSubmitBtn(){
        Button btnTakeTest = (Button)view.findViewById(R.id.btnTakeTest);
        btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    private EditText getNameField(){
        EditText edtName = (EditText)view.findViewById(R.id.edtName);
        return edtName;
    }

    private EditText getPhoneField(){
        EditText edtPhone = (EditText)view.findViewById(R.id.edtPhone);
        return edtPhone;
    }

    private EditText getEmailField(){
        EditText edtEmail = (EditText)view.findViewById(R.id.edtEmail);
        return edtEmail;
    }

    private EditText getGenderField(){
        EditText edtGender = (EditText)view.findViewById(R.id.edtGender);
        return edtGender;
    }

    private EditText getYobField(){
        EditText edtYob = (EditText)view.findViewById(R.id.edtYob);
        return edtYob;
    }

    private EditText getHeightField(){
        EditText edtHeight = (EditText)view.findViewById(R.id.edtHeight);
        return edtHeight;
    }

    private EditText getWeightField(){
        EditText edtWeight = (EditText)view.findViewById(R.id.edtWeight);
        return edtWeight;
    }

    private boolean isValidName(){
        return ! getNameField().getText().toString().isEmpty();
    }

    private boolean isValidPhone(){
        String phone = getPhoneField().getText().toString();
        return (! phone.isEmpty() && Patterns.PHONE.matcher(phone).matches());
    }

    private boolean isValidEmail(){
        String email = getEmailField().getText().toString();
        return (! email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidGender(){
        return ! getGenderField().getText().toString().isEmpty();
    }

    private boolean isValidYob(){
        return ! getYobField().getText().toString().isEmpty();
    }

    private boolean isValidHeigh(){
        return ! getHeightField().getText().toString().isEmpty();
    }

    private boolean isValidWeight(){
        return ! getWeightField().getText().toString().isEmpty();
    }

    private void validateForm(){
        if(! isValidName()){
            getNameField().setError("Please enter valid user name");
        }else if(! isValidPhone()){
            getPhoneField().setError("Please enter valid phone number");
        } else if(! isValidEmail()){
            getEmailField().setError("Please enter valid email");
        }else if(! isValidGender()){
            getGenderField().setError("Please enter valid gender");
        }else if(! isValidYob()){
            getYobField().setError("Please enter valid year of birth");
        }else if(! isValidHeigh()){
            getHeightField().setError("Please enter valid height");
        }else if(! isValidWeight()){
            getWeightField().setError("Please enter valid weight");
        }else {
            navigateToTestPage();
        }
    }

    private TestUserModel getTestUserModel(){
        TestUserModel testUser = new TestUserModel();
        testUser.setName(getNameField().getText().toString());
        testUser.setPhone(Long.parseLong(getPhoneField().getText().toString()));
        testUser.setEmail(getEmailField().getText().toString());
        testUser.setGender(getGenderField().getText().toString());
        testUser.setYob(Integer.parseInt(getYobField().getText().toString()));
        testUser.setHeight(getHeightField().getText().toString());
        testUser.setWeight(getWeightField().getText().toString());
        testUser.setBmi(getBmi(testUser));
        return testUser;
    }

    private String getBmi(TestUserModel testUserModel){
        return CommanUtils
                .calculateBMI(Float.parseFloat(testUserModel.getHeight()),
                        Float.parseFloat(testUserModel.getWeight()));
    }

    private void navigateToTestPage(){
        FragmentTestInput fragmentTestInput = FragmentTestInput.getInstance();
        fragmentTestInput.setData(getTestUserModel());
        addFragment(fragmentTestInput, FragmentTestInput.FRAGMENT_TAG, true);
    }

}
