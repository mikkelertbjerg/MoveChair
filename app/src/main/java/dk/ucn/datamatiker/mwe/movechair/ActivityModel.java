package dk.ucn.datamatiker.mwe.movechair;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityModel implements Serializable {

    private int id;
    private String name;
    private String description;
    private int duration;
    private double points;

    public ActivityModel(String name, String description, int id){
        this.id = id;
        this.name = name;
        this.description = description;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
