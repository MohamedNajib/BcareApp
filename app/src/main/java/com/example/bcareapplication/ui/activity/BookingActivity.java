package com.example.bcareapplication.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bcareapplication.R;
import com.example.bcareapplication.adapter.DatesRecyclerViewAdapter;
import com.example.bcareapplication.adapter.ViewPagerTapsAdapter;
import com.example.bcareapplication.data.model.DateTimePickerModel;
import com.example.bcareapplication.ui.fragment.booking_taps.AtHomeBookingTap;
import com.example.bcareapplication.ui.fragment.booking_taps.InTheShopBookingTap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.RecyclerViewDate)
    RecyclerView RecyclerViewDate;

    @BindView(R.id.BookingTabLayout)
    TabLayout BookingTabLayout;
    @BindView(R.id.BookingViewPager)
    ViewPager BookingViewPager;


    private List<DateTimePickerModel> mDataModelList;
    private DatesRecyclerViewAdapter mDatesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        /* Set ViewPager */
        setupClientViewPager(BookingViewPager);
        BookingTabLayout.setupWithViewPager(BookingViewPager);

        Calendar c = Calendar.getInstance();
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        String month = monthName[c.get(Calendar.MONTH)];

        int year = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DATE);

        StringBuilder builder = new StringBuilder();
        for (String details : monthName) {
            builder.append(details + "\n");
        }

        TextView textView = findViewById(R.id.date);
        textView.setText(" " + date + " " + month + " /" + "" + year + "\n" + builder);

//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
//        String month_name = month_date.format(cal.getTime());

        addData();

        RecyclerViewDate.setHasFixedSize(true);
        RecyclerViewDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDatesRecyclerViewAdapter = new DatesRecyclerViewAdapter(mDataModelList, this, new DatesRecyclerViewAdapter.ItemListener() {
            @Override
            public void onItemClick(DateTimePickerModel model) {
                for (int i = 0; i < mDataModelList.size(); i++) {

                    DateTimePickerModel current = mDataModelList.get(i);
                    mDataModelList.get(i).highlghted = model.day.equals(current.day) && model.date.equals(current.date);
                }
                mDatesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        RecyclerViewDate.setAdapter(mDatesRecyclerViewAdapter);
    }

    /* Add Fragments to Tabs */
    private void setupClientViewPager(ViewPager viewPager) {
        ViewPagerTapsAdapter adapter = new ViewPagerTapsAdapter(getSupportFragmentManager());
        adapter.addFragment(new InTheShopBookingTap(), "في المحل");
        adapter.addFragment(new AtHomeBookingTap(), "في المنزل");

        viewPager.setAdapter(adapter);
    }

    private void addData() {
        mDataModelList = new ArrayList<>();
        Calendar c = Calendar.getInstance();

        for (int i = 1; i < 31; i++) {
            c.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = c.getTime();

            long timestamp = tomorrow.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);

            DateTimePickerModel dataModel = new DateTimePickerModel();
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    dataModel.day = "Su";
                    break;
                case Calendar.MONDAY:
                    dataModel.day = "Mo";
                    break;
                case Calendar.TUESDAY:
                    dataModel.day = "Tu";
                    break;
                case Calendar.WEDNESDAY:
                    dataModel.day = "We";
                    break;
                case Calendar.THURSDAY:
                    dataModel.day = "Th";
                    break;
                case Calendar.FRIDAY:
                    dataModel.day = "Fr";
                    break;
                case Calendar.SATURDAY:
                    dataModel.day = "Sa";
                    break;
            }

            dataModel.date = String.format("%02d", (cal.get(Calendar.DATE)));
            dataModel.month = String.format("%02d", (cal.get(Calendar.MONTH)));
            dataModel.year = String.valueOf(cal.get(Calendar.YEAR));
            dataModel.highlghted = i == 1;

            mDataModelList.add(dataModel);
        }

    }
}
