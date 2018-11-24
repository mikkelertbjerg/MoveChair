package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.text.ParseException;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;

public class ExerciseFragment extends Fragment implements View.OnClickListener {

    private ExerciseViewModel mExerciseViewModel;
    //TODO DELETE THEESE/TESTING PURPOSE
    private ExerciseModel exercise;
    private UserModel user;


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

        //TODO DELETE THIS DUMMY DATA
        user = new DummyData().createUser(2,5);

        //Get activity object from fragment arguments
        ExerciseModel activity = (ExerciseModel) getArguments().getSerializable("activity");

        Button startExerciseButton = (Button) view.findViewById(R.id.start_exercise_button);
        startExerciseButton.setOnClickListener(this);

        mExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);

        exercise = (ExerciseModel) mExerciseViewModel.getItem(activity.getId());
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(exercise.getName());

        VideoView exercise_video = view.findViewById(R.id.exercise_video);
        TextView exercise_title = view.findViewById(R.id.exercise_title);
        TextView exercise_description = view.findViewById(R.id.exercise_description);
        TextView exercise_points = view.findViewById(R.id.exercise_points);
        TextView exercise_duration = view.findViewById(R.id.exercise_duration);
        TextView exercise_category = view.findViewById(R.id.exercise_category);
        TextView exercise_equipment = view.findViewById(R.id.exercise_equipment);
        TextView exercise_muscle = view.findViewById(R.id.exercise_muscles);

        //TODO exercise_video
        exercise_title.setText("Title: " + exercise.getName());
        exercise_description.setText("Description: " + exercise.getDescription());
        exercise_points.setText("Points: " + Double.toString(exercise.getPoints()));
        exercise_duration.setText("Duration: " + Double.toString(exercise.getDuration()));
        exercise_category.setText("Category: " + exercise.getCategories());
        exercise_equipment.setText("Equipment: " + exercise.getEquipment());
        exercise_muscle.setText("Muscle(s): " + exercise.getMuscles());

    }

    @Override
    public void onClick(View v) {
        //TODO Start activityGo
        try {
            mExerciseViewModel.addActivityToUser(user, exercise);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
