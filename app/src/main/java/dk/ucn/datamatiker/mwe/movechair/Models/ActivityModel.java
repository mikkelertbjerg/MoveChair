package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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


    public ActivityModel(String name, String description, int id, ActivityTypeModel activityType, double points){
        this.id = id;
        this.name = name;
        this.description = description;
        this.activityType = activityType;
        this.points = points;
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
}
