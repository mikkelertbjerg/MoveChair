package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseModel extends ActivityModel implements Serializable {

    @SerializedName("categories")
    @Expose
    private List<CategoryModel> categories;
    @SerializedName("muscles")
    @Expose
    private List<MuscleModel> muscles;
    @SerializedName("equipment")
    @Expose
    private List<EquipmentModel> equipment;


    public ExerciseModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration, List<MediaModel> media, List<CategoryModel> categories, List<MuscleModel> muscles, List<EquipmentModel> equipment) {
        super(name, description, id, activityType, points, duration, media);
        this.categories = categories;
        this.muscles = muscles;
        this.equipment = equipment;
    }

    public ExerciseModel(String name, String description, int id, ActivityTypeModel activityType, double points, double duration, List<MediaModel> media) {
        super(name, description, id, activityType, points, duration, media);
    }

    public ExerciseModel() {

    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }

    public void setEquipment(ArrayList<EquipmentModel> equipment) {
        this.equipment = equipment;
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
