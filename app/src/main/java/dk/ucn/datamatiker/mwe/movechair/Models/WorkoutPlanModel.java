package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;

public class WorkoutPlanModel extends ActivityModel implements Serializable {


    @SerializedName("rest_days")
    @Expose
    private int restDays;
    @SerializedName("workouts")
    @Expose
    private List<WorkoutModel> workouts;

    public WorkoutPlanModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration, int restDays, List<WorkoutModel> workouts) {
        super(name, description, id, activityType, points, duration);
        this.restDays = restDays;
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration) {
        super(name, description, id, activityType, points, duration);
    }

    public WorkoutPlanModel() {
    }

    public int getRestDays() {
        return restDays;
    }

    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

    public List<WorkoutModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    public void setWorkouts(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }
}
