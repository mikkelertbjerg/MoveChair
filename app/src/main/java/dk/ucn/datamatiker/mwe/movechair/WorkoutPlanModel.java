package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;

public class WorkoutPlanModel extends ActivityModel{

    private ArrayList<ActivityModel> workouts;

    public WorkoutPlanModel(String name, String description, int id, ArrayList<ActivityModel> workouts) {
        super(name, description, id);
        this.workouts = workouts;
    }

    public WorkoutPlanModel(String name, String description, int id) {
        super(name, description, id);
        this.workouts = new ArrayList<ActivityModel>();
    }

    public ArrayList<ActivityModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<ActivityModel> workouts) {
        this.workouts = workouts;
    }


    @Override
    public int getDuration() {
        int temp = 0;
        for (ActivityModel x: this.getWorkouts()
                ) {
            temp += x.getDuration();
        }

        return temp;
    }
}
