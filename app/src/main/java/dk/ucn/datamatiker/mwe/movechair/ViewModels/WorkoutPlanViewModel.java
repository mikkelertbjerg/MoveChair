package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.arch.lifecycle.ViewModel;
import java.util.List;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;


public class WorkoutPlanViewModel extends ViewModel implements IFViewModel<WorkoutPlanModel> {


/*
    public WorkoutPlanViewModel(@NonNull Application application) {
        super(application);
    }
*/

    public WorkoutPlanViewModel() {
    }

    @Override
    public WorkoutPlanModel getItem(int id) {
        //TODO Method that retrieves an WorkoutPlanModel from DB
        List<WorkoutPlanModel> temp = new DummyData().createWorkoutPlans(10);
        return (WorkoutPlanModel) temp.get(id);
    }

}
