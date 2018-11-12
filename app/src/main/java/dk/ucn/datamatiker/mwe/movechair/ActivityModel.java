package dk.ucn.datamatiker.mwe.movechair;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityModel implements Serializable {

    //TODO Change this/Delete
    private int id;
    private String name;
    private String description;

    public ActivityModel(String name, String description, int id){
        this.id = id;
        this.name = name;
        this.description = description;

    }

    public int getId (){return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public static int lastActivtyListTitle = 0;
    public static int lastActivityListDescription = 0;
    //public static int lastActivityListImg = 0;

    public static ArrayList<ActivityModel> createExercises(int numActivities){
        ArrayList<ActivityModel> exercises = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            exercises.add(new ActivityModel("Exercise" + ++lastActivtyListTitle, "Description " + ++lastActivityListDescription, i));
        }
        return exercises;
    }
    public static ArrayList<ActivityModel> createWorkouts(int numActivities){
        ArrayList<ActivityModel> workouts = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            workouts.add(new ActivityModel("Workout " + ++lastActivtyListTitle, "Description " + ++lastActivityListDescription, i));
        }
        return workouts;
    }
    public static ArrayList<ActivityModel> createWorkoutPlans(int numActivities){
        ArrayList<ActivityModel> workoutPlans = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            workoutPlans.add(new ActivityModel("Workout Plan " + ++lastActivtyListTitle, "Description " + ++lastActivityListDescription, i));
        }
        return workoutPlans;
    }

}
