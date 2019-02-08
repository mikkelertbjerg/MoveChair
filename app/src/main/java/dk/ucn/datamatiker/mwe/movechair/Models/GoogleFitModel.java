package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.Date;
import java.util.List;

public class GoogleFitModel extends ActivityModel {

    private Date starttime;
    private Date endtime;
    private int strides;
    private int distance;
    private int calories;

    public GoogleFitModel() {
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public int getStrides() {
        return strides;
    }

    public void setStrides(int strides) {
        this.strides = strides;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
}
