package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ParameterVisualizationModelListTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.SessionLogListTask;

public class ParameterVisualizationViewModel extends AndroidViewModel {

    public ParameterVisualizationViewModel(@NonNull Application application) {
        super(application);
    }

    private AsyncJsonTask.AsyncJsonResponse callback;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void getParameterVisualizationModels(AsyncJsonTask.AsyncJsonResponse callback, Type type) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        ParameterVisualizationModelListTask task = new ParameterVisualizationModelListTask(callback, type);
        task.execute();
    }

    public List<ParameterVisualizationModel> getVisualizationModels(List<ParameterVisualizationModel> parameterVisualizationModels,List<ScalarModel> scalars) {
        List<ParameterVisualizationModel> visualizationModels = new ArrayList<>();

        for (ScalarModel s : scalars) {
            //TODO Time complexity is shyte, reconsider!

            boolean found = false;
            int i = 0;
            while(found != true && i < parameterVisualizationModels.size()) {
                if(parameterVisualizationModels.get(i).getUnit().toLowerCase().equals(s.getUnit().toLowerCase())) {
                    if(s.getValue() > parameterVisualizationModels.get(i).getThreshold()) {
                        ParameterVisualizationModel p = parameterVisualizationModels.get(i);
                        p.setValue(s.getValue());
                        visualizationModels.add(p);
                    }
                    found = true;
                }
                i++;
            }
        }

        return visualizationModels;
    }
}
