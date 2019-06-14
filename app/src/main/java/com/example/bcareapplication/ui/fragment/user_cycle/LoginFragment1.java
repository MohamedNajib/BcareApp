package com.example.bcareapplication.ui.fragment.user_cycle;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bcareapplication.R;
import com.example.bcareapplication.util.HelperMethod;
import com.example.fontutil.EditTextCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment1 extends Fragment implements View.OnFocusChangeListener {

    Unbinder unbinder;
    @BindView(R.id.Spinner_LoginPhone)
    Spinner SpinnerLoginPhone;
    @BindView(R.id.ET_LoginPhone)
    EditTextCustomFont ETLoginPhone;
    @BindView(R.id.layout_phoneNumber)
    LinearLayout layoutPhoneNumber;
    @BindView(R.id.CL_OR)
    ConstraintLayout CLOR;
    @BindView(R.id.ET_LoginEmail)
    EditTextCustomFont ETLoginEmail;
    @BindView(R.id.ET_LoginPassword)
    EditTextCustomFont ETLoginPassword;
    @BindView(R.id.IV_Close_)
    ImageView IVClose;


    public LoginFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login1, container, false);
        unbinder = ButterKnife.bind(this, view);


        ETLoginEmail.setOnFocusChangeListener(this);
        ETLoginPhone.setOnFocusChangeListener(this);
        ETLoginPassword.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.IV_Close_)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.IV_Close_:
                IVClose.setVisibility(View.GONE);
                CLOR.setVisibility(View.VISIBLE);
                ETLoginEmail.setVisibility(View.VISIBLE);
                layoutPhoneNumber.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.ET_LoginPhone:
                if (hasFocus) {
                    IVClose.setVisibility(View.VISIBLE);
                    CLOR.setVisibility(View.GONE);
                    ETLoginEmail.setVisibility(View.GONE);
                }
                break;

            case R.id.ET_LoginEmail:
                if (hasFocus) {
                    IVClose.setVisibility(View.VISIBLE);
                    CLOR.setVisibility(View.GONE);
                    layoutPhoneNumber.setVisibility(View.GONE);
                }
                break;

            case R.id.ET_LoginPassword:
                if (hasFocus) {

                }
                break;
        }
    }
}
