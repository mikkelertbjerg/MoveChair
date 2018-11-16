package dk.ucn.datamatiker.mwe.movechair.Fragments;

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
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

import java.util.ArrayList;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class ActivitiesListFragment extends Fragment implements View.OnClickListener {

    ArrayList<ActivityModel> activities;
//    private SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("My Filters", Context.MODE_PRIVATE);

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
        //TODO Generate dummy data
        DummyData dummyData = new DummyData();

        RecyclerView rvActivities = view.findViewById(R.id.rv_activities);

        Button filterButton = (Button) view.findViewById(R.id.set_filters_button);
        filterButton.setOnClickListener(this);

        //TODO method should be replaced by getting data from DB:
        //TODO if statement that switches on ActivityTypeID fills the adapter list

        //TODO Delete: used to generate dummy data

        switch(getArguments().getString("buttonText")){
            case "Exercises":
                activities = dummyData.createExercises(10);
                break;

            case "Workouts":
                activities = dummyData.createWorkouts(5);
                break;

            case "Workout Plans":
                activities = dummyData.createWorkoutPlans(5);
                break;

            default:
                break;
        }

        // Create adapter passing in the sample user data
        ActivityAdapter adapter = new ActivityAdapter(activities);

        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);

        Bundle adapterBundle = new Bundle();
        adapterBundle.putSerializable("activityAdapter", adapter);

        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvActivities.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {
        FilterFragment filterFragment = new FilterFragment();
        Bundle bundleActivities = new Bundle();
        bundleActivities.putSerializable("Activities", activities);
        filterFragment.setArguments(bundleActivities);
        MainActivity mainActivity = (MainActivity) v.getContext();
        mainActivity.switchFragment(filterFragment);
    }
}