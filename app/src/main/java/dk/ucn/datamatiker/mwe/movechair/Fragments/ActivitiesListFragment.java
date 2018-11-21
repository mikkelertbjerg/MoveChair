package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
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

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ActivityListViewModel;

public class ActivitiesListFragment extends Fragment implements View.OnClickListener {

    List<ActivityModel> exercises;
    List<ActivityModel> workouts;
    List<ActivityModel> workoutPlans;
    ActivityListViewModel vModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_activities_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("buttonText"));

        vModel = ViewModelProviders.of(this).get(ActivityListViewModel.class);

        RecyclerView rvActivities = view.findViewById(R.id.rv_activities);

        Button filterButton = (Button) view.findViewById(R.id.set_filters_button);
        filterButton.setOnClickListener(this);

        //TODO method should be replaced by getting data from DB:
        //TODO if statement that switches on ActivityTypeID fills the adapter list

        //TODO Delete: used to generate dummy data

        switch(getArguments().getString("buttonText")){
            case "Exercises":
                exercises = new DummyData().createExercises(10);
                // Create adapter passing in the sample user data
               /* ActivityAdapter exerciseAdapter = new ActivityAdapter(exercises);

                // Attach the adapter to the recyclerview to populate items
                rvActivities.setAdapter(exerciseAdapter);*/
                break;

            case "Workouts":
                workouts = new DummyData().createWorkouts(5);
                // Create adapter passing in the sample user data
                /*MyWorkoutRecyclerViewAdapter workoutAdapter = new MyWorkoutRecyclerViewAdapter(workouts);

                // Attach the adapter to the recyclerview to populate items
                rvActivities.setAdapter(workoutAdapter);*/
                break;

            case "Workout Plans":
                workoutPlans = new DummyData().createWorkoutPlans(5);
                /*MyWorkoutPlanRecyclerViewAdapter workoutPlanAdapter = new MyWorkoutPlanRecyclerViewAdapter(workouts);

                // Attach the adapter to the recyclerview to populate items
                rvActivities.setAdapter(workoutPlanAdapter);*/
                break;

            default:
                break;
        }


        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvActivities.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {
        FilterFragment filterFragment = new FilterFragment();
        /*Bundle bundleActivities = new Bundle();
        bundleActivities.putSerializable("Activities", activities);
        filterFragment.setArguments(bundleActivities);*/
        MainActivity mainActivity = (MainActivity) v.getContext();
        mainActivity.switchFragment(filterFragment);
    }
}
