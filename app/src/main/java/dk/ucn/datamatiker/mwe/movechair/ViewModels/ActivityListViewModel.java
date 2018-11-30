package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;

public class ActivityListViewModel extends AndroidViewModel {

    private ActivityListTask.AsyncJsonResponse callback;

    public ActivityListViewModel(@NonNull Application application) {
        super(application);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void getActivities(AsyncJsonTask.AsyncJsonResponse callback, Type activityType) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        ActivityListTask task = new ActivityListTask(callback, activityType);
        task.execute();
    }
}
