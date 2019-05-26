package com.example.bcareapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperMethod.replaceFragments(new LoginFragment(), getSupportFragmentManager(), R.id.FragmentContainer);

    }
}
