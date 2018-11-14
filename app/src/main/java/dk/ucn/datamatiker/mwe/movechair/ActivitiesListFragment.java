package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;


public class ActivitiesListFragment extends Fragment implements View.OnClickListener {

    ArrayList<ActivityModel> activities;
    private String activityType;
    private ActivityModel activityModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_activities_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("buttonText"));
        RecyclerView rvActivities = view.findViewById(R.id.rv_activities);

        Button filterButton = (Button) view.findViewById(R.id.set_filters_button);
        filterButton.setOnClickListener(this);

        //TODO method should be replaced by getting data from DB:
        //TODO if statement that switches on ActivityTypeID fills the adapter list

        //TODO Delete: used to generate dummy data

        switch(getArguments().getString("buttonText")){
            case "Exercises":
                activities = createExercises(10);
                activityModel = activities.get(0);
                break;

            case "Workouts":
                activities = createWorkouts(5);
                activityModel = activities.get(0);
                break;

            case "Workout Plans":
                activities = createWorkoutPlans(5);
                activityModel = activities.get(0);
                break;

            default:
                break;
        }
        // Determine the activity type
        activityType = activities.get(0).getClass().getSimpleName();

        // Create adapter passing in the sample user data
        ActivityAdapter adapter = new ActivityAdapter(activities);

        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);

        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvActivities.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {
        FilterFragment filterFragment = new FilterFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ActivityModel", activityModel);
        filterFragment.setArguments(bundle);
        MainActivity mainActivity = (MainActivity) v.getContext();
        mainActivity.switchFragment(filterFragment);
    }

    private ArrayList<ActivityModel> getFilteredActivities(){
        ArrayList<ActivityModel> filteredActivities = new ArrayList<>();
            switch(activityType){
                case "ExerciseModel":
                    for(int i = 0; i < activities.size(); i++) {
                        //Do something with user input
                    }
                    break;

                case "WorkoutModel":
                    for(int i = 0; i < activities.size(); i++) {
                        //Do something with user input
                    }
                    break;

                case "WorkoutPlanModel":
                    for(int i = 0; i < activities.size(); i++) {
                        //Do something with user input
                    }
                    break;
            }
        return filteredActivities;
    }



    private ArrayList<MuscleModel> createMuscles(int numActivities){
        ArrayList<MuscleModel> muscles = new ArrayList<MuscleModel>();

        for(int i = 0; i < numActivities; i++){
            muscles.add(new MuscleModel("Muscle" + (i+1)));
        }
        return muscles;
    }
    private ArrayList<MuscleGroupModel> createMuscleGroups(int numActivities){
        ArrayList<MuscleGroupModel> muscleGroups = new ArrayList<MuscleGroupModel>();

        for(int i = 0; i < numActivities; i++){
            muscleGroups.add(new MuscleGroupModel("Muscle Group" + (i+1), createMuscles(3)));
        }
        return muscleGroups;
    }
    private ArrayList<EquipmentModel> createEquipment(int numActivities){
        ArrayList<EquipmentModel> equipment = new ArrayList<EquipmentModel>();

        for(int i = 0; i < numActivities; i++){
            equipment.add(new EquipmentModel("Equipment" + (i+1)));
        }
        return equipment;
    }
    private ArrayList<ActivityModel> createExercises(int numActivities) {
        ArrayList<ActivityModel> exercises = new ArrayList<ActivityModel>();

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
    private ArrayList<ActivityModel> createWorkouts(int numActivities){
        ArrayList<ActivityModel> workouts = new ArrayList<ActivityModel>();

        for(int i = 0; i < numActivities; i++){
            workouts.add(new WorkoutModel("Workout " + (i+1), "Description " + (i+1), i, createExercises(2)));
        }
        return workouts;
    }
    private ArrayList<ActivityModel> createWorkoutPlans(int numActivities){
        ArrayList<ActivityModel> workoutPlans = new ArrayList<ActivityModel>();

        for(int i = 0; i < numActivities; i++){
            workoutPlans.add(new WorkoutPlanModel("Workout Plan " + (i+1), "Description " + (i+1), i, 3, createWorkouts(2)));
        }
        return workoutPlans;
    }
    //TODO DELETE THIS
    private ArrayList<ActivityModel> createDummyData() {
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
