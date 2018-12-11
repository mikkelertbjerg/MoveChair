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
import android.widget.Toast;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.ProgressHelper;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutPlanViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class WorkoutPlanFragment extends Fragment implements View.OnClickListener {

    private WorkoutPlanModel workoutPlan;
    private WorkoutPlanViewModel mWorkoutPlanViewModel;
    private ProgressHelper progressHelper;

    //UI Elements
    private TextView workout_plan_title;
    private TextView workout_plan_duration;
    private TextView workout_plan_description;
    private View mProgressView;
    private View mWorkoutPlanView;
    private Button addWorkoutPlanButton;
    private RecyclerView rvActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_plan_view, container, false);
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Instantiate the viewmodel from ViewModelProviders
        mWorkoutPlanViewModel = ViewModelProviders.of(this).get(WorkoutPlanViewModel.class);

        //UI Elements
        //Get activity object from fragment arguments
        ActivityModel activity = (ActivityModel) getArguments().getSerializable("activity");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());
        workout_plan_title = view.findViewById(R.id.workout_plan_title);
        workout_plan_duration = view.findViewById(R.id.workout_plan_duration);
        workout_plan_description = view.findViewById(R.id.workout_plan_description);
        mProgressView = view.findViewById(R.id.progress);
        mWorkoutPlanView = view.findViewById(R.id.workout_plan_form);
        rvActivities = view.findViewById(R.id.rv_workouts);

        //Setup ProgressHelper
        progressHelper = new ProgressHelper();

        //Buttons
        addWorkoutPlanButton = (Button) view.findViewById(R.id.add_workout_plan_button);
        addWorkoutPlanButton.setOnClickListener(this);
        if(UserHelper.getUser() == null){
            addWorkoutPlanButton.setEnabled(false);
        }

        //Call the async method in the viewmodel
        mWorkoutPlanViewModel.getItem(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object res) {
                loadedUsersWorkoutPlans((WorkoutPlanModel) res);
                progressHelper.showProgress(false, mWorkoutPlanView, mProgressView, getContext());
            }
        }, WorkoutPlanModel.class, activity.getId());
       
        // Set layout manager to position the items
        rvActivities.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        //Pass the id of the activity to the ViewModel which delegates to task
        mWorkoutPlanViewModel.addWorkoutPlanToUser(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                addedWorkoutPlanToUser((String) o);
            }
        }, String.class, workoutPlan.getId());
    }

    public void addedWorkoutPlanToUser(String res) {
        if(res == "true") {
            Toast.makeText(getContext(), "Plan added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadedUsersWorkoutPlans(WorkoutPlanModel res) {
        this.workoutPlan = res;
        workout_plan_title.setText("Title: " + this.workoutPlan.getName());
        workout_plan_duration.setText("Duration: " + this.workoutPlan.getDuration());
        workout_plan_description.setText("Description: " + this.workoutPlan.getDescription());

        // Create adapter passing in the sample user data
        ActivityAdapter adapter = new ActivityAdapter((List<ActivityModel>)(List<?>) this.workoutPlan.getWorkouts());
        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);
    }

}
