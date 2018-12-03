package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
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
        AsyncJsonTask<List<WorkoutModel>> task = new GetMyPlanTask(callback, type, userId);
        task.execute();
    }

}
