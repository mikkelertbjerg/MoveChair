package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityModel implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("activity_type")
    @Expose
    private ActivityTypeModel activityType;
    @SerializedName("points")
    @Expose
    private double points;
    @SerializedName("duration")
    @Expose
    private double duration;
    @SerializedName("media")
    @Expose
    private List<MediaModel> media;
    @SerializedName("calories")
    @Expose
    private int calories;


    public ActivityModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration, List<MediaModel> media){
        this.id = id;
        this.name = name;
        this.description = description;
        this.activityType = activityType;
        this.points = points;
        this.duration = duration;
        this.media = media;
    }

    public ActivityModel () {

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

    public void setDescription(String description){
        this.description = description;
    }

    public ActivityTypeModel getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeModel activityType) {
        this.activityType = activityType;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public List<MediaModel> getMedia() {
        return media;
    }

    public void setMedia(List<MediaModel> media) {
        this.media = media;
    }

    public List<MediaModel> getMediaByType(String type){
        List<MediaModel> media = new ArrayList<>();
        for(int i = 0; i < getMedia().size(); i++){
            if(getMedia().get(i).getMediaType().getName().toLowerCase().equals(type.toLowerCase())){
                media.add(getMedia().get(i));
            }
        }
        return media;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
