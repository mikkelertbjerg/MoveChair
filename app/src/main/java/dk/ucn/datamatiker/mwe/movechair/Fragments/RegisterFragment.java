package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.LoginViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.UserViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class RegisterFragment extends Fragment {
    //Input field
    private EditText emailView;
    private EditText passwordView;
    private UserViewModel mUserViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Register");
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        emailView = view.findViewById(R.id.email);
        passwordView = view.findViewById(R.id.password);

        Button registerButton = (Button) view.findViewById(R.id.action_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailView.getText().toString().trim().length() > 0 &&
                        passwordView.getText().toString().trim().length() > 0){
                    createNewUser();
                }
                else{
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createNewUser(){
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        mUserViewModel.create(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                login((UserModel) o);
                Toast.makeText(getContext(), ((UserModel) o).getEmail() + " Created", Toast.LENGTH_SHORT).show();
            }
        },UserModel.class, email, password);
    }

    private void login(UserModel o){
        if(o != null){

            mUserViewModel.login(new AsyncJsonTask.AsyncJsonResponse() {
                @Override
                public void processFinish(Object o) {
                    UserHelper.setUser((UserModel) o);
                    Toast.makeText(getContext(), "Logged in as " + ((UserModel) o).getEmail(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), MainActivity.class);
                    getActivity().finish(); //Kill the current activity
                    startActivity(i);
                }
            }, UserModel.class, o.getEmail(), o.getHashedPassword());

        }
    }
}
