package dk.ucn.datamatiker.mwe.movechair.Test;

import java.util.ArrayList;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.CategoryModel;
import dk.ucn.datamatiker.mwe.movechair.Models.DifficultyModel;
import dk.ucn.datamatiker.mwe.movechair.Models.EquipmentModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.MediaTypeModel;
import dk.ucn.datamatiker.mwe.movechair.Models.MuscleGroupModel;
import dk.ucn.datamatiker.mwe.movechair.Models.MuscleModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;

public class DummyData {

    public ArrayList<MuscleModel> createMuscles(int numActivities){
        ArrayList<MuscleModel> muscles = new ArrayList<MuscleModel>();

        for(int i = 0; i < numActivities; i++){
            muscles.add(new MuscleModel("Muscle" + (i+1)));
        }
        return muscles;
    }
    public ArrayList<MuscleGroupModel> createMuscleGroups(int numActivities){
        ArrayList<MuscleGroupModel> muscleGroups = new ArrayList<MuscleGroupModel>();

        for(int i = 0; i < numActivities; i++){
            muscleGroups.add(new MuscleGroupModel("Muscle Group" + (i+1), createMuscles(3)));
        }
        return muscleGroups;
    }
    public ArrayList<EquipmentModel> createEquipment(int numActivities){
        ArrayList<EquipmentModel> equipment = new ArrayList<EquipmentModel>();

        for(int i = 0; i < numActivities; i++){
            equipment.add(new EquipmentModel("Equipment" + (i+1)));
        }
        return equipment;
    }
    public ArrayList<ExerciseModel> createExercises(int numActivities) {
        ArrayList<ExerciseModel> exercises = new ArrayList<ExerciseModel>();

        /* DUMMY DATA - DIFFICULTY MODELS*/
        DifficultyModel beginner = new DifficultyModel("Beginner", 1);
        DifficultyModel intermediate = new DifficultyModel("Intermediate", 1.25);
        DifficultyModel advanced = new DifficultyModel("Advanced", 1.5);
        ArrayList<DifficultyModel> difficulties = new ArrayList<>();
        difficulties.add(beginner);
        difficulties.add(intermediate);
        difficulties.add(advanced);

        /* DUMMY DATA - CATEGORY MODELS*/
        CategoryModel hypertrophy = new CategoryModel("Hypertrophy");
        CategoryModel cardio = new CategoryModel("Cardio");
        CategoryModel strength = new CategoryModel("Strength");
        ArrayList<CategoryModel> categories = new ArrayList<>();
        categories.add(hypertrophy);
        categories.add(cardio);
        categories.add(strength);


        for (int i = 0; i < numActivities; i++) {
            exercises.add(new ExerciseModel("Exercise" + (i + 1), "Description " + (i + 1), i, 1, 90, null,
                    difficulties, categories, createMuscleGroups(2), createEquipment(2)));
        }
        return exercises;
    }
    public ArrayList<WorkoutModel> createWorkouts(int numActivities){
        ArrayList<WorkoutModel> workouts = new ArrayList<WorkoutModel>();

        for(int i = 0; i < numActivities; i++){
            workouts.add(new WorkoutModel("Workout " + (i+1), "Description " + (i+1), i, i*10, i*2, createExercises(2)));
        }
        return workouts;
    }
    public ArrayList<WorkoutPlanModel> createWorkoutPlans(int numActivities){
        ArrayList<WorkoutPlanModel> workoutPlans = new ArrayList<WorkoutPlanModel>();

        for(int i = 0; i < numActivities; i++){
            workoutPlans.add(new WorkoutPlanModel("Workout Plan " + (i+1), "Description " + (i+1), i, i*10, i*2, createWorkouts(2)));
        }
        return workoutPlans;
    }

    public ArrayList<ActivityModel> createDummyData() {
        ArrayList<ActivityModel> activities = new ArrayList<ActivityModel>();
        ArrayList<ExerciseModel> armBlaster = new ArrayList<ExerciseModel>();
        ArrayList<ExerciseModel> shoulderBlaster = new ArrayList<ExerciseModel>();
        ArrayList<ExerciseModel> chestBlaster = new ArrayList<ExerciseModel>();
        ArrayList<ExerciseModel> backBlaster = new ArrayList<ExerciseModel>();
        ArrayList<WorkoutModel> projectArms = new ArrayList<WorkoutModel>();


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
        ExerciseModel curls = new ExerciseModel("Bicep curl", "Raise the arm from the hip, towards the shoulder. Pin the elbow, and tighten the core", 1, 1, 90);
        curls.getEquipment().add(dumbbell);
        curls.getDifficulties().add(beginner);
        curls.getCategories().add(hypertrophy);
        curls.getMuscleGroups().add(arm);

        ExerciseModel tricepOverHeadExtensions = new ExerciseModel("Tricep Over Head Extensions", "Extend your triceps over your head!", 2, 1, 90);
        tricepOverHeadExtensions.getEquipment().add(barbell);
        tricepOverHeadExtensions.getDifficulties().add(intermediate);
        tricepOverHeadExtensions.getCategories().add(hypertrophy);
        tricepOverHeadExtensions.getMuscleGroups().add(arm);

        ExerciseModel dumbbellRows = new ExerciseModel("Dumbbell Rows", "Row with dumbbells!", 3, 1, 90);
        dumbbellRows.getEquipment().add(dumbbell);
        dumbbellRows.getDifficulties().add(intermediate);
        dumbbellRows.getCategories().add(stregth);
        dumbbellRows.getMuscleGroups().add(back);
        //dumbbellRows.getMuscleGroups().add(arm); //Do we allow multiple muscle groups in one exercises (it wouldn't be wrong).

        ExerciseModel barbellRows = new ExerciseModel("Barbell Rows", "Row with a barbell!", 4, 2, 90);
        barbellRows.getEquipment().add(barbell);
        barbellRows.getDifficulties().add(advanced);
        barbellRows.getCategories().add(stregth);
        barbellRows.getMuscleGroups().add(back);

        ExerciseModel dumbbellShoulderPress = new ExerciseModel("Dumbbell Shoulder Press", "Press dumbbells with your shoulders!", 5, 1, 90);
        dumbbellShoulderPress.getEquipment().add(dumbbell);
        dumbbellShoulderPress.getDifficulties().add(intermediate);
        dumbbellShoulderPress.getCategories().add(hypertrophy);
        dumbbellShoulderPress.getMuscleGroups().add(shoulder);

        ExerciseModel barbellShoulderPress = new ExerciseModel("Barbell Shoulder Press", "Press a barbell with your shoulders!", 6, 2, 90);
        barbellShoulderPress.getEquipment().add(barbell);
        barbellShoulderPress.getDifficulties().add(advanced);
        barbellShoulderPress.getCategories().add(stregth);
        barbellShoulderPress.getMuscleGroups().add(shoulder);

        ExerciseModel dumbbellChestPress = new ExerciseModel("Dumbbell Chest Press", "Press dumbbells with your chest", 7, 1, 90);
        dumbbellChestPress.getEquipment().add(dumbbell);
        dumbbellChestPress.getDifficulties().add(intermediate);
        dumbbellChestPress.getCategories().add(hypertrophy);
        dumbbellChestPress.getMuscleGroups().add(chest);

        ExerciseModel benchPress = new ExerciseModel("Bench Press", "Press a barbell with your chest", 8, 3, 90);
        benchPress.getEquipment().add(barbell);
        benchPress.getDifficulties().add(advanced);
        benchPress.getCategories().add(stregth);
        benchPress.getMuscleGroups().add(chest);

        armBlaster.add(curls);
        armBlaster.add(tricepOverHeadExtensions);

        chestBlaster.add(benchPress);
        chestBlaster.add(dumbbellChestPress);

        shoulderBlaster.add(barbellShoulderPress);
        shoulderBlaster.add(dumbbellShoulderPress);

        backBlaster.add(barbellRows);
        backBlaster.add(dumbbellRows);


        return activities;
    }

}
