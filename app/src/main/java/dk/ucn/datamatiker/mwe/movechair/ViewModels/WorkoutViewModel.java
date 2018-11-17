package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

public class WorkoutViewModel extends AndroidViewModel implements IFViewModel<WorkoutModel> {


    public WorkoutViewModel(@NonNull Application application) {
        super(application);
    }
    //TODO Implement ExerciseViewModel


    @Override
    public WorkoutModel getItem(int id) {
        //TODO Method that retrieves an ExerciseModel from DB
        return (WorkoutModel) new DummyData().createWorkouts(10).get(id);
    }

}


