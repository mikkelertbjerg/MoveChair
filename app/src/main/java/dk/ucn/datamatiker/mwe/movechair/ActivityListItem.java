package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;

public class ActivityListItem {

    private String title;
    private String description;
    //private String img;

    public ActivityListItem(String title, String description){
        this.title = title;
        this.description = description;
        //this.img = img;

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

    public static ArrayList<ActivityListItem> createActivityListItems(int numActivities){
        ArrayList<ActivityListItem> activityListItems = new ArrayList<ActivityListItem>();

        for(int i = 0; i <= numActivities; i++){
            activityListItems.add(new ActivityListItem("Activity " + ++lastActivtyListTitle, "Description " + ++lastActivityListDescription));
        }
        return activityListItems;
    }

}
