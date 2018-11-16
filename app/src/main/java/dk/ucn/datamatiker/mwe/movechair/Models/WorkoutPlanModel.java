package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.ArrayList;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;

public class WorkoutPlanModel extends ActivityModel {

    private ArrayList<ActivityModel> workouts;
    private int restDays;
    private int workoutPlanDuration;

    public WorkoutPlanModel(String name, String description, int id, int restDays, int workoutPlanDuration, ArrayList<ActivityModel> workouts) {
        super(name, description, id);
        this.restDays = restDays;
        this.workoutPlanDuration = workoutPlanDuration;
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id, int restDays, int workoutPlanDuration) {
        super(name, description, id);
        this.restDays = restDays;
        this.workoutPlanDuration = workoutPlanDuration;
        this.workouts = new ArrayList<ActivityModel>();
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

    public ArrayList<ActivityModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<ActivityModel> workouts) {
        this.workouts = workouts;
    }

}
