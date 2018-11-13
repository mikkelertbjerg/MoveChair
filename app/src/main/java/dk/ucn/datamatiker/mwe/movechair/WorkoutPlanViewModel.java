package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutPlanViewModel extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_workout_plan_view, container, false);
    }

    //We need to show chosen exercise based on ID from Bundle

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        WorkoutPlanModel activity = (WorkoutPlanModel) getArguments().getSerializable("activity");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        WorkoutPlanModel workoutPlanModel = new WorkoutPlanModel(activity.getName(),
                activity.getDescription(),
                activity.getId(),
                activity.getWorkouts()
        );

        TextView workout_plan_title = view.findViewById(R.id.workout_plan_title);
        TextView workout_plan_duration = view.findViewById(R.id.workout_plan_duration);
        TextView workout_plan_description = view.findViewById(R.id.workout_plan_description);

        workout_plan_title.setText("Title: " + workoutPlanModel.getName());
        workout_plan_duration.setText("Duration: " + workoutPlanModel.getDuration());
        workout_plan_description.setText("Duration: " + workoutPlanModel.getDescription());

        RecyclerView rvActivities = view.findViewById(R.id.rv_workouts);
        // Create adapter passing in the sample user data
        ActivityAdapter adapter = new ActivityAdapter(activity.getWorkouts());
        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);
        // Set layout manager to position the items
        rvActivities.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}
