package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WorkoutViewModel extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_workout_view, container, false);
    }

    //We need to show chosen exercise based on ID from Bundle

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        WorkoutModel activity = (WorkoutModel) getArguments().getSerializable("activity");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("buttonText"));

        WorkoutModel workoutModel = new WorkoutModel(activity.getName(),
                activity.getDescription(),
                activity.getId(),
                activity.getExercises()
        );

        TextView workout_title = view.findViewById(R.id.workout_title);
        TextView workout_duration = view.findViewById(R.id.workout_duration);
        //TODO Do we need theese props? Not according to our domain
        //TextView workout_category = view.findViewById(R.id.workout_category);
        //TextView workout_difficulty = view.findViewById(R.id.workout_difficulty);
        //TextView workout_muscle_group = view.findViewById(R.id.workout_muscle_group);
        //TextView workout_equipment = view.findViewById(R.id.workout_equipment);
        TextView workout_description = view.findViewById(R.id.workout_description);

        workout_title.setText("Title: " + workoutModel.getName());
        workout_duration.setText("Duration: 5"); //TODO Add to model
        workout_description.setText("Description: " + workoutModel.getName());





    }
}

