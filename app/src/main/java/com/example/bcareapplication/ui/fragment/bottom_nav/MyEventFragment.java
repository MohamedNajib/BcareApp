package com.example.bcareapplication.ui.fragment.bottom_nav;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bcareapplication.R;
import com.example.fontutil.TextViewCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventFragment extends Fragment {


    // bar Icon
    @BindView(R.id.IV_MyEventNavIcon)
    ImageView IVMyEventNavIcon;
    @BindView(R.id.IV_MyEventNotificationIcon)
    ImageView IVMyEventNotificationIcon;

    // RecycleView
    @BindView(R.id.RV_MyEventCurrentDates)
    RecyclerView RVMyEventCurrentDates;
    @BindView(R.id.RV_MyEventLastDates)
    RecyclerView RVMyEventLastDates;

    // Button Container
    @BindView(R.id.CL_BTN_Container)
    ConstraintLayout CLBTNContainer;
    Unbinder unbinder;

    public MyEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_event, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_MyEventNewBooking, R.id.BTN_MyEventNearbySalon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_MyEventNewBooking:
                break;

            case R.id.BTN_MyEventNearbySalon:
                break;
        }
    }
}
