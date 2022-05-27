package com.example.zun.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.zun.MainActivity;
import com.example.zun.R;
import com.example.zun.adapter.GenderSpinnerAdapter;
import com.example.zun.domain.Theme;
import com.example.zun.fakedb.NoDb;
import com.example.zun.rest.ZunApiImpl;


public class ChangeThemeFragment extends Fragment {
private EditText etThemeName;
private AppCompatSpinner sp_gender;

    private boolean checkEmpty() {
        boolean problem = false;

        if (TextUtils.isEmpty(etThemeName.getText().toString())) {
            etThemeName.setError("Обязательное поле");
            problem = true;
        }

        return problem;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_theme, container, false);
        etThemeName = view.findViewById(R.id.et_themeName);
        Theme theme = (Theme) (getArguments().getSerializable(MainActivity.MSG_NAME));
        etThemeName.setText(theme.getName());
        sp_gender = view.findViewById(R.id.sp_gender);
        GenderSpinnerAdapter genderSpinnerAdapter =
                new GenderSpinnerAdapter(getActivity(),
                        R.layout.spinner_item,
                        NoDb.GENDER_LIST);
        sp_gender.setAdapter(genderSpinnerAdapter);

        AppCompatButton btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkEmpty()) {


                    new ZunApiImpl(getActivity())
                            .updateTheme(
                                    theme.getId(),
                                    etThemeName.getText().toString(),null,null
                            );


                    getActivity().getSupportFragmentManager().beginTransaction().remove(ChangeThemeFragment.this).commit();

                }
            }
        });

        return view;
    }
    @Override
    public void onStop() {
        super.onStop();

        ((MainActivity) getActivity()).update();
    }
}