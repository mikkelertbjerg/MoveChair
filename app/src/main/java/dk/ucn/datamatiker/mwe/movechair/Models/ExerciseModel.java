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


    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }

    public List<MuscleModel> getMuscles() {
        return muscles;
    }

    public void setMuscles(ArrayList<MuscleModel> muscles) {
        this.muscles = muscles;
    }

    public List<EquipmentModel> getEquipment() {
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
