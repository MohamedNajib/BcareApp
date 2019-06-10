package com.example.bcareapplication.ui.fragment.user_cycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bcareapplication.R;
import com.example.fontutil.EditTextCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPassword1 extends Fragment {


    @BindView(R.id.ET_ForgotPassEmail)
    EditTextCustomFont ETForgotPassEmail;
    Unbinder unbinder;

    public ForgotPassword1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_Restore_password_btn, R.id.TV_ForgotPass_LoginLink_, R.id.TV_ForgotPass_CreateAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_Restore_password_btn:
                break;

            case R.id.TV_ForgotPass_LoginLink_:
                break;

            case R.id.TV_ForgotPass_CreateAccountLink:
                break;
        }
    }
}
