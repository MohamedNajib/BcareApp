package com.example.bcareapplication.ui.fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bcareapplication.R;
import com.example.bcareapplication.adapter.fragments_adapter.SelectSalonAdapter;
import com.example.bcareapplication.data.locle.SharedPrefManager;
import com.example.bcareapplication.data.model.api_model.favorite.Favorite;
import com.example.bcareapplication.data.model.api_model.salons.SalonData;
import com.example.bcareapplication.data.model.api_model.salons.Salons;
import com.example.bcareapplication.data.rest.RetrofitClient;
import com.example.bcareapplication.util.MyLocationProvider;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bcareapplication.Constants.FragmentsKeys.MY_PERMISSIONS_REQUEST_ACCESS_GPS;
import static com.example.bcareapplication.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.example.bcareapplication.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectSalonFragment extends Fragment {

    @BindView(R.id.RV_SelectSalon)
    RecyclerView RVSelectSalon;
    Unbinder unbinder;
    @BindView(R.id.IV_SelectSalonPackIcon)
    ImageView IVSelectSalonPackIcon;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SelectSalonAdapter mSelectSalonAdapter;
    private MyLocationProvider locationProvider;
    private Location location;

    //var
    private List<SalonData> mSalonDataList;
    private double mLatitude;
    private double mLongitude;

    public SelectSalonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_salon, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVSelectSalonPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVSelectSalonPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        /* Check If The Permission is Granted or Not */
        if (isGPSPermissionAllowed()) {
            initializeGPS();
        } else {
            requestLocationPermission();
        }

        mSalonDataList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSelectSalon.setLayoutManager(mLayoutManager);
        RVSelectSalon.setHasFixedSize(true);
        RVSelectSalon.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    /**
     * Get List Of Salon API Call
     */
    private void getSalonAPICall(String toke, String lang, int category_id, String user_latitude, String user_longitude, String orderBy) {
        Call<Salons> salonCall = RetrofitClient.getInstance()
                .getApiServices().getSalons(toke, lang, category_id, user_latitude, user_longitude
                        , orderBy);

        salonCall.enqueue(new Callback<Salons>() {
            @Override
            public void onResponse(Call<Salons> call, Response<Salons> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        //showToast(getContext(), "OK");
                        mSalonDataList = response.body().getData();

                        mSelectSalonAdapter = new SelectSalonAdapter(getContext(), mSalonDataList, new SelectSalonAdapter.OnClickItem() {
                            @Override
                            public void onBookingClicked(int position) {
                                showToast(getContext(), "  Booking Clicked");
                            }

                            @Override
                            public void onShareClicked(int position) {
                                //showToast(getContext(), "Share Clicked");

                                Intent share = new Intent(Intent.ACTION_SEND);
                                share.setType("text/plain");
                                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                share.putExtra(Intent.EXTRA_SUBJECT, "ShareSalon");
                                share.putExtra(Intent.EXTRA_TEXT, mSalonDataList.get(position).getShareLink());
                                startActivity(Intent.createChooser(share, "Share Salon"));
                            }

                            boolean mStatus = false;

                            @Override
                            public void onLikeClicked(int position, ImageView IV_LikeSalon) {
                                //showToast(getContext(), "Like Clicked");

                                if (!mStatus) {
                                    mStatus = true;
                                    IV_LikeSalon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_group_liked));
                                    addSalonToFavorite(
                                            "Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                                            mSalonDataList.get(position).getId(),
                                            1);

                                } else {
                                    mStatus = false;
                                    IV_LikeSalon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_group_like));
                                    addSalonToFavorite(
                                            "Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                                            mSalonDataList.get(position).getId(),
                                            0);
                                }

                            }
                        });
                        RVSelectSalon.setAdapter(mSelectSalonAdapter);

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Salons> call, Throwable t) {
                showToast(getContext(), "onFailure" + t.getMessage());
            }
        });
    }

    /**
     * add Salon To Favorite Use API Call
     */
    private void addSalonToFavorite(String apiToken, int salonId, int toggle) {
        Call<Favorite> favoriteCall = RetrofitClient.getInstance().getApiServices().addFavorite(apiToken, salonId, toggle);

        favoriteCall.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), response.body().getData());

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void callApiMethod(String orderPy) {
        getSalonAPICall(
                "Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                "ar",
                1,
                String.valueOf(mLatitude),
                String.valueOf(mLongitude),
                orderPy);

    }


    @OnClick({R.id.IV_SelectSalonPackIcon, R.id.BTN_Filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_SelectSalonPackIcon:
                break;
            case R.id.BTN_Filter:
                showPopUpFilter();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_GPS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    initializeGPS();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getContext(), "permission denied App cannot access GPS", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    /**
     * Request Location Permission From User
     */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                    .setTitle("Warning")
                    .setMessage("")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_GPS);
                        }
                    }).show();

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_GPS);
        }
    }

    /**
     * User agree to access GPS Permission
     * Initialize GPS
     */
    public void initializeGPS() {
        Toast.makeText(getContext(), "GPS ALLOWED", Toast.LENGTH_SHORT).show();
        locationProvider = new MyLocationProvider(getContext());
        location = locationProvider.getBestLastknownLocation();
        if (location == null) {
            Toast.makeText(getContext(), "Cannot get your Location", Toast.LENGTH_SHORT).show();

        } else {
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();

            callApiMethod("nearest");
        }
    }

    /**
     * Check If The Permission is Granted or Not
     */
    public boolean isGPSPermissionAllowed() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    /**
     * Dialog to Show if the Request is Successful
     */
    public void showPopUpFilter() {
        final Dialog dialog = new Dialog(getContext());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dialog)));
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        ConstraintLayout CLPOP = dialog.findViewById(R.id.CLPOP);

        final TextViewCustomFont TV_POPNear = dialog.findViewById(R.id.TV_POPNear);
        final TextViewCustomFont TV_POPTopRated = dialog.findViewById(R.id.TV_POPTopRated);
        final TextViewCustomFont TV_POPBestPrice = dialog.findViewById(R.id.TV_POPBestPrice);
        final TextViewCustomFont TV_POPFavorites = dialog.findViewById(R.id.TV_POPFavorites);

        final ImageView IV_POPNear = dialog.findViewById(R.id.IV_POPNear);
        final ImageView IV_POPTopRated = dialog.findViewById(R.id.IV_POPTopRated);
        final ImageView IV_POPBestPrice = dialog.findViewById(R.id.IV_POPBestPrice);
        final ImageView IV_POPFavorites = dialog.findViewById(R.id.IV_POPFavorites);


        switch (SharedPrefManager.getInstance(getContext()).getPopUpState()) {
            case "nearest":
                IV_POPNear.setVisibility(View.VISIBLE);
                IV_POPTopRated.setVisibility(View.GONE);
                IV_POPBestPrice.setVisibility(View.GONE);
                IV_POPFavorites.setVisibility(View.GONE);
                break;
            case "rate":
                IV_POPTopRated.setVisibility(View.VISIBLE);
                IV_POPNear.setVisibility(View.GONE);
                IV_POPBestPrice.setVisibility(View.GONE);
                IV_POPFavorites.setVisibility(View.GONE);
                break;
            case "price":
                IV_POPBestPrice.setVisibility(View.VISIBLE);
                IV_POPTopRated.setVisibility(View.GONE);
                IV_POPNear.setVisibility(View.GONE);
                IV_POPFavorites.setVisibility(View.GONE);
                break;
            case "favorite":
                IV_POPFavorites.setVisibility(View.VISIBLE);
                IV_POPBestPrice.setVisibility(View.GONE);
                IV_POPTopRated.setVisibility(View.GONE);
                IV_POPNear.setVisibility(View.GONE);
                break;
        }

        TV_POPNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).setPopUpState("nearest");
                IV_POPNear.setVisibility(View.VISIBLE);
                IV_POPTopRated.setVisibility(View.GONE);
                IV_POPBestPrice.setVisibility(View.GONE);
                IV_POPFavorites.setVisibility(View.GONE);
                callApiMethod(SharedPrefManager.getInstance(getContext()).getPopUpState());
                dialog.dismiss();
            }
        });

        TV_POPTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).setPopUpState("rate");
                IV_POPTopRated.setVisibility(View.VISIBLE);
                IV_POPNear.setVisibility(View.GONE);
                IV_POPBestPrice.setVisibility(View.GONE);
                IV_POPFavorites.setVisibility(View.GONE);
                callApiMethod(SharedPrefManager.getInstance(getContext()).getPopUpState());
                dialog.dismiss();
            }
        });
        TV_POPBestPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).setPopUpState("price");
                IV_POPBestPrice.setVisibility(View.VISIBLE);
                IV_POPTopRated.setVisibility(View.GONE);
                IV_POPNear.setVisibility(View.GONE);
                IV_POPFavorites.setVisibility(View.GONE);
                callApiMethod(SharedPrefManager.getInstance(getContext()).getPopUpState());
                dialog.dismiss();
            }
        });
        TV_POPFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).setPopUpState("favorite");
                IV_POPFavorites.setVisibility(View.VISIBLE);
                IV_POPBestPrice.setVisibility(View.GONE);
                IV_POPTopRated.setVisibility(View.GONE);
                IV_POPNear.setVisibility(View.GONE);
                callApiMethod(SharedPrefManager.getInstance(getContext()).getPopUpState());

                dialog.dismiss();
            }
        });

        Animation mFromBottom;
        mFromBottom = AnimationUtils.loadAnimation(getContext(), R.anim.frombottom);
        CLPOP.setAnimation(mFromBottom);
        dialog.show();

        dialog.getWindow().setAttributes(layoutParams);
    }

}
