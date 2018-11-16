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
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.R;


public class WorkoutPlanViewModel extends AndroidViewModel implements IFViewModel<WorkoutPlanModel> {


    public WorkoutPlanViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public WorkoutPlanModel getItem(int id) {
        //TODO Method that retrieves an WorkoutPlanModel from DB
        return null;
    }

    @Override
    public List<WorkoutPlanModel> getAll() {
        //TODO Method that retrieves all WorkoutPlanModels from the DB
        return null;
    }
}
