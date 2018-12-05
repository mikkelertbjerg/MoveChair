package dk.ucn.datamatiker.mwe.movechair.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.GenderModel;

public class GetStartedFragment extends Fragment {

    private RadioButton gender_male;
    private RadioButton gender_female;
    private EditText birth_date;
    private EditText weight;
    private EditText height;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_get_started, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gender_male = view.findViewById(R.id.male_radio_button);
        gender_female = view.findViewById(R.id.female_radio_button);
        birth_date = view.findViewById(R.id.input_birthday);
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);

        RadioButton male = (RadioButton) view.findViewById(R.id.male_radio_button);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Male?", Toast.LENGTH_SHORT).show();
            }
        });

        Button getStartedButton = (Button) view.findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender_male.isChecked()){
                    UserHelper.getUser().setGender(new GenderModel("2", "Male"));
                }
                else if(gender_female.isChecked()){
                    UserHelper.getUser().setGender(new GenderModel("1", "Female"));
                }
                if(birth_date.getText().length() > 9){
                    String date = birth_date.getText().toString();
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date birthDate = format.parse(date);
                        UserHelper.getUser().setBirthDate(birthDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(Integer.valueOf(weight.getText().toString()) > 0){
                    UserHelper.getUser().setWeight(Double.valueOf(weight.getText().toString()).doubleValue());
                }
                if(Integer.valueOf(height.getText().toString()) > 0){
                    UserHelper.getUser().setHeight(Double.valueOf(weight.getText().toString()).doubleValue());
                }

                Intent i = new Intent(getContext(), MainActivity.class);
                getActivity().finish(); //Kill the current activity
                startActivity(i);
            }
        });

        Button skipButton = (Button) view.findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                getActivity().finish(); //Kill the current activity
                startActivity(i);
            }
        });
    }

}
