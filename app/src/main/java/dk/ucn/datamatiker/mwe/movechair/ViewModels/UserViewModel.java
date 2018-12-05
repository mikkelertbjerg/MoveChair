package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.CreateUserTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.LoginTask;

@RequiresApi(api = Build.VERSION_CODES.P)
public class UserViewModel extends AndroidViewModel {
    private AsyncJsonTask.AsyncJsonResponse callback;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }


    public void create(AsyncJsonTask.AsyncJsonResponse callback, Type type, String email, String password){
        this.callback = callback;
        CreateUserTask task = new CreateUserTask(callback, type, email, password);
        task.execute();
    }

    public void login(AsyncJsonTask.AsyncJsonResponse callback, Type type, String email, String password){
        this.callback = callback;
        LoginTask task = new LoginTask(callback, type, email, password);
        task.execute();
    }
}
