package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityModel implements Serializable {

    private int id;
    private String name;
    private String description;
    private  ActivityTypeModel activityType;

    public ActivityModel(String name, String description, int id, ActivityTypeModel activityType){
        this.id = id;
        this.name = name;
        this.description = description;
        this.activityType = activityType;
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
}
