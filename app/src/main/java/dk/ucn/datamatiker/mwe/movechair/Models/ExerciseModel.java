package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.ArrayList;

public class ExerciseModel extends ActivityModel {

    private double points;
    private double duration;
    private ArrayList<MediaModel> media;
    private ArrayList<CategoryModel> categories;
    private ArrayList<MuscleModel> muscles;
    private ArrayList<EquipmentModel> equipment;

    public ExerciseModel(String name, String description, int id, double points, int duration,
                         ArrayList<MediaModel> media,
                         ArrayList<CategoryModel> categories,
                         ArrayList<MuscleModel> muscles,
                         ArrayList<EquipmentModel> equipment) {
        super(name, description, id);
        this.points = points;
        this.duration = duration;
        this.media = media;
        this.categories = categories;
        this.muscles = muscles;
        this.equipment = equipment;
    }

    public ExerciseModel(String name, String description, int id, double points, int duration) {
        super(name, description, id);
        this.points = points;
        this.duration = duration;
        this.categories = new ArrayList<CategoryModel>();
        this.media = new ArrayList<MediaModel>();
        this.equipment = new ArrayList<EquipmentModel>();
        this.muscles = new ArrayList<MuscleModel>();
    }

    public ExerciseModel() {

    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public ArrayList<MediaModel> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<MediaModel> media) {
        this.media = media;
    }


    public ArrayList<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }

    public ArrayList<MuscleModel> getMuscles() {
        return muscles;
    }

    public void setMuscles(ArrayList<MuscleModel> muscles) {
        this.muscles = muscles;
    }

    public ArrayList<EquipmentModel> getEquipment() {
        return equipment;
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

}
