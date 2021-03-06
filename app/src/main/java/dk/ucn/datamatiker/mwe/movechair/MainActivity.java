package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import dk.ucn.datamatiker.mwe.movechair.Fragments.AchievementsFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.ActivitiesFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.AnalyzeFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.GetStartedFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.GoogleFitFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.HighscoreFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.HomeFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.OptionsFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.ProfileFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.MyPlanFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.SMARTFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.SessionLogsFragment;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;

@RequiresApi(api = Build.VERSION_CODES.P)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        //Adding default page fragment
        //Checking which fragment user is comming from
        if(getIntent().getExtras().getString("key").equals("getStarted")){
            GetStartedFragment getStartedFragment = new GetStartedFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, getStartedFragment, getStartedFragment.getClass().toString())
                    .commit();
        }
        else if(getIntent().getExtras().getString("key").equals("login") || getIntent().getExtras().getString("key").equals("preview")) {
            HomeFragment startFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, startFragment, startFragment.getClass().toString())
                    .commit();
        }

        // Find header so we can access its views
        //Then fill them with the logged in users info
        TextView username = header.findViewById(R.id.header_username);
        TextView email = header.findViewById(R.id.header_email);
        if (UserHelper.getUser() != null) {
            username.setText(UserHelper.getUser().getName());
            email.setText(UserHelper.getUser().getEmail());
        }

        //Adjust the UI if there's no user
        if(UserHelper.getUser() == null){
            setUpDrawer();
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

    private void setUpDrawer(){
        navigationView.getMenu().findItem(R.id.nav_activity_log).setEnabled(false);
        navigationView.getMenu().findItem(R.id.nav_my_plan).setEnabled(false);
        navigationView.getMenu().findItem(R.id.nav_profile).setEnabled(false);
        navigationView.getMenu().findItem(R.id.nav_achievements).setEnabled(false);
        navigationView.getMenu().findItem(R.id.nav_get_started).setEnabled(false);
        navigationView.getMenu().findItem(R.id.nav_highscore).setEnabled(false);
        navigationView.getMenu().findItem(R.id.nav_analyze).setEnabled(false);
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
            case R.id.nav_analyze:
                fragment = new AnalyzeFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_get_started:
                fragment = new GetStartedFragment();
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
            case R.id.nav_googlefit:
                fragment = new GoogleFitFragment();
                break;
            case R.id.nav_smart:
                fragment = new SMARTFragment();
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

    //GOOGLE FIT TESTING
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
    }
}
