package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;


public class WorkoutModel extends ActivityModel implements Serializable {

    @SerializedName("exercises")
    @Expose
    private List<ExerciseModel> exercises;
    @SerializedName("workoutDuration")
    @Expose
    private double workoutDuration;
    @SerializedName("restDuration")
    @Expose
    private double restDuration;
    @SerializedName("difficulty")
    @Expose
    private DifficultyModel difficulty;
    @SerializedName("activity_type")
    @Expose
    private ActivityTypeModel activityType;

    public WorkoutModel(String name, String description, int id, ActivityTypeModel activityTypeModel, double workoutDuration, double restDuration, DifficultyModel difficulty, List<ExerciseModel> exercises) {
        super(name, description, id, activityTypeModel);
        this.workoutDuration = workoutDuration;
        this.restDuration = restDuration;
        this.difficulty = difficulty;
        this.exercises = exercises;
    }

    public WorkoutModel(String name, String description, int id, ActivityTypeModel activityTypeModel) {
        super(name, description, id, activityTypeModel);
        this.exercises = new ArrayList<ExerciseModel>() {
        };
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

    public DifficultyModel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyModel difficulty) {
        this.difficulty = difficulty;
    }

    public List<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseModel> exercises) {
        this.exercises = exercises;
    }

    public double getPoints(){
        double points = 0;
        for(int i = 0; i < exercises.size(); i++){
            points += exercises.get(i).getPoints() * difficulty.getMultiplier();

        }
        return points;
    }

    @Override
    public ActivityTypeModel getActivityType() {
        return activityType;
    }

    @Override
    public void setActivityType(ActivityTypeModel activityType) {
        this.activityType = activityType;
    }
}
