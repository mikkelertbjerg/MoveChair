package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask;

public class ActivityListViewModel extends AndroidViewModel {

    private ActivityListTask.AsyncJsonResponse callback;

    public ActivityListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getActivities(String activityType, ActivityListTask.AsyncJsonResponse callback) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        ActivityListTask task = new ActivityListTask(callback, activityType);
        task.execute();
    }
}
