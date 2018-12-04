package dk.ucn.datamatiker.mwe.movechair;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import dk.ucn.datamatiker.mwe.movechair.Fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        //Adding default page fragment
        LoginFragment startFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame_login, startFragment, startFragment.getClass().toString())
                .commit();

    }

    public void switchFragment(Fragment fragment) {
        //replacing the fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.content_frame_login);
        if (!fragment.getClass().toString().equals(currentFragment.getTag())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame_login, fragment, fragment.getClass().toString())
                    .commit();
        }
    }
}

