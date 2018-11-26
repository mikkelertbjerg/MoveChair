package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.LoginTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.LoginViewModel;

public class LoginFragment extends Fragment {

    // Dependencies
    private LoginViewModel loginVM;
    private UserModel loggedInUser;
    private LoginTask loginTask;

    // Views
    private AutoCompleteTextView vEmail;
    private EditText vPassword;
    private Button vSignInBtn;
    private Button vPreviewBtn;
    private Button vSignUpBtn;
    private View vProgress;
    private View vLoginView;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Instantiate the viewmodel from viewmodelproviders
        this.loginVM = ViewModelProviders.of(this).get(LoginViewModel.class);

        // Instantiate views
        this.vEmail = view.findViewById(R.id.email);
        this.vPassword = view.findViewById(R.id.password);
        this.vProgress = view.findViewById(R.id.login_progress);
        this.vLoginView = view.findViewById(R.id.login_form);
        this.vSignInBtn = view.findViewById(R.id.email_sign_in_button);
        this.vSignUpBtn = view.findViewById(R.id.register_button);
        this.vPreviewBtn = view.findViewById(R.id.preview_app_button);

        this.vSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        this.vSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });

        this.vPreviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptPreview();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void attemptPreview() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        getActivity().finish(); //Kill the current activity
        startActivity(i);
    }

    private void attemptRegister() {
    }

    private void attemptLogin() {

        // Read email value from TextView
        String email = this.vEmail.getText().toString();

        // Reset errors on input for each login attempt
        vEmail.setError(null);
        vPassword.setError(null);

        // Run async task to fetch data from backend
        new LoginTask(new LoginTask.AsyncJsonResponse(){

            @Override
            public void processFinish(UserModel res) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("user", res);
                getActivity().finish(); //Kill the current activity
                startActivity(i);
            }

        }, email).execute();

        showProgress(true);

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            vLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
            vLoginView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    vLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            vProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            vLoginView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
