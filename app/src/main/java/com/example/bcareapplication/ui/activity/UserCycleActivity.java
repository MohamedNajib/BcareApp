package com.example.bcareapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bcareapplication.ui.fragment.ConfirmationFragment;
import com.example.bcareapplication.ui.fragment.ReviewsSpecialFragment;
import com.example.bcareapplication.ui.fragment.SalonServicesFragment;
import com.example.bcareapplication.ui.fragment.SelectSalonFragment;
import com.example.bcareapplication.ui.fragment.SpecialistsFragment;
import com.example.bcareapplication.ui.fragment.user_cycle.LoginFragment1;
import com.example.bcareapplication.util.HelperMethod;
import com.example.bcareapplication.R;

import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_ADDRESS;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_GROUP;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_IMAGE;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_NAME;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_RATE;

public class UserCycleActivity extends AppCompatActivity implements ReviewsSpecialFragment.CommunicationSpecialists {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);


        //startActivity(new Intent(this, BottomNavActivity.class));
        //startActivity(new Intent(this, SalonActivity.class));
        //startActivity(new Intent(this, BookingActivity.class));
        //HelperMethod.replaceFragments(new ConfirmationFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
        //HelperMethod.replaceFragments(new SpecialistsFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
        HelperMethod.replaceFragments(new SalonServicesFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
        //HelperMethod.replaceFragments(new SpecialistsFragment(), getSupportFragmentManager(), R.id.FragmentContainer);
    }


    @Override
    public void getSpecializedData(String name, String image, String specialistGroup, String specialistRate) {
        ReviewsSpecialFragment reviewsSpecialFragment = new ReviewsSpecialFragment();

        Bundle bundle = new Bundle();
        bundle.putString(SPECIALIZED_NAME, name);
        bundle.putString(SPECIALIZED_IMAGE, image);
        bundle.putString(SPECIALIZED_GROUP, specialistGroup);
        bundle.putString(SPECIALIZED_RATE, specialistRate);

        reviewsSpecialFragment.setArguments(bundle);
        HelperMethod.replaceFragments(reviewsSpecialFragment, getSupportFragmentManager(), R.id.FragmentContainer);
    }
}
