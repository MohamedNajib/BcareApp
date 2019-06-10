package com.example.bcareapplication.adapter.fragments_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bcareapplication.R;
import com.example.bcareapplication.data.model.api_model.specialists.SpecialistsData;

import java.util.List;

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

    }

    @Override
    public int getItemCount() {
        return mSpecialistsData.size();
    }

    public class SpecialistsHolder extends RecyclerView.ViewHolder{

        public SpecialistsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
