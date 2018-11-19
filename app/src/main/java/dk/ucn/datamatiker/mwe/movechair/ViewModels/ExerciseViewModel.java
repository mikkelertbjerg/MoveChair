package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

public class ExerciseViewModel extends AndroidViewModel implements IFViewModel<ExerciseModel> {


    public ExerciseViewModel(@NonNull Application application) {
        super(application);
    }
    //TODO Implement ExerciseViewModel




    @Override
    public ExerciseModel getItem(int id) {
        //TODO Method that retrieves an ExerciseModel from DB
        List<ExerciseModel> temp = new DummyData().createExercises(10);
        return temp.get(id);
    }

}
