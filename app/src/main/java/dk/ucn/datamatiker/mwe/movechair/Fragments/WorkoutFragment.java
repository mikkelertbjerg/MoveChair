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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.ProgressHelper;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class WorkoutFragment extends Fragment implements View.OnClickListener {
    private WorkoutViewModel mWorkoutViewModel;
    private ProgressHelper progressHelper;
    private WorkoutModel workout;

    //UI Elements
    private TextView workout_title;
    private TextView workout_duration;
    private TextView workout_description;
    private RecyclerView rvActivities;
    private View mProgressView;
    private View mWorkoutView;


    @RequiresApi(api = Build.VERSION_CODES.P)
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
        ActivityModel activity = (ActivityModel) getArguments().getSerializable("activity");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        //ViewModels
        mWorkoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        //UI Elements
        workout_title = view.findViewById(R.id.workout_title);
        workout_duration = view.findViewById(R.id.workout_duration);
        //TODO Do we need theese props? Not according to our domain
        //workout_category = view.findViewById(R.id.workout_category);
        //workout_difficulty = view.findViewById(R.id.workout_difficulty);
        //workout_muscle_group = view.findViewById(R.id.workout_muscle_group);
        //workout_equipment = view.findViewById(R.id.workout_equipment);
        workout_description = view.findViewById(R.id.workout_description);
        mProgressView = view.findViewById(R.id.progress);
        mWorkoutView = view.findViewById(R.id.workout_form);

        //Progresshelper
        progressHelper = new ProgressHelper();

        //Buttons
        Button startWorkoutButton = (Button) view.findViewById(R.id.start_workout_button);
        startWorkoutButton.setOnClickListener(this);

        //Setup the adapter
        //Call getWorkoutmethod on ViewModel that starts the async task which retrives data from DB
        mWorkoutViewModel.getWorkout(new AsyncJsonTask.AsyncJsonResponse() {

            @Override
            public void processFinish(Object o) {
                onGetWorkout((WorkoutModel) o);
                progressHelper.showProgress(false, mWorkoutView, mProgressView, getContext());
            }
        }, WorkoutModel.class, activity.getId());

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
        //Initiates new ActivityGOFragment and parses the workoutModel
        ActivityGOFragment fragment = new ActivityGOFragment();
        Bundle bundleActivities = new Bundle();
        bundleActivities.putSerializable("workout", workout);
        fragment.setArguments(bundleActivities);
        MainActivity mainActivity = (MainActivity) v.getContext();
        mainActivity.switchFragment(fragment);
    }

    public void onGetWorkout(WorkoutModel res) {
        workout = res;
        workout_title.setText("Title: " + res.getName());
        workout_duration.setText("Duration: " + res.getDuration());
        workout_description.setText("Description: " + res.getDescription());

        ActivityAdapter adapter = new ActivityAdapter((List<ActivityModel>)(List<?>)res.getExercises());
        rvActivities.setAdapter(adapter);
    }
}
