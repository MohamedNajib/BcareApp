package com.example.bcareapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

public class HelperMethod {

    /**
     * Helper Method
     * to Replace Fragments
     *
     * @param fragment
     * @param supportFragmentManager
     * @param id
     */
    public static void replaceFragments(Fragment fragment, FragmentManager supportFragmentManager, int id) {
        supportFragmentManager.beginTransaction()
                .replace(id, fragment)
                .addToBackStack(null)
                .commit();
    }
}
