package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;

import dk.ucn.datamatiker.mwe.movechair.Tasks.AddSessionLogTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.SessionLogListTask;

public class SessionLogsViewModel extends ViewModel {
    private AsyncJsonTask.AsyncJsonResponse callback;


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void getSessionLogs(AsyncJsonTask.AsyncJsonResponse callback, Type type, int userId) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        SessionLogListTask task = new SessionLogListTask(callback, type, userId);
        task.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void addSessionLog(AsyncJsonTask.AsyncJsonResponse callback, Type type, int activityId) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        AddSessionLogTask task = new AddSessionLogTask(callback, type, activityId);
        task.execute();
    }
}
