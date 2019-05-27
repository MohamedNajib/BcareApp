package com.example.bcareapplication.ui.fragment.user_cycle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.bcareapplication.R;
import com.example.fontutil.EditTextCustomFont;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    @BindView(R.id.Spinner_SignUpPhone_)
    Spinner SpinnerSignUpPhone;
    @BindView(R.id.ET_SignUpPhone_)
    EditTextCustomFont ETSignUpPhone;
    Unbinder unbinder;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.BTN_SignUp_, R.id.TV_SignUP_HaveAccountLink})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BTN_SignUp_:
                break;
            case R.id.TV_SignUP_HaveAccountLink:
                break;
        }
    }
}
