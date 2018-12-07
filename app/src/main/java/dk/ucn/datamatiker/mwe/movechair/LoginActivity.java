package dk.ucn.datamatiker.mwe.movechair;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import dk.ucn.datamatiker.mwe.movechair.Fragments.LoginFragment;
@RequiresApi(api = Build.VERSION_CODES.P)
public class LoginActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        //Adding default page fragment
        LoginFragment startFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_content_frame, startFragment, startFragment.getClass().toString())
                .commit();
    }

    public void switchFragment(Fragment fragment) {
        //replacing the fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.login_content_frame);
        if (!fragment.getClass().toString().equals(currentFragment.getTag())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.login_content_frame, fragment, fragment.getClass().toString())
                    .commit();
        }
    }
}

