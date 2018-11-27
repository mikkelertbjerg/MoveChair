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
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ExerciseTask;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;

public class ExerciseFragment extends Fragment implements View.OnClickListener, ExerciseTask.AsyncJsonResponse {

    private ExerciseViewModel mExerciseViewModel;
    private UserModel user;

    //UI Elements
    VideoView exercise_video;
    TextView exercise_title;
    TextView exercise_description;
    TextView exercise_points;
    TextView exercise_duration;
    TextView exercise_category;
    TextView exercise_equipment;
    TextView exercise_muscle;


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

        //Get viewModel
        mExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);

        //Get activity object from fragment arguments
        ExerciseModel activity = (ExerciseModel) getArguments().getSerializable("activity");

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        //Pass the id of the activity to the ViewModel which delegates to task
        mExerciseViewModel.getExercise(this,activity.getId());

        //Find button and set onClick
        Button startExerciseButton = (Button) view.findViewById(R.id.start_exercise_button);
        startExerciseButton.setOnClickListener(this);

        //Instantiate ui elements
        exercise_video = view.findViewById(R.id.exercise_video);
        exercise_title = view.findViewById(R.id.exercise_title);
        exercise_description = view.findViewById(R.id.exercise_description);
        exercise_points = view.findViewById(R.id.exercise_points);
        exercise_duration = view.findViewById(R.id.exercise_duration);
        exercise_category = view.findViewById(R.id.exercise_category);
        exercise_equipment = view.findViewById(R.id.exercise_equipment);
        exercise_muscle = view.findViewById(R.id.exercise_muscles);
    }

    @Override
    public void onClick(View v) {
        //TODO Start activityGo
    }

    //This method is the callback for our ActivityListTask
    @Override
    public void processFinish(ExerciseModel res) {
        //TODO exercise_video
        exercise_title.setText("Title: " + res.getName());
        exercise_description.setText("Description: " + res.getDescription());
        exercise_points.setText("Points: " + Double.toString(res.getPoints()));
        exercise_duration.setText("Duration: " + Double.toString(res.getDuration()));
        exercise_category.setText("Category: " + res.getCategories());
        exercise_equipment.setText("Equipment: " + res.getEquipment());
        exercise_muscle.setText("Muscle(s): " + res.getMuscles());
    }

}
