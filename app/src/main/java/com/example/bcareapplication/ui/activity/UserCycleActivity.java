package com.example.bcareapplication.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bcareapplication.ui.fragment.SectionsFragment;
import com.example.bcareapplication.ui.fragment.user_cycle.ForgotPassword1;
import com.example.bcareapplication.ui.fragment.user_cycle.ForgotPassword2;
import com.example.bcareapplication.ui.fragment.user_cycle.ForgotPassword3;
import com.example.bcareapplication.ui.fragment.user_cycle.LoginFragment1;
import com.example.bcareapplication.ui.fragment.user_cycle.LoginFragment2;
import com.example.bcareapplication.ui.fragment.user_cycle.SignUpFragment;
import com.example.bcareapplication.ui.fragment.user_cycle.SignUpFragment1;
import com.example.bcareapplication.util.HelperMethod;
import com.example.bcareapplication.R;
import com.example.bcareapplication.ui.fragment.SelectSalonFragment;

public class UserCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);


        //startActivity(new Intent(this, BottomNavActivity.class));
        //startActivity(new Intent(this, SalonActivity.class));
        //startActivity(new Intent(this, BookingActivity.class));


        HelperMethod.replaceFragments(new SelectSalonFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
    }


}
