package com.test.beatoapp.base_components;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.test.beatoapp.R;

public class BaseActivity extends AppCompatActivity {

    final FragmentManager fragmentManager = getSupportFragmentManager();
    public void addFragment(@NonNull Fragment fragment,
                               @NonNull String fragmentTag, boolean isAddToBackStack) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenttest, fragment,fragmentTag);
        if(isAddToBackStack){
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 )
        {
            int lastBackStackIndex = getSupportFragmentManager().getBackStackEntryCount()-1;
            String fragmentName = getSupportFragmentManager().getBackStackEntryAt(lastBackStackIndex).getName();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentName);
            addFragment(fragment, fragmentName,false);
        }
        super.onBackPressed();
    }

}