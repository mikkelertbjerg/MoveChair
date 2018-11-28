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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.DailyLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ExerciseTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ExercisesListTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.WorkoutTask;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;

public class WorkoutViewModel extends AndroidViewModel {

    private WorkoutTask.AsyncJsonResponse getWorkoutCallback;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
    }

    public void getWorkout(WorkoutTask.AsyncJsonResponse callback, int workoutId) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.getWorkoutCallback = callback;
        WorkoutTask task = new WorkoutTask(callback, workoutId);
        task.execute();
    }

    private boolean compareDates(Date psDate1, Date psDate2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        String date1 = dateFormat.format(psDate1);
        String date2 = dateFormat.format(psDate2);
        if(date1.equals(date2)) {
            return true;
        } else {
            return false;
        }
    }

    //TODO Change params to recive a user or userid
    public void addActivityToUser(UserModel user, ActivityModel activity) throws ParseException {
        SessionLogModel sessionLog = new SessionLogModel();
        sessionLog.setActivity(activity);
        //Compare the todays date and the users sessionLogs dates, if he doesn't have a session today, a new dailyLog will be added.
        Date today = new Date(); //Today
        if(user.getDailyLogs().isEmpty()){
            //Users created with the constructor always have an empty list
            //Create new session Log list
            List<SessionLogModel> sessionLogs = new ArrayList<SessionLogModel>();
            //Add the sessionLog to the list
            sessionLogs.add(sessionLog);
            //Create new dailyLog
            DailyLogModel dailyLog = new DailyLogModel(sessionLogs);
            //Assign the dailyLog to the user
            user.getDailyLogs().add(dailyLog);
        }
        else if(!compareDates(user.getDailyLogs().get(user.getDailyLogs().size()-1).getSessionLogs().get(0).getDate(), today)){
            //Users created with the constructor always have an empty list
            //Create new session Log list
            List<SessionLogModel> sessionLogs = new ArrayList<SessionLogModel>();
            //Add the sessionLog to the list
            sessionLogs.add(sessionLog);
            //Create new dailyLog
            DailyLogModel dailyLog = new DailyLogModel(sessionLogs);
            //Get the users list of dailyLogs and add the dailyLog
            user.getDailyLogs().add(dailyLog);
        }
        else{
            //Get the users dailyLog (today), and that dailyLogs sessionLogs, and add the current sessionLog.
            user.getDailyLogs().get(user.getDailyLogs().size()-1).getSessionLogs().add(sessionLog);
        }

    }


}


