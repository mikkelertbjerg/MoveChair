package dk.ucn.datamatiker.mwe.movechair;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityModel implements Serializable {

    //TODO Change this/Delete
    private int id;
    private String title;
    private String description;

    public ActivityModel(String title, String description, int id){
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public int getId (){return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    /*
    public String getImg(){

        return img;
    }
    */
    public static int lastActivtyListTitle = 0;
    public static int lastActivityListDescription = 0;
    //public static int lastActivityListImg = 0;

    public static ArrayList<ActivityModel> createActivities(int numActivities){
        ArrayList<ActivityModel> activites = new ArrayList<ActivityModel>();

        for(int i = 0; i <= numActivities; i++){
            activites.add(new ActivityModel("Activity " + ++lastActivtyListTitle, "Description " + ++lastActivityListDescription, i));
        }
        return activites;
    }

}
