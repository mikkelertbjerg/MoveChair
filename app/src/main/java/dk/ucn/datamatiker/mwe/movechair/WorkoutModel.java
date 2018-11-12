package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;


public class WorkoutModel extends ActivityModel{

    private ArrayList<ActivityModel> exercises;

    public WorkoutModel(String name, String description, int id, ArrayList<ActivityModel> exercises) {
        super(name, description, id);
        this.exercises = exercises;
    }

    public WorkoutModel(String name, String description, int id) {
        super(name, description, id);
        this.exercises = new ArrayList<ActivityModel>();
    }

    public ArrayList<ActivityModel> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ActivityModel> exercises) {
        this.exercises = exercises;
    }
}
