package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;

public class ParameterVisualizationViewModel extends AndroidViewModel {

    public ParameterVisualizationViewModel(@NonNull Application application) {
        super(application);
    }


    public List<ParameterVisualizationModel> getVisualizationModels(List<ScalarModel> scalars) {
        List<ParameterVisualizationModel> visualizationModels = new ArrayList<>();

        for (ScalarModel s : scalars) {
            //TODO Add if statement that checks the database for corresponding VisualizationModel for given paramter

            ParameterVisualizationModel p = new ParameterVisualizationModel("Stars", "You've earned this many stars!", "Points", 100, "",s.getValue());

            ParameterVisualizationModel p1 = new ParameterVisualizationModel("Prut", "You've earned this many stars!", "Points", 100, "",s.getValue());


            visualizationModels.add(p);
            visualizationModels.add(p1);
        }

        return visualizationModels;
    }
}
