package com.example.bcareapplication.ui.fragment;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bcareapplication.R;
import com.example.fontutil.ButtonCustomFont;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmationFragment extends Fragment {


    @BindView(R.id.imageView4)
    ImageView imageView4;
    Unbinder unbinder;

    public ConfirmationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (Locale.getDefault().getLanguage().equals("ar")) {
            imageView4.setRotationY(getResources().getInteger(R.integer.Image_Locale_RTL_Mood));

        } else {
            imageView4.setRotationY(getResources().getInteger(R.integer.Image_locale_LTR_Mood));
        }

        return view;
    }


    /**
     * Dialog to Show if the Request is Successful
     */
    public void showDialog() {
        final Dialog dialog = new Dialog(getContext());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmatoin);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.dialog)));
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        final ButtonCustomFont dialogConfirmationPtn = dialog.findViewById(R.id.BTN_DialogConfirmation);
        dialogConfirmationPtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Dialog Confirmation Button Event Listener
                Toast.makeText(getContext(), "God", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.BTN_ConfirmationRequest)
    public void onViewClicked() {

        showDialog();
    }
}
