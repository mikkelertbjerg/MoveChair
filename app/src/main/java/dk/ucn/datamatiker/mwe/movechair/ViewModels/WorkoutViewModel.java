package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;

public class WorkoutViewModel extends AndroidViewModel {

    private AsyncJsonTask.AsyncJsonResponse getWorkoutCallback;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void getWorkout(AsyncJsonTask.AsyncJsonResponse callback, Type type, int workoutId) {
        //Defines callback method for task and starts the task that gets all activities with type.
        this.getWorkoutCallback = callback;
        AsyncJsonTask<ActivityModel> task = new ActivityTask(callback, type, workoutId);
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


    /*
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

    } */


}


