package com.example.bcareapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bcareapplication.R;
import com.github.glomadrian.codeinputlib.CodeInput;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment3 extends Fragment {


    Unbinder unbinder;

    private CodeInput cInput;

    public ForgotPasswordFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password3, container, false);
        unbinder = ButterKnife.bind(this, view);

        cInput = view.findViewById(R.id.CodeInput_ForgotPass);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * get Confirm String Code From CodeInput View
     */
    private String getStringCode(Character[] characterCode) {
        StringBuilder stringBuilder = new StringBuilder(characterCode.length);
        for (Character i : characterCode) {
            stringBuilder.append(i);
        }
        String stringCood = stringBuilder.toString();
        return stringCood;
    }

    @OnClick(R.id.BTN_ForgotPassConfirmPass)
    public void onViewClicked() {
        Toast.makeText(getContext(), "code: " + getStringCode(cInput.getCode()), Toast.LENGTH_SHORT).show();
    }
}
