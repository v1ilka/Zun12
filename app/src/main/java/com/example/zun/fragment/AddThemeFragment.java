package com.example.zun.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.example.zun.MainActivity;
import com.example.zun.R;
import com.example.zun.adapter.GenderSpinnerAdapter;
import com.example.zun.domain.Theme;
import com.example.zun.fakedb.NoDb;
import com.example.zun.rest.ZunApiImpl;


public class AddThemeFragment extends Fragment {
    private EditText etThemeName;

    private AppCompatSpinner sp_gender;

    private Theme theme;

    @Override
    public void onResume() {
        super.onResume();

        if (etThemeName != null) {

            etThemeName.setText("");
        }
    }

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
        View view = inflater.inflate(R.layout.fragment_add_theme, container, false);

        etThemeName = view.findViewById(R.id.et_themeName);

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
                    String selectedItem = (String) sp_gender.getSelectedItem();

                    theme = new Theme(
                            0,
                            etThemeName.getText().toString(),
                            selectedItem.equals("man") ? 1 : (selectedItem.equals("woman") ? 1 : 2)
                            , "sjkhegdhjkhsjlsdhjkdhslkjsd"
                            , 0,
                            0
                    );
                    new ZunApiImpl(getActivity()).newTheme(theme);

                    getActivity().getSupportFragmentManager().beginTransaction().remove(AddThemeFragment.this).commit();
                }

                //Toast.makeText(getActivity(), sp_author.getSelectedItem() + "", Toast.LENGTH_SHORT).show();
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