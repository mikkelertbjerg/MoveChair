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
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;

public class WorkoutViewModel extends AndroidViewModel implements IFViewModel<WorkoutModel>{


    public WorkoutViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public WorkoutModel getItem(int id) {
        return null;
    }

    @Override
    public List<WorkoutModel> getAll() {
        //TODO Method that retrieves all WorkoutModels from the DB

        return null;
    }
}


