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

public class ActivitiesListFragment extends Fragment implements View.OnClickListener, ActivityListTask.AsyncJsonResponse {

    List<ActivityModel> activities;
    ActivityListViewModel mActivityListViewModel;
    ActivityAdapter activityAdapter;
    RecyclerView rvActivities;

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

        mActivityListViewModel = ViewModelProviders.of(this).get(ActivityListViewModel.class);

        rvActivities = view.findViewById(R.id.rv_activities);

        activities = new ArrayList<>();

        // Create adapter passing in the sample user data
        activityAdapter = new ActivityAdapter(activities);
        rvActivities.setAdapter(activityAdapter);

        Button filterButton = (Button) view.findViewById(R.id.set_filters_button);
        filterButton.setOnClickListener(this);

        //Get the activityType from the button text and start an async call to viewmodel with the type given
        String activityType = getArguments().getString("buttonText");
        mActivityListViewModel.getActivities(activityType,this);

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

    //This method is the callback for our ActivityListTask
    @Override
    public void processFinish(List<ActivityModel> res) {
        activityAdapter = new ActivityAdapter(res);
        rvActivities.setAdapter(activityAdapter);
    }
}
