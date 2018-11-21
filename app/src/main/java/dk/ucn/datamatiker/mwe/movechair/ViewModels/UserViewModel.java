package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;

public class UserViewModel extends AndroidViewModel implements IFViewModel<UserModel>{

    private List<ExerciseModel> exercises;
    private List<WorkoutModel> workouts;
    private List<WorkoutPlanModel> workoutPlans;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    public UserModel getItem(int id) {
        // TODO Retrieve a user from db by id
        return null;
    }

    public UserModel login(String email) {
        UserModel temp = null;

        return temp;
    }
}
