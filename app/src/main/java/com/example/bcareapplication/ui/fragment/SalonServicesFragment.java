package com.example.bcareapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.bcareapplication.R;
import com.example.bcareapplication.adapter.fragments_adapter.SalonServicesAdapterA;
import com.example.bcareapplication.data.model.api_model.checkCopoun.CheckCopoun;
import com.example.bcareapplication.data.model.api_model.checkCopoun.WithoutCopoun;
import com.example.bcareapplication.data.model.api_model.salon_services.SalonServices;
import com.example.bcareapplication.data.model.api_model.salon_services.SalonServicesData;
import com.example.bcareapplication.data.rest.RetrofitClient;
import com.example.bcareapplication.util.HelperMethod;
import com.example.fontutil.EditTextCustomFont;
import com.example.fontutil.TextViewCustomFont;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bcareapplication.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.example.bcareapplication.util.HelperMethod.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalonServicesFragment extends Fragment {


    @BindView(R.id.IV_SalonServicesPackIcon)
    ImageView IVSalonServicesPackIcon;
    Unbinder unbinder;
    @BindView(R.id.SpinnerCountrySalonServices)
    Spinner SpinnerCountrySalonServices;
    @BindView(R.id.RV_SalonServices)
    RecyclerView RVSalonServices;
    @BindView(R.id.TV_SalonServicesPrice)
    TextViewCustomFont TVSalonServicesPrice;
    @BindView(R.id.ET_DiscountCodeSalonServices)
    EditTextCustomFont ETDiscountCodeSalonServices;


    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SalonServicesAdapterA mSalonServicesAdapterA;

    // var
    private List<SalonServicesData> mSalonServicesDataList;

    public SalonServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_salon_services, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVSalonServicesPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVSalonServicesPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mSalonServicesDataList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSalonServices.setLayoutManager(mLayoutManager);
        RVSalonServices.setHasFixedSize(true);
        RVSalonServices.setItemAnimator(new DefaultItemAnimator());

        getSalonServicesAPI("Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                "ar",
                1);

        return view;
    }

    /**
     * get Salon Services API Call
     */
    private void getSalonServicesAPI(String token, String lang, int country_id) {
        Call<SalonServices> salonServicesCall = RetrofitClient.getInstance().getApiServices().getSalonServices(token, lang, country_id);
        salonServicesCall.enqueue(new Callback<SalonServices>() {
            @Override
            public void onResponse(Call<SalonServices> call, Response<SalonServices> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "OK");
                        mSalonServicesDataList = response.body().getData();
                        mSalonServicesAdapterA = new SalonServicesAdapterA(getContext(),
                                mSalonServicesDataList,
                                getCategoryWithoutMatch(getAllCatNames(mSalonServicesDataList)), TVSalonServicesPrice);
                        RVSalonServices.setAdapter(mSalonServicesAdapterA);

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SalonServices> call, Throwable t) {

            }
        });
    }

    /**
     * get Category Without match
     */
    private List<String> getCategoryWithoutMatch(List<String> cName) {
        List<String> catNames = new ArrayList<>();
        Set<String> unique = new HashSet<>(cName);
        for (String key : unique) {
            catNames.addAll(Collections.singleton(key));
        }
        return catNames;
    }

    /**
     * get String List Of Category Name
     */
    private List<String> getAllCatNames(List<SalonServicesData> mSalonServicesDataList) {
        List<String> stringCatName = new ArrayList<>();
        for (int i = 0; i < mSalonServicesDataList.size(); i++) {
            stringCatName.add(mSalonServicesDataList.get(i).getCatName());
        }
        return stringCatName;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.CL_Continue)
    public void onViewClicked() {
        discountCopounCode("Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                "ar", ETDiscountCodeSalonServices.getText().toString().trim());
    }

    private void discountCopounCode(String apiToken, String lang, String code) {

        if (code.equals("")){
            Call<WithoutCopoun> withoutCopounCall = RetrofitClient.getInstance().getApiServices().withoutCopoun(
                    apiToken, lang, code
            );
            withoutCopounCall.enqueue(new Callback<WithoutCopoun>() {
                @Override
                public void onResponse(Call<WithoutCopoun> call, Response<WithoutCopoun> response) {
                    try {
                        if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                            showToast(getContext(), "code: " + response.body().getData());

                            HelperMethod.replaceFragments(new ConfirmationFragment(),
                                    getActivity().getSupportFragmentManager(), R.id.FragmentContainer);
                        } else {
                            showToast(getContext(), "NO");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<WithoutCopoun> call, Throwable t) {

                }
            });
        }else {
            Call<CheckCopoun> checkCopounCall = RetrofitClient.getInstance().getApiServices().checkCopoun(
                    apiToken, lang, code
            );
            checkCopounCall.enqueue(new Callback<CheckCopoun>() {
                @Override
                public void onResponse(Call<CheckCopoun> call, Response<CheckCopoun> response) {
                    try {
                        if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {

                            showToast(getContext(), "code: " + response.body().getData().getCode());

                            //showToast(getContext(), "OK" + response.body().getData().getCode());
                            HelperMethod.replaceFragments(new ConfirmationFragment(),
                                    getActivity().getSupportFragmentManager(), R.id.FragmentContainer);
                        } else {
                            showToast(getContext(), "NO");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<CheckCopoun> call, Throwable t) {
                    showToast(getContext(), "onFailure" + t.getMessage());
                }
            });
        }
    }


}
