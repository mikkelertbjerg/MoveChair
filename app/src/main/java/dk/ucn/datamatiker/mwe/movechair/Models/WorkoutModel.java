package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.ArrayList;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;


public class WorkoutModel extends ActivityModel {

    private ArrayList<ActivityModel> exercises;
    private double workoutDuration;
    private double restDuration;

    public WorkoutModel(String name, String description, int id, double workoutDuration, double restDuration, ArrayList<ActivityModel> exercises) {
        super(name, description, id);
        this.workoutDuration = workoutDuration;
        this.restDuration = restDuration;
        this.exercises = exercises;
    }

    public WorkoutModel(String name, String description, int id) {
        super(name, description, id);
        this.exercises = new ArrayList<ActivityModel>();
    }

    public double getWorkoutDuration() {
        return workoutDuration;
    }

    public double getRestDuration() {
        return restDuration;
    }

    public void setRestDuration(double restDuration) {
        this.restDuration = restDuration;
    }

    public void setWorkoutDuration(double workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public ArrayList<ActivityModel> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ActivityModel> exercises) {
        this.exercises = exercises;
    }
}
