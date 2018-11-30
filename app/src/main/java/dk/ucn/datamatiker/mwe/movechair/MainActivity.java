package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import dk.ucn.datamatiker.mwe.movechair.Fragments.AchievementsFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.ActivitiesFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.HighscoreFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.HomeFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.OptionsFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.ProfileFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.MyPlanFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.SessionLogsFragment;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu 1");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        //Adding default page fragment
        HomeFragment startFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, startFragment, startFragment.getClass().toString())
                .commit();

        // Find header so we can access its views
        //Then fill them with the logged in users info
        TextView username = header.findViewById(R.id.header_username);
        TextView email = header.findViewById(R.id.header_email);
        if (UserHelper.getUser() != null) {
            username.setText(UserHelper.getUser().getName());
            email.setText(UserHelper.getUser().getEmail());
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            moveTaskToBack(true);
        }
        else{
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_activity_log:
                fragment = new SessionLogsFragment();
                break;
            case R.id.nav_activities:
                fragment = new ActivitiesFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_achievements:
                fragment = new AchievementsFragment();
                break;
            case R.id.nav_highscore:
                fragment = new HighscoreFragment();
                break;
            case R.id.nav_options:
                fragment = new OptionsFragment();
                break;
            case R.id.nav_my_plan:
                fragment = new MyPlanFragment();
                break;

                default:
            fragment = new HomeFragment();
                    break;
        }

        //replacing the fragment
        switchFragment(fragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void switchFragment(Fragment fragment) {
        //replacing the fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.content_frame);
        if (!fragment.getClass().toString().equals(currentFragment.getTag())) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame, fragment, fragment.getClass().toString())
                    .commit();
        }
    }

}
