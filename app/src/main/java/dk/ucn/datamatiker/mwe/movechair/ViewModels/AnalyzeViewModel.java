package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.SessionLogListTask;

@RequiresApi(api = Build.VERSION_CODES.P)
public class AnalyzeViewModel extends AndroidViewModel {

    private List<SessionLogModel> sessionLogs;
    private AsyncJsonTask.AsyncJsonResponse callback;

    public AnalyzeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getSessionLogs(AsyncJsonTask.AsyncJsonResponse callback, Type type, int userId) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        SessionLogListTask task = new SessionLogListTask(callback, type, userId);
        task.execute();
    }

    public List<ScalarModel> getScalars(List<SessionLogModel> sessionLogs) {

        int points = 0;
        List<ScalarModel> scalars = new ArrayList<>();
        /*
        int calories = 0;
        int distance = 0;
        int weight = 0;
         */
        for (int i = 0; i < sessionLogs.size(); i++) {

            if (sessionLogs.get(i).getActivity().getPoints() > 0)
                points += sessionLogs.get(i).getActivity().getPoints();

            /*
            if (sessionLogs.get(i).getActivity().getCalories() > 0)
                calories += sessionLogs.get(i).getActivity().getCalories();

            if (sessionLogs.get(i).getActivity().getDistance() > 0)
                distance += sessionLogs.get(i).getActivity().getDistance();

            if (sessionLogs.get(i).getActivity().getEquipment() != null)
            weight += sessionLogs.get(i).getActivity().getEquipment().getWeight();
            */
        }
        ScalarModel pointsScalar = new ScalarModel("Points", points);
        scalars.add(pointsScalar);

        return scalars;
    }
}
