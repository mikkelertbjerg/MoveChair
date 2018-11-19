package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;


public class WorkoutModel extends ActivityModel {

    private List<ExerciseModel> exercises;
    private double workoutDuration;
    private double restDuration;

    public WorkoutModel(String name, String description, int id, double workoutDuration, double restDuration, List<ExerciseModel> exercises) {
        super(name, description, id);
        this.workoutDuration = workoutDuration;
        this.restDuration = restDuration;
        this.exercises = exercises;
    }

    public WorkoutModel(String name, String description, int id) {
        super(name, description, id);
        this.exercises = new ArrayList<ExerciseModel>();
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

    public List<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ExerciseModel> exercises) {
        this.exercises = exercises;
    }
}
