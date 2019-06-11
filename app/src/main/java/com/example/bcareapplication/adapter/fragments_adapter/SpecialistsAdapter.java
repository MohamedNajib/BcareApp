package com.example.bcareapplication.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.bcareapplication.R;
import com.example.bcareapplication.data.model.api_model.specialists.SpecialistsData;
import com.example.bcareapplication.ui.custom.RoundRectCornerImageView;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialistsAdapter extends RecyclerView.Adapter<SpecialistsAdapter.SpecialistsHolder> {

    private Context mContext;
    private List<SpecialistsData> mSpecialistsData;

    public SpecialistsAdapter(Context mContext, List<SpecialistsData> mSpecialistsData) {
        this.mContext = mContext;
        this.mSpecialistsData = mSpecialistsData;
    }

    @NonNull
    @Override
    public SpecialistsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SpecialistsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_specialists_a, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialistsHolder selectSalonHolder, int i) {
        SpecialistsData specialistsData = mSpecialistsData.get(i);

        Glide.with(mContext).load(specialistsData.getImage()).into(selectSalonHolder.IVSpecialistImage);

        selectSalonHolder.TVSpecialistName.setText(specialistsData.getName());
        selectSalonHolder.TVSpecialistAddress.setText(specialistsData.getAddress());

        if (specialistsData.getSpecialistRate() == null){
            selectSalonHolder.RATSpecialistRate.setRating(0);
        }else {
            selectSalonHolder.RATSpecialistRate.setRating(Float.parseFloat(specialistsData.getSpecialistRate()));
        }

    }

    @Override
    public int getItemCount() {
        return mSpecialistsData.size();
    }

    public class SpecialistsHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.IV_SpecialistImage)
        RoundRectCornerImageView IVSpecialistImage;
        @BindView(R.id.TV_SpecialistName)
        TextViewCustomFont TVSpecialistName;
        @BindView(R.id.TV_SpecialistAddress)
        TextViewCustomFont TVSpecialistAddress;
        @BindView(R.id.RAT_SpecialistRate)
        RatingBar RATSpecialistRate;
        @BindView(R.id.BTN_SpecialistBooking)
        ButtonCustomFont BTNSpecialistBooking;

        private final View view;

        public SpecialistsHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
