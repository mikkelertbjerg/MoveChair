package dk.ucn.datamatiker.mwe.movechair.Fragments;


import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.UserViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class GetStartedFragment extends Fragment {

    private UserViewModel mUserViewModel;

    private RadioButton gender_male;
    private RadioButton gender_female;
    private EditText birth_date;
    private EditText weight;
    private EditText height;

    //Datepicker
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_get_started, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");


        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mUserViewModel.getUserByEmail(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                onGetUserByEmail((UserModel)o);
            }
        },UserModel.class, UserHelper.getUser().getEmail());

        gender_male = view.findViewById(R.id.male_radio_button);
        gender_female = view.findViewById(R.id.female_radio_button);
        birth_date = view.findViewById(R.id.birth_date);
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);

        //Date picker
        birth_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getContext(),android.R.style.Theme_Material_Dialog, mDateSetListener, year,month,day);
                datePicker.show();
            }
        });

        // Date picker listener
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                birth_date.setText(dayOfMonth + "-" + month + "-" + year);
            }
        };

        Button getStartedButton = (Button) view.findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;
                int uGender_id = 0;
                String uBirthDate = "";
                double uWeight = 0;
                double uHeight = 0;
                if(gender_male.isChecked()){
                    uGender_id = 2;
                }
                else if(gender_female.isChecked()){
                    uGender_id = 1;
                }
                else{
                    check = false;
                }
                if(!birth_date.getText().equals("")){
                    uBirthDate = birth_date.getText().toString();
                }
                else{
                    check = false;
                }
                if(Double.valueOf(weight.getText().toString()).doubleValue()  > 0){
                    uWeight = Double.valueOf(weight.getText().toString()).doubleValue();
                }
                else{
                    check = false;
                }
                if(Double.valueOf(height.getText().toString()).doubleValue() > 0){
                    uHeight = (Double.valueOf(height.getText().toString()).doubleValue());
                }
                else{
                    check = false;
                }

                if(check){
                    mUserViewModel.getStarted(new AsyncJsonTask.AsyncJsonResponse() {
                        @Override
                        public void processFinish(Object o) {

                        }
                    }, UserModel.class, UserHelper.getUser().getId(), uGender_id, uBirthDate, uWeight, uHeight);
                }

                //Switch to appropriate exercise/workout/workoutplan
                //Currently sat to home
                HomeFragment homeFragment = new HomeFragment();
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.switchFragment(homeFragment);
            }
        });

        Button skipButton = (Button) view.findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment = new HomeFragment();
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.switchFragment(homeFragment);
            }
        });
    }

    private void onGetUserByEmail(UserModel o) {
        if(o.getGender() != null) {
            if(o.getGender().getName().equals("male")) {
                gender_male.setChecked(true);
            } else if(o.getGender().getName().equals("female")){
                gender_female.setChecked(true);
            }
        }

        if(o.getBirthDate().getTime() > 0) {

            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            birth_date.setText(formatter.format(o.getBirthDate()));

        }

        if(o.getWeight() > 0) {
            weight.setText(Double.toString(o.getWeight()));
        }

        if(o.getHeight() > 0) {
            height.setText(Double.toString(o.getHeight()));
        }
    }

}
