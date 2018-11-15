package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;

public class WorkoutPlanModel extends ActivityModel{

    private ArrayList<ActivityModel> workouts;

    public WorkoutPlanModel(String name, String description, int id, int duration, ArrayList<ActivityModel> workouts) {
        super(name, description, id);
        this.setDuration(duration);
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id, int duration) {
        super(name, description, id);
        this.setDuration(duration);
        this.workouts = new ArrayList<ActivityModel>();
    }

    public WorkoutPlanModel() {
    }

    @Override
    public double getPoints(){
        int temp = 0;
        for(ActivityModel x: this.getWorkouts()){
            temp += x.getPoints();
        }
        return temp;
    }

    public ArrayList<ActivityModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<ActivityModel> workouts) {
        this.workouts = workouts;
    }

}
