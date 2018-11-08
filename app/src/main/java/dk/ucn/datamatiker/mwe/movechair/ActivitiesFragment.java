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
        Button exercisesButton = (Button) view.findViewById(R.id.exercises_button);
        Button workoutsButton = (Button) view.findViewById(R.id.workouts_button);
        Button workoutPlansButton = (Button) view.findViewById(R.id.workout_plans_button);
        exercisesButton.setOnClickListener(this);
        workoutsButton.setOnClickListener(this);
        workoutPlansButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //creating fragment object
        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.exercises_button:
                fragment = new ActivitiesListFragment();
                break;

            case R.id.workouts_button:
                fragment = new ActivitiesListFragment();
                break;

            case R.id.workout_plans_button:
                fragment = new ActivitiesListFragment();
                break;

            default:
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();

            ft.replace(R.id.activities_menu_layout, fragment);
            //ft.addToBackStack(null);

            ft.commit();
        }
    }

}
