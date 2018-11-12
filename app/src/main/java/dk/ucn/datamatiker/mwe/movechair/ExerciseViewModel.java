package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class ExerciseViewModel extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_exercise_view, container, false);
    }

    //We need to show chosen exercise based on ID from Bundle

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        ActivityModel activity = (ActivityModel)getArguments().getSerializable("activity");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        //TODO Replace this with db call to get specific exercise?
        ExerciseModel exerciseModel = new ExerciseModel(activity.getName(), activity.getDescription(),activity.getId());
        exerciseModel.setPoints(5);
        CategoryModel category = new CategoryModel("Weightloss");
        EquipmentModel equipment = new EquipmentModel("Kettlebell");
        MuscleModel muscle = new MuscleModel("Bicep");
        MuscleGroupModel muscleGroup = new MuscleGroupModel("Arms");
        muscleGroup.getMuscles().add(muscle);
        DifficultyModel difficulty = new DifficultyModel("Easy", 1);

        exerciseModel.getCategories().add(category);
        exerciseModel.getEquipment().add(equipment);
        exerciseModel.getDifficulties().add(difficulty);
        exerciseModel.getMuscleGroups().add(muscleGroup);

        //VideoView exercise_video = view.findViewById(R.id.exercise_video);
        TextView exercise_title = view.findViewById(R.id.exercise_title);
        TextView exercise_description = view.findViewById(R.id.exercise_description);
        TextView exercise_points = view.findViewById(R.id.exercise_points);
        TextView exercise_category = view.findViewById(R.id.exercise_category);
        TextView exercise_equipment = view.findViewById(R.id.exercise_equipment);
        TextView exercise_musclegroup = view.findViewById(R.id.exercise_muscle_group);
        TextView exercise_difficulty = view.findViewById(R.id.exercise_difficulty);

        //TODO exercise_video
        exercise_title.setText("Title: " + exerciseModel.getName());
        exercise_description.setText("Description: " + exerciseModel.getDescription());
        exercise_points.setText("Points: " + Double.toString(exerciseModel.getPoints()));
        exercise_category.setText("Category: " + exerciseModel.getCategories().get(0).getName());
        exercise_equipment.setText("Equipment: " + exerciseModel.getEquipment().get(0).getName());
        exercise_musclegroup.setText("Muscle group(s): " + exerciseModel.getMuscleGroups().get(0).getName());
        exercise_difficulty.setText("Difficulty: " + exerciseModel.getDifficulties().get(0).getName());
    }
}
