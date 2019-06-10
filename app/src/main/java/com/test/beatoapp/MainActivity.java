package com.test.beatoapp;

import android.os.Bundle;

import com.facebook.stetho.Stetho;
import com.test.beatoapp.base_components.BaseActivity;
import com.test.beatoapp.pages.FragmentLogin;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        addFragment(new FragmentLogin(),
                FragmentLogin.FRAGMENT_TAG, false);
    }
}
