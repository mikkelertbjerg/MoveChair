package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.LoginActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ActivityListViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.LoginTask;

public class ActivitiesListFragment extends Fragment implements View.OnClickListener {

    List<ActivityModel> exercises;
    List<ActivityModel> workouts;
    List<ActivityModel> workoutPlans;
    ActivityListViewModel vModel;
    ActivityAdapter activityAdapter;
    RecyclerView rvActivities;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_activities_list, container, false);
    }

    private void updateActivitiesList(List<ActivityModel> activities) {
        //activityAdapter.updateData((List<ExerciseModel>)(List<?>) activities);

        activityAdapter = new ActivityAdapter(activities);
        rvActivities.setAdapter(activityAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("buttonText"));

        vModel = ViewModelProviders.of(this).get(ActivityListViewModel.class);

        rvActivities = view.findViewById(R.id.rv_activities);

        exercises = new ArrayList<ActivityModel>();

        // Create adapter passing in the sample user data
        activityAdapter = new ActivityAdapter(exercises);

        Button filterButton = (Button) view.findViewById(R.id.set_filters_button);
        filterButton.setOnClickListener(this);

        //TODO method should be replaced by getting data from DB:
        //TODO if statement that switches on ActivityTypeID fills the adapter list

        //TODO Delete: used to generate dummy data
        String activityType = getArguments().getString("buttonText");

        switch(activityType){
            case "Exercises":

                new ActivityListTask(new ActivityListTask.AsyncJsonResponse(){

                    @Override
                    public void processFinish(List<ActivityModel> res) {
                        if(res != null) {
                            updateActivitiesList(res);
                        }
                    }

                }, activityType).execute();

                break;

            case "Workouts":

                new ActivityListTask(new ActivityListTask.AsyncJsonResponse(){

                    @Override
                    public void processFinish(List<ActivityModel> res) {
                        if(res != null) {
                            updateActivitiesList(res);
                        }
                    }

                }, activityType).execute();
/*                workouts = new DummyData().createWorkouts(5);
                // Create adapter passing in the sample user data
                activityAdapter = new ActivityAdapter(workouts);

                // Attach the adapter to the recyclerview to populate items
                rvActivities.setAdapter(activityAdapter);
                break;*/

            case "Workout Plans":
                workoutPlans = new DummyData().createWorkoutPlans(5);
                activityAdapter = new ActivityAdapter(workoutPlans);

                // Attach the adapter to the recyclerview to populate items
                rvActivities.setAdapter(activityAdapter);
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
