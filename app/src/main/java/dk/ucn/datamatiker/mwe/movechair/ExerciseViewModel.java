package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        ExerciseModel exerciseModel = new ExerciseModel(activity.getName(), activity.getDescription(),activity.getId(), 5);
        CategoryModel category = new CategoryModel("Cardio");
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

        //TODO DELETE THIS
        /* DUMMY DATA - EQUIPMENT MODELS*/
        EquipmentModel barbell = new EquipmentModel("Barbell");
        EquipmentModel dumbbell = new EquipmentModel("Dumbbell");
        EquipmentModel kettlebell = new EquipmentModel("Kettlebell");

        /* DUMMY DATA - DIFFICULTY MODELS*/
        DifficultyModel beginner = new DifficultyModel("Beginner", 1);
        DifficultyModel intermediate = new DifficultyModel("Intermediate", 1.25);
        DifficultyModel advanced = new DifficultyModel("Advanced", 1.5);

        /* DUMMY DATA - CATEGORY MODELS*/
        CategoryModel hypertrophy = new CategoryModel("Hypertrophy");
        CategoryModel cardio = new CategoryModel("Cardio");
        CategoryModel stregth = new CategoryModel("Strength");

        /* DUMMY DATA - MUSCLE MODELS */
        MuscleModel biceps = new MuscleModel("biceps");
        MuscleModel triceps = new MuscleModel("triceps");
        MuscleModel frontDelt = new MuscleModel("front deltoid");
        MuscleModel deltoid = new MuscleModel("deltoid");
        MuscleModel backDelt = new MuscleModel("back deltoid");
        MuscleModel pectorals = new MuscleModel("pectorals");
        MuscleModel upperPecs = new MuscleModel("upper pectorals");
        MuscleModel lowerPecs = new MuscleModel("lower pectorals");
        MuscleModel trapezius = new MuscleModel("trapezius");
        MuscleModel rhomboids = new MuscleModel("rhomboids");

        /* DUMMY DATA - MUSCLEGROUP MODELS*/
        MuscleGroupModel arm = new MuscleGroupModel("Arm");
        arm.getMuscles().add(biceps);
        arm.getMuscles().add(triceps);
        MuscleGroupModel shoulder = new MuscleGroupModel("Shoulder");
        shoulder.getMuscles().add(frontDelt);
        shoulder.getMuscles().add(deltoid);
        shoulder.getMuscles().add(backDelt);
        MuscleGroupModel back = new MuscleGroupModel("Back");
        back.getMuscles().add(trapezius);
        back.getMuscles().add(rhomboids);
        MuscleGroupModel chest = new MuscleGroupModel("Chest");
        chest.getMuscles().add(pectorals);
        chest.getMuscles().add(upperPecs);
        chest.getMuscles().add(lowerPecs);

        /* DUMMY DATA - MEDIA TYPE MODELS*/
        MediaTypeModel video = new MediaTypeModel("Video");
        MediaTypeModel audio = new MediaTypeModel("Audio");

        /* DUMMY DATA EXERCISE MODELS */
        ExerciseModel curls = new ExerciseModel("Bicep curl", "Raise the arm from the hip, towards the shoulder. Pin the elbow, and tighten the core", 1, 1);
        curls.getEquipment().add(dumbbell);
        curls.getDifficulties().add(beginner);
        curls.getCategories().add(hypertrophy);
        curls.getMuscleGroups().add(arm);

        ExerciseModel tricepOverHeadExtensions = new ExerciseModel("Tricep Over Head Extensions", "Extend your triceps over your head!", 2, 1);
        tricepOverHeadExtensions.getEquipment().add(barbell);
        tricepOverHeadExtensions.getDifficulties().add(intermediate);
        tricepOverHeadExtensions.getCategories().add(hypertrophy);
        tricepOverHeadExtensions.getMuscleGroups().add(arm);

        ExerciseModel dumbbellRows = new ExerciseModel("Dumbbell Rows", "Row with dumbbells!", 3, 1);
        dumbbellRows.getEquipment().add(dumbbell);
        dumbbellRows.getDifficulties().add(intermediate);
        dumbbellRows.getCategories().add(stregth);
        dumbbellRows.getMuscleGroups().add(back);
        //dumbbellRows.getMuscleGroups().add(arm); //Do we allow multiple muscle groups in one exercises (it wouldn't be wrong).

        ExerciseModel barbellRows = new ExerciseModel("Barbell Rows", "Row with a barbell!", 4, 2);
        barbellRows.getEquipment().add(barbell);
        barbellRows.getDifficulties().add(advanced);
        barbellRows.getCategories().add(stregth);
        barbellRows.getMuscleGroups().add(back);

        ExerciseModel dumbbellShoulderPress = new ExerciseModel("Dumbbell Shoulder Press", "Press dumbbells with your shoulders!", 5, 1);
        dumbbellShoulderPress.getEquipment().add(dumbbell);
        dumbbellShoulderPress.getDifficulties().add(intermediate);
        dumbbellShoulderPress.getCategories().add(hypertrophy);
        dumbbellShoulderPress.getMuscleGroups().add(shoulder);

        ExerciseModel barbellShoulderPress = new ExerciseModel("Barbell Shoulder Press", "Press a barbell with your shoulders!", 6, 2);
        barbellShoulderPress.getEquipment().add(barbell);
        barbellShoulderPress.getDifficulties().add(advanced);
        barbellShoulderPress.getCategories().add(stregth);
        barbellShoulderPress.getMuscleGroups().add(shoulder);

        ExerciseModel dumbbellChestPress = new ExerciseModel("Dumbbell Chest Press", "Press dumbbells with your chest", 7, 1);
        dumbbellChestPress.getEquipment().add(dumbbell);
        dumbbellChestPress.getDifficulties().add(intermediate);
        dumbbellChestPress.getCategories().add(hypertrophy);
        dumbbellChestPress.getMuscleGroups().add(chest);

        ExerciseModel benchPress = new ExerciseModel("Bench Press", "Press a barbell with your chest", 8, 3);
        benchPress.getEquipment().add(barbell);
        benchPress.getDifficulties().add(advanced);
        benchPress.getCategories().add(stregth);
        benchPress.getMuscleGroups().add(chest);
    }
}
