package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.AchievementAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.AchievementModel;
import dk.ucn.datamatiker.mwe.movechair.Models.AchievementTypeModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.StridesModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.HomeViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.SessionLogsViewModel;

//TODO For now StepCounter lives here in HomeFragment, let's find a better place later

@RequiresApi(api = Build.VERSION_CODES.P)
public class HomeFragment extends Fragment implements SensorEventListener {

    SensorManager sensorManager;
    HomeViewModel mHomeViewModel;
    boolean running = false;
    private RecyclerView recyclerViewAchievements;

    //UI Elements
    TextView total_points;
    TextView workout_title;
    TextView workout_duration;
    TextView workout_points;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Hides the keyboard if it's shown
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        View focusedView = getActivity().getCurrentFocus();
        if(focusedView != null){
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        //TODO Move to separate class
        //Instantiate sensormanager
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        workout_title = getActivity().findViewById(R.id.activity_title);
        workout_duration = getActivity().findViewById(R.id.activity_field_one);
        workout_points = getActivity().findViewById(R.id.activity_field_two);

        getActivity().findViewById(R.id.activity_divider).setAlpha(0);
        //Starting data for upcoming workout
        workout_title.setText("Login to");
        workout_duration.setText("use this");
        workout_points.setText("feature");

        //Starting data for points
        total_points = getActivity().findViewById(R.id.total_points);
        total_points.setText("Login to track points");
        if(UserHelper.getUser() == null) {
            ImageView points_icon = getActivity().findViewById(R.id.points_icon);
            points_icon.setVisibility(View.GONE);
        }

        //Create achievement dummy data
        //TODO replace dummy data
        ArrayList<AchievementModel> achievements = new ArrayList<AchievementModel>();

        for(int i = 0; i < 4; i++) {
            AchievementTypeModel achievementType = new AchievementTypeModel("Weight", (android.R.drawable.btn_star)+i);
            AchievementModel achievement = new AchievementModel("Meals on Wheels",i+100, achievementType);
            achievements.add(achievement);
        }

        // Create adapter passing in the sample user data
        AchievementAdapter adapter = new AchievementAdapter(achievements);
        // Attach the adapter to the recyclerview to populate items
        recyclerViewAchievements = getActivity().findViewById(R.id.achievement_recyclerview);
        recyclerViewAchievements.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewAchievements.setLayoutManager(linearLayoutManager);

        //OnClick switches to MyPlan view
        Button my_plan_button = getActivity().findViewById(R.id.my_plan_btn);
        my_plan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPlanFragment myPlanFragment = new MyPlanFragment();
                //Replacing the fragment
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.switchFragment(myPlanFragment);
            }
        });


        //Instantiate a sessionLogsViewModel to retrieve a users logs
        SessionLogsViewModel mSessionLogsViewModel = ViewModelProviders.of(this).get(SessionLogsViewModel.class);

        if(UserHelper.getUser() != null) {
            mSessionLogsViewModel.getSessionLogs(new AsyncJsonTask.AsyncJsonResponse() {
                @Override
                public void processFinish(Object o) {
                    getPointsFromLogs((List<SessionLogModel>) o);
                }
            }, SessionLogModel.class, UserHelper.getUser().getId());
        }


        //Instantiate a myPlanViewModel to retrieve a users WorkoutPlans
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        if(UserHelper.getUser() != null) {
            homeViewModel.getNextWorkout(new AsyncJsonTask.AsyncJsonResponse() {
                @Override
                public void processFinish(Object o) {
                    onGetNextWorkout((WorkoutModel) o);
                }
            }, WorkoutModel.class, UserHelper.getUser().getId());

            homeViewModel.getAllStridesFromUser(new AsyncJsonTask.AsyncJsonResponse() {
                @Override
                public void processFinish(Object o) {
                    onGetAllStridesFromUser((List<StridesModel>) o);
                }
            }, StridesModel.class, UserHelper.getUser().getId());
        }
    }

    private void onGetAllStridesFromUser(List<StridesModel> strides) {
        GraphView graph = getActivity().findViewById(R.id.graph_strides);
        if(!strides.isEmpty()) {


            graph.addSeries(mHomeViewModel.getStrides(strides));

            // set date label formatter
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
            graph.getGridLabelRenderer().setNumHorizontalLabels(7);
            graph.getGridLabelRenderer().setTextSize(30);

            // set manual x bounds to have nice steps
            graph.getViewport().setMinX(strides.get(0).getDate().getTime());
            graph.getViewport().setMaxX(strides.get(strides.size() - 1).getDate().getTime());
            graph.getViewport().setXAxisBoundsManual(true);
        } else {
            graph.setVisibility(View.INVISIBLE);
            TextView strides_title = getActivity().findViewById(R.id.strides_title);
            strides_title.setText("No roads conquered yet!");
            strides_title.setTextSize(25);
        }
    }

    private void onGetNextWorkout(final WorkoutModel workout) {
        if(workout != null) {
            View v = getActivity().findViewById(R.id.include);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("activity", workout);
                    WorkoutFragment workoutFragment = new WorkoutFragment();
                    workoutFragment.setArguments(bundle);
                    MainActivity mainActivity = (MainActivity) v.getContext();
                    mainActivity.switchFragment(workoutFragment);
                }
            });
            workout_title.setText("Title: " + workout.getName());
            workout_duration.setText("Duration: " + workout.getDuration());
            workout_points.setText("Points: " + workout.getPoints());
        }
    }

    private void getPointsFromLogs(List<SessionLogModel> logs) {
        UserHelper.getUser().setSessionLogs(logs);
        double points = 0;
        if(logs != null) {
            for (SessionLogModel sessionLog : logs) {
                points += sessionLog.getActivity().getPoints();
            }
        }
        total_points.setText(String.valueOf(points));
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        Sensor counter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (counter != null) {
            sensorManager.registerListener(this, counter, SensorManager.SENSOR_DELAY_UI);
        } else {
            //TODO Sensor not found
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //TODO When steps are received add them to daily log and update graph
        /*if (running) {
            addStepsToGraph(event.values[0]);
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
