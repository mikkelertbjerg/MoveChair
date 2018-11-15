package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ActivitiesFragment extends Fragment implements View.OnClickListener {
    Button exercisesButton;
    Button workoutsButton;
    Button workoutPlansButton;

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

        workoutsButton = view.findViewById(R.id.workouts_button);
        exercisesButton = view.findViewById(R.id.exercises_button);
        workoutPlansButton = view.findViewById(R.id.workout_plans_button);

        exercisesButton.setOnClickListener(this);
        workoutsButton.setOnClickListener(this);
        workoutPlansButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //creating fragment object
        Fragment fragment = null;
        String buttonText;
        Bundle bundle = new Bundle();

        switch (v.getId()) {
            case R.id.exercises_button:
                buttonText = (String) exercisesButton.getText();
                bundle.putString("buttonText", buttonText);
                fragment = new ActivitiesListFragment();
                fragment.setArguments(bundle);
                break;

            case R.id.workouts_button:
                buttonText = (String) workoutsButton.getText();
                bundle.putString("buttonText", buttonText);
                fragment = new ActivitiesListFragment();
                fragment.setArguments(bundle);
                break;

            case R.id.workout_plans_button:
                buttonText = (String) workoutPlansButton.getText();
                bundle.putString("buttonText", buttonText);
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
