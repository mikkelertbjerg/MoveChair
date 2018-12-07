package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Button;

import dk.ucn.datamatiker.mwe.movechair.Adapters.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.ProgressHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ActivityListViewModel;

public class ActivitiesListFragment extends Fragment {

    List<ActivityModel> activities;
    ActivityListViewModel mActivityListViewModel;
    ActivityAdapter activityAdapter;
    RecyclerView rvActivities;
    ProgressHelper progressHelper;
    View mActivitiesView;
    View mProgressView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_activities_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("buttonText"));

        mActivityListViewModel = ViewModelProviders.of(this).get(ActivityListViewModel.class);

        progressHelper = new ProgressHelper();
        mActivitiesView = view.findViewById(R.id.rv_activities);
        mProgressView = view.findViewById(R.id.progress);

        rvActivities = view.findViewById(R.id.rv_activities);

        activities = new ArrayList<>();

        // Create adapter passing in the sample user data
        activityAdapter = new ActivityAdapter(activities);
        rvActivities.setAdapter(activityAdapter);

        //Button filterButton = (Button) view.findViewById(R.id.set_filters_button);
        //filterButton.setOnClickListener(this);

        //Get the Activity Type from the bundle and start an async call to viewmodel with the type given
        Type type = (Type) getArguments().getSerializable("type");
        mActivityListViewModel.getActivities(new AsyncJsonTask.AsyncJsonResponse() {

            @Override
            public void processFinish(Object o) {
                onGetActivities(o);
                progressHelper.showProgress(false, mActivitiesView, mProgressView, getContext());
            }

        }, type);

        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvActivities.setLayoutManager(linearLayoutManager);
    }

/*    @Override
    public void onClick(View v) {
        FilterFragment filterFragment = new FilterFragment();
        *//*Bundle bundleActivities = new Bundle();
        bundleActivities.putSerializable("Activities", activities);
        filterFragment.setArguments(bundleActivities);*//*
        MainActivity mainActivity = (MainActivity) v.getContext();
        mainActivity.switchFragment(filterFragment);
    }*/

    //This method is the callback for our ActivityListTask
    public void onGetActivities(Object o) {
        List<ActivityModel> temp = (List<ActivityModel>) o;
        activityAdapter = new ActivityAdapter(temp);
        rvActivities.setAdapter(activityAdapter);
    }
}
