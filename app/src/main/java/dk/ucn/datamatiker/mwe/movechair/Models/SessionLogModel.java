package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.Date;
import java.util.List;

public class SessionLogModel {
    private List<ActivityModel> activities;

    public SessionLogModel(List<ActivityModel> activities) {
        this.activities = activities;
    }

    public SessionLogModel() {
    }

    //Returns a string so we can compare it in the sessionLog Class

    public void setActivities(List<ActivityModel> activities) {
        this.activities = activities;
    }

    public String getActivities(){
        String activities = "";
        for(int i = 0; i < this.activities.size(); i++){
            String temp;
            temp = this.activities.get(i).getName();
            if(this.activities.size() > 1){
                activities += temp + " ,";
            }
            else{
                activities = temp;
            }
        }
        return activities;
    }
}
