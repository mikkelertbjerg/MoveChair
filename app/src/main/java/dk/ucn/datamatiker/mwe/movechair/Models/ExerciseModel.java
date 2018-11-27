package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseModel extends ActivityModel implements Serializable {

    @SerializedName("points")
    @Expose
    private double points;
    @SerializedName("duration")
    @Expose
    private double duration;
    @SerializedName("media")
    @Expose
    private List<MediaModel> media;
    @SerializedName("categories")
    @Expose
    private List<CategoryModel> categories;
    @SerializedName("muscles")
    @Expose
    private List<MuscleModel> muscles;
    @SerializedName("equipment")
    @Expose
    private List<EquipmentModel> equipment;
    @SerializedName("activity_type")
    @Expose
    private ActivityTypeModel activityType;


    public ExerciseModel(String name, String description, int id, ActivityTypeModel activityType, double points, int duration,
                         List<MediaModel> media,
                         List<CategoryModel> categories,
                         List<MuscleModel> muscles,
                         List<EquipmentModel> equipment) {
        super(name, description, id, activityType);
        this.points = points;
        this.duration = duration;
        this.media = media;
        this.categories = categories;
        this.muscles = muscles;
        this.equipment = equipment;
    }

    public ExerciseModel() {

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

    public void setMedia(ArrayList<MediaModel> media) {
        this.media = media;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }

    public void setEquipment(ArrayList<EquipmentModel> equipment) {
        this.equipment = equipment;
    }

    public double getPoints(){
        return points;
    }

    public void setPoints(double points){
        this.points = points;
    }

    @Override
    public ActivityTypeModel getActivityType() {
        return super.getActivityType();
    }

    @Override
    public void setActivityType(ActivityTypeModel activityType) {
        this.activityType = activityType;
    }

    public String getMuscles(){
        String muscles = "";
        for (int i = 0; i < this.muscles.size(); i++) {
            String temp;
            temp = this.muscles.get(i).getName();

            if (this.muscles.size() > 1) {
                muscles += temp + ", ";
            } else {
                muscles = temp;
            }
        }
        return muscles;
    }

    public String getEquipment(){
        String equipment = "";
        for (int i = 0; i < this.equipment.size(); i++) {
            String temp;
            temp = this.equipment.get(i).getName();

            if (this.equipment.size() > 1) {
                equipment += temp + ", ";
            } else {
                equipment = temp;
            }
        }
        return equipment;
    }

    public String getCategories(){
        String categories = "";
        for (int i = 0; i < this.categories.size(); i++) {
            String temp;
            temp = this.categories.get(i).getName();

            if (this.categories.size() > 1) {
                categories += temp + ", ";
            } else {
                categories = temp;
            }
        }
        return categories;
    }

}
