package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

public class ActivityListViewModel extends AndroidViewModel {

    private List<ExerciseModel> exercises;
    private List<WorkoutModel> workouts;
    private List<WorkoutPlanModel> workoutPlans;

    public ActivityListViewModel(@NonNull Application application) {
        super(application);
    }

    private List<ExerciseModel> getExercises() {
        //TODO Method that retrieves all ExerciseModels from DB
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



    /////////////////////////////////////////////////////////////////////////////////////////////////
    // MOFO WEED LINES // WHATTEN FLOR STYLE /
    private List<ActivityModel> activities;
    private ArrayAdapter<ActivityModel> mAdapter;

    public List<ActivityModel> getActivities() {
        if (activities == null) {
            activities = new ArrayList<ActivityModel>();
            loadActivities();
            mAdapter.clear();
            mAdapter.addAll(activities);
            mAdapter.notifyDataSetChanged();
        }
        return activities;
    }

    private void loadActivities() {
        this.activities = new DummyData().createExercises(4);
    }

    public void setActivities(List<ActivityModel> activities) {
        this.activities = activities;
        mAdapter.clear();
        mAdapter.addAll(activities);
        mAdapter.notifyDataSetChanged();
    }

    // MOFO WEED LINES // WHATTEN FLOR STYLE /
    /////////////////////////////////////////////////////////////////////////////////////////////////

}
