package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;

public class WorkoutPlanModel extends ActivityModel implements Serializable {

    @SerializedName("workouts")
    @Expose
    private List<WorkoutModel> workouts;
    @SerializedName("restDays")
    @Expose
    private int restDays;
    @SerializedName("workoutPlanDuration")
    @Expose
    private int workoutPlanDuration;
    @SerializedName("activity_type")
    @Expose
    private ActivityTypeModel activityType;

    public WorkoutPlanModel(String name, String description, int id, ActivityTypeModel activityTypeModel, int restDays, int workoutPlanDuration, List<WorkoutModel> workouts) {
        super(name, description, id, activityTypeModel);
        this.restDays = restDays;
        this.workoutPlanDuration = workoutPlanDuration;
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id, ActivityTypeModel activityTypeModel, int restDays, int workoutPlanDuration) {
        super(name, description, id, activityTypeModel);

        this.restDays = restDays;
        this.workoutPlanDuration = workoutPlanDuration;
        this.workouts = new ArrayList<WorkoutModel>();
    }

    public WorkoutPlanModel() {
    }

    public int getRestDays() {
        return restDays;
    }

    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

    public int getWorkoutPlanDuration() {
        return workoutPlanDuration;
    }

    public void setWorkoutPlanDuration(int workoutPlanDuration) {
        this.workoutPlanDuration = workoutPlanDuration;
    }

    public List<WorkoutModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<WorkoutModel> workouts) {
        this.workouts = workouts;
    }


    @Override
    public ActivityTypeModel getActivityType() {
        return activityType;
    }

    @Override
    public void setActivityType(ActivityTypeModel activityType) {
        this.activityType = activityType;
    }

}
