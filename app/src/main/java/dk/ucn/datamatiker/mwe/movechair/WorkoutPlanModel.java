package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;

public class WorkoutPlanModel extends ActivityModel{

    private ArrayList<WorkoutModel> workouts;

    public WorkoutPlanModel(String name, String description, int id, ArrayList<WorkoutModel> workouts) {
        super(name, description, id);
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id) {
        super(name, description, id);
        this.workouts = new ArrayList<WorkoutModel>();
    }

    public ArrayList<WorkoutModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<WorkoutModel> workouts) {
        this.workouts = workouts;
    }
}
