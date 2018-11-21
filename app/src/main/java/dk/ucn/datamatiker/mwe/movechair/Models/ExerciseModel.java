package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.ArrayList;
import java.util.List;

public class ExerciseModel extends ActivityModel {

    private double points;
    private double duration;
    private List<MediaModel> media;
    private List<CategoryModel> categories;
    private List<MuscleModel> muscles;
    private List<EquipmentModel> equipment;

    public ExerciseModel(String name, String description, int id, double points, int duration,
                         List<MediaModel> media,
                         List<CategoryModel> categories,
                         List<MuscleModel> muscles,
                         List<EquipmentModel> equipment) {
        super(name, description, id);
        this.points = points;
        this.duration = duration;
        this.media = media;
        this.categories = categories;
        this.muscles = muscles;
        this.equipment = equipment;
    }

/*    public ExerciseModel(String name, String description, int id, double points, int duration) {
        super(name, description, id);
        this.points = points;
        this.duration = duration;
        this.categories = new ArrayList<CategoryModel>();
        this.media = new ArrayList<MediaModel>();
        this.muscles = new ArrayList<MuscleModel>();
        this.equipment = new ArrayList<EquipmentModel>();
    }*/

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
