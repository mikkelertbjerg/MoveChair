package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;


public class WorkoutPlanViewModel extends AndroidViewModel implements IFViewModel<ActivityModel> {



    public WorkoutPlanViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    public ActivityModel getItem(int id) {
        //TODO Method that retrieves an WorkoutPlanModel from DB
        return new DummyData().createWorkoutPlans(10).get(id);
    }

}
