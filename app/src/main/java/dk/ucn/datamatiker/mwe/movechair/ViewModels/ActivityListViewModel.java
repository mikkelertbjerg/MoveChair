package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask;

public class ActivityListViewModel extends AndroidViewModel {

    private List<ExerciseModel> exercises;
    private List<WorkoutModel> workouts;
    private List<WorkoutPlanModel> workoutPlans;

    private ActivityListTask.AsyncJsonResponse callback;

    public ActivityListViewModel(@NonNull Application application) {
        super(application);
    }

    private List<ExerciseModel> getExercises(String activityType, ActivityListTask.AsyncJsonResponse callback) {
        //TODO Method that retrieves all ExerciseModels from DB
        this.callback = callback;
        ActivityListTask task = new ActivityListTask(callback, activityType);
        return null;
    }
    private List<WorkoutModel> getWorkouts() {
        //TODO Method that retrieves all WorkoutModels from DB
        return null;
    }
    private List<WorkoutPlanModel> getWorkoutPlans() {
        //TODO Method that retrieves all WorkoutModels from DB
       return null;
    }




}
