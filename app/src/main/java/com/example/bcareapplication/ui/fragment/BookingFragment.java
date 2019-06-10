package com.example.bcareapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bcareapplication.R;
import com.example.bcareapplication.adapter.DatesRecyclerViewAdapter;
import com.example.bcareapplication.data.model.DateTimePickerModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    private List<DateTimePickerModel> dataModelList;
    private DatesRecyclerViewAdapter adapter;


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        unbinder = ButterKnife.bind(this, view);
        addData();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

       adapter = new DatesRecyclerViewAdapter(dataModelList, getContext(), new DatesRecyclerViewAdapter.ItemListener() {
           @Override
           public void onItemClick(DateTimePickerModel model) {
               for (int i = 0; i < dataModelList.size(); i++) {

                   DateTimePickerModel current = dataModelList.get(i);
                   dataModelList.get(i).highlghted = model.day.equals(current.day) && model.date.equals(current.date);
               }
               adapter.notifyDataSetChanged();
           }
       });
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void addData(){
        dataModelList = new ArrayList<>();
        Calendar c = Calendar.getInstance();

        for (int i = 1; i < 31; i++) {
            c.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = c.getTime();

            long timestamp = tomorrow.getTime();
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(timestamp);

            DateTimePickerModel dataModel = new DateTimePickerModel();
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY: dataModel.day = "Su";
                    break;
                case Calendar.MONDAY: dataModel.day = "Mo";
                    break;
                case Calendar.TUESDAY: dataModel.day = "Tu";
                    break;
                case Calendar.WEDNESDAY: dataModel.day = "We";
                    break;
                case Calendar.THURSDAY: dataModel.day = "Th";
                    break;
                case Calendar.FRIDAY: dataModel.day = "Fr";
                    break;
                case Calendar.SATURDAY: dataModel.day = "Sa";
                    break;

            }

            dataModel.date = String.format("%02d", (cal.get(Calendar.DATE)));
            dataModel.month = String.format("%02d", (cal.get(Calendar.MONTH)));
            dataModel.year = String.valueOf(cal.get(Calendar.YEAR));
            dataModel.highlghted = i == 1;

            dataModelList.add(dataModel);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
