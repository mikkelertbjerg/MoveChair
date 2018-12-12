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
    @SerializedName("rest_duration")
    @Expose
    private double restDuration;
    @SerializedName("difficulty")
    @Expose
    private DifficultyModel difficulty;

    public WorkoutModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration, List<MediaModel> media, List<ExerciseModel> exercises, double restDuration, DifficultyModel difficulty) {
        super(name, description, id, activityType, points, duration, media);
        this.exercises = exercises;
        this.restDuration = restDuration;
        this.difficulty = difficulty;
    }

    public WorkoutModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration, List<MediaModel> media) {
        super(name, description, id, activityType, points, duration, media);
    }

    public WorkoutModel() {
    }

    public double getRestDuration() {
        return restDuration;
    }

    public void setRestDuration(double restDuration) {
        this.restDuration = restDuration;
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
}
