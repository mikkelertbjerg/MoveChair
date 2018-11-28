package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.WorkoutTask;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutViewModel;

public class WorkoutFragment extends Fragment implements View.OnClickListener, WorkoutTask.AsyncJsonResponse {

    //TODO DELETE THEESE/TESTING PURPOSE
    private WorkoutModel workout;

    //UI Elements
    TextView workout_title;
    TextView workout_duration;
    //TODO Do we need theese props? Not according to our domain
    //TextView workout_category;
    //TextView workout_difficulty;
    //TextView workout_muscle_group;
    //TextView workout_equipment;
    TextView workout_description;
    RecyclerView rvActivities;

    private WorkoutViewModel mWorkoutViewModel;

    public WorkoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_view, container, false);

        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        WorkoutModel activity = (WorkoutModel) getArguments().getSerializable("activity");

        Button startWorkoutButton = (Button) view.findViewById(R.id.start_workout_button);
        startWorkoutButton.setOnClickListener(this);

        mWorkoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        //Call getWorkoutmethod on ViewModel that starts the async task which retrives data from DB
        mWorkoutViewModel.getWorkout(this,activity.getId());

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        workout_title = view.findViewById(R.id.workout_title);
        workout_duration = view.findViewById(R.id.workout_duration);
        //TODO Do we need theese props? Not according to our domain
        //workout_category = view.findViewById(R.id.workout_category);
        //workout_difficulty = view.findViewById(R.id.workout_difficulty);
        //workout_muscle_group = view.findViewById(R.id.workout_muscle_group);
        //workout_equipment = view.findViewById(R.id.workout_equipment);
        workout_description = view.findViewById(R.id.workout_description);


        rvActivities = view.findViewById(R.id.rv_exercises);
        // Create adapter passing in the sample user data
        List<ActivityModel> exercises = new ArrayList<>();
        ActivityAdapter adapter = new ActivityAdapter(exercises);
        //ActivityAdapter adapter = new ActivityAdapter(workoutModel.getExercises());

        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);
        // Set layout manager to position the items
        rvActivities.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
    /*    try {
            mWorkoutViewModel.addActivityToUser(user, workout);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    //This is the callback method from WorkoutViewmodel, which gets it from WorkoutTask
    @Override
    public void processFinish(WorkoutModel res) {
        workout_title.setText("Title: " + res.getName());
        workout_duration.setText("Duration: " + res.getWorkoutDuration());
        workout_description.setText("Description: " + res.getDescription());

        ActivityAdapter adapter = new ActivityAdapter((List<ActivityModel>)(List<?>)res.getExercises());
        rvActivities.setAdapter(adapter);
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(WorkoutModel item);
    }
}
