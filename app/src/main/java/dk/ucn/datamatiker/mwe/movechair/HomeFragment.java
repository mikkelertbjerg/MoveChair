package dk.ucn.datamatiker.mwe.movechair;

import android.app.Activity;
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
import android.widget.Toast;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

//TODO For now StepCounter lives here in HomeFragment, let's find a better place later
//TODO Maybe replace current implementation of next workout with a calenderView of some sort?
//TODO Change Upcoming Workout to select data from DB, the users chosen workoutplans next workout

public class HomeFragment extends Fragment implements SensorEventListener {

    SensorManager sensorManager;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Plan");

        //Instantiate sensormanager
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        //Create and display strides graph
        createStridesGraph();

        //Dummy data for upcoming workout
        //TODO change to values from DB
        TextView workout_title = getActivity().findViewById(R.id.activity_title);
        TextView workout_duration = getActivity().findViewById(R.id.activity_duration);
        TextView workout_points = getActivity().findViewById(R.id.activity_points);

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
        //TODO Retrieve strides from DB

        GraphView graph = getActivity().findViewById(R.id.graph_strides);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 125),
                new DataPoint(1, 521),
                new DataPoint(2, 353),
                new DataPoint(3, 2156),
                new DataPoint(4, 66),
                new DataPoint(5, 220),
                new DataPoint(6, 23)
        });
        graph.addSeries(series);
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
