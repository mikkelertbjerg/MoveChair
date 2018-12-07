package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dk.ucn.datamatiker.mwe.movechair.Helpers.ProgressHelper;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.LoginActivity;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.UserViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class LoginFragment extends Fragment {

    ProgressHelper progressHelper;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserViewModel mUserViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressHelper = new ProgressHelper();

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) getActivity().findViewById(R.id.email);

        mPasswordView = (EditText)getActivity().findViewById(R.id.password);

        Button mEmailSignInButton = (Button) getActivity().findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button previewButton = (Button) getActivity().findViewById(R.id.preview_app_button);
        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "preview");
                i.putExtras(bundle);
                getActivity().finish(); //Kill the current activity
                startActivity(i);
            }
        });

        Button registerButton = (Button) getActivity().findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RegisterFragment loginFragment = new RegisterFragment();
                LoginActivity loginActivity = (LoginActivity)getActivity();
                loginActivity.switchFragment(loginFragment);
            }
        });

        mLoginFormView = getActivity().findViewById(R.id.login_form);
        mProgressView = getActivity().findViewById(R.id.progress);
        progressHelper.showProgress(false, mLoginFormView, mProgressView, getContext());

    }


    private void attemptLogin() {
        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        // Creates the LoginTask and binds a listener in its interface.
        // This will delegate from AsyncTasks "onPostExecute()" method to trigger this overriden "processFinish(UserModel res)" method.
        mUserViewModel.login(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                if(o != null) {
                    UserHelper.setUser((UserModel) o);
                    Intent i = new Intent(getContext(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("key", "login");
                    i.putExtras(bundle);
                    getActivity().finish(); //Kill the current activity
                    startActivity(i);
                    progressHelper.showProgress(true, mLoginFormView, mProgressView, getContext());
                }
                else{
                    Toast.makeText(getContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        }, UserModel.class, email, password);

    }
}
