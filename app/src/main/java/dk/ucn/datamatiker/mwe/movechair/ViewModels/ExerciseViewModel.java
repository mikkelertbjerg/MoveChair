package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

public class ExerciseViewModel extends AndroidViewModel implements IFViewModel<ActivityModel> {


    public ExerciseViewModel(@NonNull Application application) {
        super(application);
    }
    //TODO Implement ExerciseViewModel

    @Override
    public ActivityModel getItem(int id) {
        //TODO Method that retrieves an ExerciseModel from DB
        return new DummyData().createExercises(10).get(id);
    }

}
