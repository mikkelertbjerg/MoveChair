package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;

public class ExerciseViewModel extends AndroidViewModel implements IFViewModel<ExerciseModel> {


    public ExerciseViewModel(@NonNull Application application) {
        super(application);
    }
    //TODO Implement ExerciseViewModel




    @Override
    public ExerciseModel getItem(int id) {
        //TODO Method that retrieves an ExerciseModel from DB
        return null;
    }

    @Override
    public List<ExerciseModel> getAll() {
        //TODO Method that retrieves all ExerciseModels from the DB

        return null;
    }
}
