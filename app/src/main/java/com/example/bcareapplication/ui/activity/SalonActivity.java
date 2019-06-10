package com.example.bcareapplication.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.bcareapplication.R;
import com.example.bcareapplication.adapter.ViewPagerTapsAdapter;
import com.example.bcareapplication.ui.fragment.salon_taps.AboutTapFragment;
import com.example.bcareapplication.ui.fragment.salon_taps.RatingsTapFragment;
import com.example.bcareapplication.ui.fragment.salon_taps.ServicesTapFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalonActivity extends AppCompatActivity {

    @BindView(R.id.SalonTabLayout)
    TabLayout SalonTabLayout;
    @BindView(R.id.SalonViewPager)
    ViewPager SalonViewPager;
    @BindView(R.id.IV_SalonPackIcon)
    ImageView IVSalonPackIcon;
    @BindView(R.id.IV_SalonShareIcon)
    ImageView IVSalonShareIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        ButterKnife.bind(this);

        /* Set ViewPager */
        setupClientViewPager(SalonViewPager);
        SalonTabLayout.setupWithViewPager(SalonViewPager);


        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVSalonPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
            IVSalonShareIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));

        } else {
            IVSalonPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVSalonShareIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        }

    }



    /* Add Fragments to Tabs */
    private void setupClientViewPager(ViewPager viewPager) {
        ViewPagerTapsAdapter adapter = new ViewPagerTapsAdapter(getSupportFragmentManager());
        adapter.addFragment(new AboutTapFragment(), "حول الصالون");
        adapter.addFragment(new RatingsTapFragment(), "خدمات");
        adapter.addFragment(new ServicesTapFragment(), "تقييمات");

        viewPager.setAdapter(adapter);
    }
}
