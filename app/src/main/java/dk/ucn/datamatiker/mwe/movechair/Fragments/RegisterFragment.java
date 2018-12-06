package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.LoginActivity;
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
    private View mProgressView;
    private View mRegisterFormView;

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

        //Shows the keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(emailView, InputMethodManager.SHOW_IMPLICIT);

        Button registerButton = (Button) view.findViewById(R.id.action_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailView.getText().toString().trim().length() > 0 &&
                        passwordView.getText().toString().trim().length() > 0) {
                    createNewUser();
                } else {
                    Toast.makeText(getContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mProgressView = getActivity().findViewById(R.id.register_progress);
        mRegisterFormView = getActivity().findViewById(R.id.register_form);
    }

    private void createNewUser() {
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();
        mUserViewModel.create(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                login((UserModel) o);
                Toast.makeText(getContext(), ((UserModel) o).getEmail() + " Created", Toast.LENGTH_SHORT).show();
            }
        }, UserModel.class, email, password);
        showProgress(true); //TODO add another layout so progress can be shown.
    }

    private void login(UserModel o) {
        if (o != null) {

            mUserViewModel.login(new AsyncJsonTask.AsyncJsonResponse() {
                @Override
                public void processFinish(Object o) {
                    UserHelper.setUser((UserModel) o);
                    Toast.makeText(getContext(), "Logged in as " + ((UserModel) o).getEmail(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "getStarted");
                    i.putExtras(bundle);
                    getActivity().finish(); //Kill the current activity
                    startActivity(i);
                }
            }, UserModel.class, o.getEmail(), o.getHashedPassword());

        }
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
