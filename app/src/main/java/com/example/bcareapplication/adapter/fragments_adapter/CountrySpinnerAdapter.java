package com.example.bcareapplication.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bcareapplication.R;
import com.example.bcareapplication.data.model.api_model.countries.CountriesData;

import java.util.List;
import java.util.Objects;

public class CountrySpinnerAdapter extends ArrayAdapter<CountriesData> {


    public CountrySpinnerAdapter(@NonNull Context context, List<CountriesData> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cusstom_spinner_layout, parent, false);
        }
        CountriesData item = getItem(position);

        TextView spinnerTV = convertView.findViewById(R.id.tvSpinnerLayout);
        if (item != null) {
            spinnerTV.setText(item.getCountryName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout, parent, false);
        }
        CountriesData item = getItem(position);

        TextView dropDownTV = convertView.findViewById(R.id.tvDropDownLayout);
        if (item != null) {
            dropDownTV.setText(item.getCountryName());

            final int sdk = android.os.Build.VERSION.SDK_INT;

            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                dropDownTV.invalidateDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getContext(), R.drawable.spenn_b)));

            } else {
                dropDownTV.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.spenn_b));
            }
        }
        return convertView;
    }
}

