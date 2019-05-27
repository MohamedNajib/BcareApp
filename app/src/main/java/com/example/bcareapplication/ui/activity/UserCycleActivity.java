package com.example.bcareapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bcareapplication.HelperMethod;
import com.example.bcareapplication.R;
import com.example.bcareapplication.ui.fragment.ProfileFragment;
import com.example.bcareapplication.ui.fragment.user_cycle.ForgotPassword3;

public class UserCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);

        HelperMethod.replaceFragments(new ProfileFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
    }


}
