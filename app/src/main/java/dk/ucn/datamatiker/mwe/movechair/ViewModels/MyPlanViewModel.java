package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetMyPlanTask;

public class MyPlanViewModel extends AndroidViewModel {


    // Constructor parses the application context
    public MyPlanViewModel(@NonNull Application application) {
        super(application);
    }

    // Callbacks
    private AsyncJsonTask.AsyncJsonResponse getMyplanCallback;

    /**
     * Returns the user's current workout plans through the provided callback.
     * @param userId
     * @return void
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void getMyPlan(AsyncJsonTask.AsyncJsonResponse callback, Type type, int userId) {
        this.getMyplanCallback = callback;
        AsyncJsonTask<List<WorkoutPlanModel>> task = new GetMyPlanTask(callback, type, userId);
        task.execute();
    }




   /* private ActivityModel createDummyData() {
        WorkoutPlanModel wp = new WorkoutPlanModel();
        wp.setName("My First Workoutplan");
        wp.setWorkoutPlanDuration(10);
        wp.getWorkouts().get(1).;
        wp.setId(1);

        ArrayList<MediaModel> media = new ArrayList<>();
        ArrayList<DifficultyModel> difficulties = new ArrayList<>();
        ArrayList<CategoryModel> categories = new ArrayList<>();
        ArrayList<MuscleGroupModel> muscleGroups = new ArrayList<>();
        ArrayList<EquipmentModel> equipment = new ArrayList<>();

        *//* DUMMY DATA - EQUIPMENT MODELS*//*
        EquipmentModel barbell = new EquipmentModel("Barbell");
        equipment.add(barbell);

        *//* DUMMY DATA - DIFFICULTY MODELS*//*
        DifficultyModel beginner = new DifficultyModel("Beginner", 1);
        difficulties.add(beginner);

        *//* DUMMY DATA - CATEGORY MODELS*//*
        CategoryModel hypertrophy = new CategoryModel("Hypertrophy");
        categories.add(hypertrophy);

        *//* DUMMY DATA - MUSCLE MODELS *//*
        MuscleModel biceps = new MuscleModel("biceps");

        *//* DUMMY DATA - MUSCLEGROUP MODELS*//*
        MuscleGroupModel arm = new MuscleGroupModel("Arm");
        arm.getMuscles().add(biceps);
        muscleGroups.add(arm);

        *//* DUMMY DATA - MEDIA TYPE MODELS*//*
        MediaTypeModel video = new MediaTypeModel("Video");
        MediaTypeModel audio = new MediaTypeModel("Audio");
        MediaModel media1 = new MediaModel("diller", video);
        MediaModel media2 = new MediaModel("penis", audio);
        media.add(media1);
        media.add(media2);

        ArrayList<ActivityModel> exercises = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            ExerciseModel e = new ExerciseModel("Exercise", "Description", 1,2,3,
                    media,difficulties,categories,muscleGroups,equipment);
            exercises.add(e);
        }

        ArrayList<ActivityModel> workouts = new ArrayList<>();
        for(int i = 0; i < 12;i++) {
            WorkoutModel wo = new WorkoutModel("Workout","Workout description",1,exercises);
            workouts.add(wo);
        }

        wp.setWorkouts(workouts);


        return wp;*/

}
