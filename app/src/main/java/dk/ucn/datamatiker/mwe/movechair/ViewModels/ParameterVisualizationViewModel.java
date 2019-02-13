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
import dk.ucn.datamatiker.mwe.movechair.Tasks.GetParameterVisualizationModelByThresholdTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.LoadPVMImageTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ParameterVisualizationModelListTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.SessionLogListTask;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ParameterVisualizationViewModel extends AndroidViewModel {

    public ParameterVisualizationViewModel(@NonNull Application application) {
        super(application);
    }

    private AsyncJsonTask.AsyncJsonResponse callback;

    public void getParameterVisualizationModels(AsyncJsonTask.AsyncJsonResponse callback, Type type) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.callback = callback;
        ParameterVisualizationModelListTask task = new ParameterVisualizationModelListTask(callback, type);
        task.execute();
    }

    public void getPVMImage(AsyncJsonTask.AsyncJsonResponse callback, String url) {
        this.callback = callback;
        LoadPVMImageTask task = new LoadPVMImageTask(callback, url);
        task.execute();
    }

    public void getParameterVisualizationModelByThreshold(AsyncJsonTask.AsyncJsonResponse callback, Type type, float value){
        this.callback = callback;
        GetParameterVisualizationModelByThresholdTask task = new GetParameterVisualizationModelByThresholdTask(callback, type, value);
        task.execute();
    }

    public List<ParameterVisualizationModel> getVisualizationModels(List<ParameterVisualizationModel> parameterVisualizationModels, List<ScalarModel> scalars) {
        List<ParameterVisualizationModel> visualizationModels = new ArrayList<>();

        for (ScalarModel s : scalars) {
            //TODO Time complexity is shyte, reconsider!
            for (ParameterVisualizationModel pvm : parameterVisualizationModels) {
                if (pvm.getUnit().toLowerCase().equals(s.getUnit().toLowerCase())) {
                    if (s.getValue() > pvm.getThreshold()) {
                        pvm.setValue(s.getValue());
                        visualizationModels.add(pvm);
                    }
                }
            }
        }
            return visualizationModels;
    }
}
