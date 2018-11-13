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


    @Override
    public int getDuration() {
        int temp = 0;
        for (ActivityModel x: this.getExercises()){
            temp += x.getDuration();
        }

        return temp;
    }

    @Override
    public double getPoints() {
        int temp = 0;
        for(ActivityModel x: this.getExercises()){
            temp += x.getPoints();
        }
        return temp;
    }
}
