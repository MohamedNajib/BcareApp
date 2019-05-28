package com.example.bcareapplication.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bcareapplication.R;

public class UserCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);


        startActivity(new Intent(this, BottomNavActivity.class));

        //HelperMethod.replaceFragments(new ProfileFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
    }


}
