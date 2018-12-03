package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Type;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetMyPlanTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetNextWorkoutTask;

public class HomeViewModel extends AndroidViewModel {
    private AsyncJsonTask.AsyncJsonResponse callback;


    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LineGraphSeries<DataPoint> getStrides() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 125),
                new DataPoint(1, 521),
                new DataPoint(2, 353),
                new DataPoint(3, 2156),
                new DataPoint(4, 66),
                new DataPoint(5, 220),
                new DataPoint(6, 23)
        });
        return series;
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void getNextWorkout(AsyncJsonTask.AsyncJsonResponse callback, Type type, int userId) {
        this.callback = callback;
        AsyncJsonTask<WorkoutModel> task = new GetNextWorkoutTask(callback, type, userId);
        task.execute();
    }
}
