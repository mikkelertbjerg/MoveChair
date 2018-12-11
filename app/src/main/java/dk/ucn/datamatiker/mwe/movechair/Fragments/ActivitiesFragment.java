package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.R;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ActivitiesFragment extends Fragment implements View.OnClickListener {
    Button exerciseButton;
    Button workoutButton;
    Button workoutPlanButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_activites, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Activities");

        exerciseButton = view.findViewById(R.id.exercise_button);
        workoutButton = view.findViewById(R.id.workout_button);
        workoutPlanButton = view.findViewById(R.id.workout_plan_button);

        exerciseButton.setOnClickListener(this);
        workoutButton.setOnClickListener(this);
        workoutPlanButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //creating fragment object
        Fragment fragment = null;
        Bundle bundle = new Bundle();

        switch (v.getId()) {
            case R.id.exercise_button:
                bundle.putSerializable("type", ExerciseModel.class);
                bundle.putString("title", "Exercises");
                fragment = new ActivitiesListFragment();
                fragment.setArguments(bundle);
                break;

            case R.id.workout_button:
                bundle.putSerializable("type", WorkoutModel.class);
                bundle.putString("title", "Workouts");
                fragment = new ActivitiesListFragment();
                fragment.setArguments(bundle);
                break;

            case R.id.workout_plan_button:
                bundle.putSerializable("type", WorkoutPlanModel.class);
                bundle.putString("title", "Workout Plans");
                fragment = new ActivitiesListFragment();
                fragment.setArguments(bundle);
                break;

            default:
                break;
        }

        //Replacing the fragment
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.switchFragment(fragment);
    }
}
