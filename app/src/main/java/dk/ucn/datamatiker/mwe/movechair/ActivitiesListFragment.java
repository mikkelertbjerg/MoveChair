package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ActivitiesListFragment extends Fragment{

    ArrayList<ActivityModel> activities;

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
        RecyclerView rvActivities = view.findViewById(R.id.rv_activities);

        //TODO method should be replaced by getting data from DB:
        //TODO if statement that switches on ActivityTypeID fills the adapter list

        //TODO Delete: used to generate dummydate

        switch(getArguments().getString("buttonText")){
            case "Exercises":
                activities = createExercises(10);
                break;

            case "Workouts":
                activities = createWorkouts(5);
                break;

            case "Workout Plans":
                activities = createWorkoutPlans(5);
                break;

            default:
                break;
        }
        // Create adapter passing in the sample user data
        ActivityAdapter adapter = new ActivityAdapter(activities);
        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);
        // Set layout manager to position the items
        rvActivities.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    //public int lastActivtyListTitle = 0;
    //public int lastActivityListDescription = 0;

    private ArrayList<ActivityModel> createExercises(int numActivities){
        ArrayList<ActivityModel> exercises = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            exercises.add(new ExerciseModel("Exercise" + (i+1), "Description " + (i+1), i, 1));
        }
        return exercises;
    }
    private ArrayList<ActivityModel> createWorkouts(int numActivities){
        ArrayList<ActivityModel> workouts = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            workouts.add(new WorkoutModel("Workout " + (i+1), "Description " + (i+1), i, createExercises(5)));
        }
        return workouts;
    }
    private ArrayList<ActivityModel> createWorkoutPlans(int numActivities){
        ArrayList<ActivityModel> workoutPlans = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            workoutPlans.add(new WorkoutPlanModel("Workout Plan " + (i+1), "Description " + (i+1), i, createWorkouts(5)));
        }
        return workoutPlans;
    }

}
