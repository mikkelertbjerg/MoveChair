package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;

public class ActivityTypeModel implements Serializable {
    private String activityType;

    public ActivityTypeModel(String activityType){
        this.activityType = activityType;
    }

    public ActivityTypeModel(){

    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
