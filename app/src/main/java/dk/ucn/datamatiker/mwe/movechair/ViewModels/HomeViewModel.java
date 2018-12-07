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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.StridesModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetMyPlanTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetNextWorkoutTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetSridesListTask;

@RequiresApi(api = Build.VERSION_CODES.P)
public class HomeViewModel extends AndroidViewModel {
    private AsyncJsonTask.AsyncJsonResponse callback;


    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LineGraphSeries<DataPoint> getStrides(List<StridesModel> strides) {
        DataPoint[] dataPoints = new DataPoint[strides.size()];
        for(int i = 0; i < strides.size(); i++) {
                DataPoint d = new DataPoint(strides.get(i).getDate(), strides.get(i).getAmount());
                dataPoints[i] = d;
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        return series;
    }

    public void getNextWorkout(AsyncJsonTask.AsyncJsonResponse callback, Type type, int userId) {
        this.callback = callback;
        AsyncJsonTask<WorkoutModel> task = new GetNextWorkoutTask(callback, type, userId);
        task.execute();
    }

    public void getAllStridesFromUser(AsyncJsonTask.AsyncJsonResponse callback, Type type, int userId) {
        this.callback = callback;
        AsyncJsonTask<List<StridesModel>> task = new GetSridesListTask(callback, type, userId);
        task.execute();
    }
}
