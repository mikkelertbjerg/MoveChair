package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;

public class WorkoutPlanModel extends ActivityModel implements Serializable {

    private List<WorkoutModel> workouts;
    private int restDays;
    private int workoutPlanDuration;

    public WorkoutPlanModel(String name, String description, int id, int restDays, int workoutPlanDuration, List<WorkoutModel> workouts) {
        super(name, description, id);
        this.restDays = restDays;
        this.workoutPlanDuration = workoutPlanDuration;
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id, int restDays, int workoutPlanDuration) {
        super(name, description, id);
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

}
