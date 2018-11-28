package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import dk.ucn.datamatiker.mwe.movechair.Tasks.DailyLogTask;

public class DailyLogViewModel extends AndroidViewModel {

    private DailyLogTask.AsyncJson callback;

    public DailyLogViewModel(@NonNull Application application) { super(application); }

    public void getDailyLogsByUserId(int id, DailyLogTask.AsyncJson callback) {
        this.callback = callback;
        DailyLogTask task = new DailyLogTask(callback, id);
        task.execute();
    }


}
