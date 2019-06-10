package com.test.beatoapp.base_components;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {



    protected void addFragment(@NonNull Fragment fragment,
                               @NonNull String fragmentTag, boolean isAddToBackStack) {

        BaseActivity baseActivity = (BaseActivity)getActivity();
        baseActivity.addFragment(fragment, fragmentTag, isAddToBackStack);

    }

}
