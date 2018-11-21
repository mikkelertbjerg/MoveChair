package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import dk.ucn.datamatiker.mwe.movechair.AchievementAdapter;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.AchievementModel;
import dk.ucn.datamatiker.mwe.movechair.Models.AchievementTypeModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.HomeViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.MyPlanViewModel;

//TODO For now StepCounter lives here in HomeFragment, let's find a better place later
//TODO Maybe replace current implementation of next workout with a calenderView of some sort?
//TODO Change Upcoming Workout to select data from DB, the users chosen workoutplans next workout

public class HomeFragment extends Fragment implements SensorEventListener {

    SensorManager sensorManager;
    HomeViewModel mHomeViewModel;
    boolean running = false;
    private RecyclerView recyclerViewAchievements;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");

        //TODO Move to separate class
        //Instantiate sensormanager
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        //Create and display strides graph
        createStridesGraph();

        //Dummy data for upcoming workout
        //TODO change to values from DB
        TextView workout_title = getActivity().findViewById(R.id.activity_title);
        TextView workout_duration = getActivity().findViewById(R.id.activity_field_one);
        TextView workout_points = getActivity().findViewById(R.id.activity_field_two);

        getActivity().findViewById(R.id.activity_divider).setAlpha(0);
        workout_title.setText("Title: Lord");
        workout_duration.setText("Duration: 5");
        workout_points.setText("Points: 10.0");

        //Dummy data for points
        //TODO Implement total points retrieved from DB
        TextView total_points = getActivity().findViewById(R.id.total_points);
        total_points.setText("2853");

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
        Button my_plan_btn = getActivity().findViewById(R.id.my_plan_btn);
        my_plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPlanFragment myPlanFragment = new MyPlanFragment();
                //Replacing the fragment
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.switchFragment(myPlanFragment);
            }
        });

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

    private void createStridesGraph() {
        //TODO Implement getting data points from step counter
        //TODO Retrieve strides from DB from viewModel

        GraphView graph = getActivity().findViewById(R.id.graph_strides);

        graph.addSeries(mHomeViewModel.getStrides());
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
