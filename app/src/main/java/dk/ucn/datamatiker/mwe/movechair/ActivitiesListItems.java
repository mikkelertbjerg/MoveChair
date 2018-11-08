package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;

public class ActivitiesListItems {

    private String title;
    private String description;
    private String img;

    public ActivitiesListItems(String title, String description, String img){
        this.title = title;
        this.description = description;
        this.img = img;

    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getImg(){
        return img;
    }

    public static int lastActivtyListTitle = 0;
    public static int lastActivityListDescription = 0;
    public static int lastActivityListImg = 0;

    public static ArrayList<ActivitiesListItems> createActivityListItems(int numActivities){
        ArrayList<ActivitiesListItems> activityListItems = new ArrayList<ActivitiesListItems>();

        for(int i = 0; i <= numActivities; i++){
            activityListItems.add(new ActivitiesListItems("Activity " + ++lastActivtyListTitle, "Description " + ++lastActivityListDescription, "Image" + ++lastActivityListImg));
        }
        return activityListItems;
    }

}
