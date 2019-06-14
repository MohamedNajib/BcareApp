package com.example.bcareapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.bcareapplication.R;
import com.example.bcareapplication.ui.custom.RoundRectCornerImageView;
import com.example.fontutil.ButtonCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_GROUP;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_IMAGE;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_NAME;
import static com.example.bcareapplication.Constants.FragmentsKeys.SPECIALIZED_RATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsSpecialFragment extends Fragment {


    @BindView(R.id.IV_ReviewsPackIcon)
    ImageView IVReviewsPackIcon;
    Unbinder unbinder;

    @BindView(R.id.TV_ReviewsSpecialName)
    TextViewCustomFont TVReviewsSpecialName;
    @BindView(R.id.IV_ReviewsSpecialImage)
    RoundRectCornerImageView IVReviewsSpecialImage;
    @BindView(R.id.TV_ReviewsSpecialNameA)
    TextViewCustomFont TVReviewsSpecialNameA;
    @BindView(R.id.TV_ReviewsSpecialSpecialty)
    TextViewCustomFont TVReviewsSpecialSpecialty;
    @BindView(R.id.RatingBar_ReviewsSpecial)
    RatingBar RatingBarReviewsSpecial;
    @BindView(R.id.BTN_ServiceRequest)
    ButtonCustomFont BTNServiceRequest;
    @BindView(R.id.IV_ButtonMore)
    ImageView IVButtonMore;

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
            IVButtonMore.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVReviewsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
            IVButtonMore.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        /* Get Food Details */
        Bundle bundle = getArguments();

        Glide.with(getContext()).load(bundle.getString(SPECIALIZED_IMAGE)).into(IVReviewsSpecialImage);
        TVReviewsSpecialName.setText(bundle.getString(SPECIALIZED_NAME));
        TVReviewsSpecialNameA.setText(bundle.getString(SPECIALIZED_NAME));
        TVReviewsSpecialSpecialty.setText(bundle.getString(SPECIALIZED_GROUP));

        if (bundle.getString(SPECIALIZED_RATE) == null) {
            RatingBarReviewsSpecial.setRating(0);
        } else {
            RatingBarReviewsSpecial.setRating(Float.parseFloat(bundle.getString(SPECIALIZED_RATE)));
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface CommunicationSpecialists {
        void getSpecializedData(String name,
                                String image,
                                String specialistGroup,
                                String specialistRate);
    }
}
