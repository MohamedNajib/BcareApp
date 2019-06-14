package com.example.bcareapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.bcareapplication.R;
import com.example.bcareapplication.adapter.fragments_adapter.CountrySpinnerAdapter;
import com.example.bcareapplication.adapter.fragments_adapter.SectionsAdapterA;
import com.example.bcareapplication.data.model.api_model.countries.CountriesData;
import com.example.bcareapplication.data.model.api_model.countries.RegisterCountries;
import com.example.bcareapplication.data.model.api_model.service.Service;
import com.example.bcareapplication.data.model.api_model.service.Service_;
import com.example.bcareapplication.data.rest.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bcareapplication.Constants.FragmentsKeys.REQUEST_STATUS_OK;
import static com.example.bcareapplication.util.HelperMethod.showToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment {

    @BindView(R.id.RV_Sections)
    RecyclerView RVSections;
    Unbinder unbinder;
    @BindView(R.id.SpinnerCountrySections)
    Spinner SpinnerCountrySections;
    @BindView(R.id.IV_SectionsPackIcon)
    ImageView IVSectionsPackIcon;

    /* member variable */
    private LinearLayoutManager mLayoutManager;
    private SectionsAdapterA mSectionsAdapter;

    //var
    private List<Service_> mSalonDataList;

    public SectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            IVSectionsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));
        } else {
            IVSectionsPackIcon.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        mSalonDataList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(getContext());
        RVSections.setLayoutManager(mLayoutManager);
        RVSections.setHasFixedSize(true);
        RVSections.setItemAnimator(new DefaultItemAnimator());

        getCountrySpinnerData("ar");
        getService("Dcfilf27URGHSoLjMScVtJVgcNd6J1aSRoDjpGrorCGeKSBMYLyc6Z9H0RWp",
                "ar",
                1);
        return view;
    }

    /**
     * get Service Use API Call
     */
    private void getService(String apiToken, String lang, int salontype_id) {
        Call<Service> serviceCall = RetrofitClient.getInstance().getApiServices().getServices(
                apiToken, lang, salontype_id
        );

        serviceCall.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "OK");
                        mSalonDataList = response.body().getData().getServices();
                        mSectionsAdapter = new SectionsAdapterA(getContext(),
                                mSalonDataList,
                                getCategoryWithoutMatch(getAllCatNames(mSalonDataList)));
                        RVSections.setAdapter(mSectionsAdapter);

                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {

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
    private List<String> getAllCatNames(List<Service_> sectionsList) {
        List<String> stringCatName = new ArrayList<>();
        for (int i = 0; i < sectionsList.size(); i++) {
            stringCatName.add(sectionsList.get(i).getCatName());
        }
        return stringCatName;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Get Spinner List Country Use API Call
     */
    private void getCountrySpinnerData(String lang) {
        Call<RegisterCountries> registerCountriesCall = RetrofitClient.getInstance().getApiServices().getCountriesList(lang);

        registerCountriesCall.enqueue(new Callback<RegisterCountries>() {
            @Override
            public void onResponse(Call<RegisterCountries> call, Response<RegisterCountries> response) {
                try {
                    if (response.body().getCode().equals(String.valueOf(REQUEST_STATUS_OK))) {
                        showToast(getContext(), "OK");
                        CountrySpinnerAdapter countrySpinnerAdapter =
                                new CountrySpinnerAdapter(getContext(), response.body().getData());

                        SpinnerCountrySections.setAdapter(countrySpinnerAdapter);
                        SpinnerCountrySections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CountriesData countriesData = (CountriesData) parent.getSelectedItem();
                                showToast(getContext(), countriesData.getCountryName());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        showToast(getContext(), "NO");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegisterCountries> call, Throwable t) {

            }
        });
    }
}
