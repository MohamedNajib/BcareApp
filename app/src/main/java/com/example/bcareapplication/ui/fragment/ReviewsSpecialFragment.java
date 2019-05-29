package com.example.bcareapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bcareapplication.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsSpecialFragment extends Fragment {


    @BindView(R.id.IV_ReviewsPackIcon)
    ImageView IVReviewsPackIcon;
    Unbinder unbinder;

    public ReviewsSpecialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reviews_special, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVReviewsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));

        } else {
            IVReviewsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
