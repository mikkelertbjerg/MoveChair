package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.arch.lifecycle.ViewModel;

import dk.ucn.datamatiker.mwe.movechair.Tasks.SessionLogListTask;

public class SessionLogsViewModel extends ViewModel {
    private SessionLogListTask.AsyncJson getSessionLogsCallback;


    public void getSessionLogs(SessionLogListTask.AsyncJson callback, int userId) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.getSessionLogsCallback = callback;
        SessionLogListTask task = new SessionLogListTask(callback, userId);
        task.execute();
    }
}
