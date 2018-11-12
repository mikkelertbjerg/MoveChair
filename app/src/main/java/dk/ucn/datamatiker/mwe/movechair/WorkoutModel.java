package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;


public class WorkoutModel extends ActivityModel{

    private ArrayList<ExerciseModel> exercises;

    public WorkoutModel(String name, String description, int id, ArrayList<ExerciseModel> exercises) {
        super(name, description, id);
        this.exercises = exercises;
    }

    public WorkoutModel(String name, String description, int id) {
        super(name, description, id);
        this.exercises = new ArrayList<ExerciseModel>();
    }

    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ExerciseModel> exercises) {
        this.exercises = exercises;
    }
}
