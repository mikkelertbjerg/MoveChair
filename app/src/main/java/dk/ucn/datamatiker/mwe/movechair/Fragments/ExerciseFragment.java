package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;

public class ExerciseFragment extends Fragment {

    private ExerciseViewModel mExerciseViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_exercise_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);

        ExerciseModel exercise = (ExerciseModel) mExerciseViewModel.getItem(1);
        //This makes you able to change toolbar title
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Exercise name");
        //Get activity object from fragment arguments
        //TODO Replace this with db call to get specific exercise?

        VideoView exercise_video = view.findViewById(R.id.exercise_video);
        TextView exercise_title = view.findViewById(R.id.exercise_title);
        TextView exercise_description = view.findViewById(R.id.exercise_description);
        TextView exercise_points = view.findViewById(R.id.exercise_points);
        TextView exercise_duration = view.findViewById(R.id.exercise_duration);
        TextView exercise_category = view.findViewById(R.id.exercise_category);
        TextView exercise_equipment = view.findViewById(R.id.exercise_equipment);
        TextView exercise_muscle_group = view.findViewById(R.id.exercise_muscle_group);
        TextView exercise_difficulty = view.findViewById(R.id.exercise_difficulty);

        //TODO exercise_video
        exercise_title.setText("Title: " + exercise.getName());
        exercise_description.setText("Description: " + exercise.getDescription());
        exercise_points.setText("Points: " + Double.toString(exercise.getPoints()));
        exercise_duration.setText("Duration: " + Double.toString(exercise.getDuration()));
        exercise_category.setText("Category: " + getCategories(exercise));

/*      exercise_equipment.setText("Equipment: " + exercise.printEquipment());
        exercise_muscle_group.setText("Muscle group(s): " + exercise.printMuscleGroups());
        exercise_difficulty.setText("Difficulty: " + exercise.printDifficulties());*/

    }

    private String getCategories(ExerciseModel exercise){
        String categories = null;
        for (int i = 0; i < exercise.getCategories().size(); i++) {
            String temp;
            temp = exercise.getCategories().get(i).getName();

            if (exercise.getCategories().size() > 1) {
                categories += temp + ", ";
            } else {
                categories = temp;
            }
        }
        return categories;
    }
}
