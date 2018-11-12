package dk.ucn.datamatiker.mwe.movechair;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExerciseModel extends ActivityModel{

    private double points;
    private ArrayList<MediaModel> media;
    private ArrayList<DifficultyModel> difficulties;
    private ArrayList<CategoryModel> categories;
    private ArrayList<MuscleGroupModel> muscleGroups;
    private ArrayList<EquipmentModel> equipment;

    public ExerciseModel(String name, String description, int id, double points,
                         ArrayList<MediaModel> media,
                         ArrayList<DifficultyModel> difficulties,
                         ArrayList<CategoryModel> categories,
                         ArrayList<MuscleGroupModel> muscleGroups,
                         ArrayList<EquipmentModel> equipment) {
        super(name, description, id);
        this.points = points;
        this.media = media;
        this.difficulties = difficulties;
        this.categories = categories;
        this.muscleGroups = muscleGroups;
        this.equipment = equipment;
    }

    public ExerciseModel(String name, String description, int id) {
        super(name, description, id);
        this.categories = new ArrayList<CategoryModel>();
        this.media = new ArrayList<MediaModel>();
        this.difficulties = new ArrayList<DifficultyModel>();
        this.equipment = new ArrayList<EquipmentModel>();
        this.muscleGroups = new ArrayList<MuscleGroupModel>();
    }

    public ArrayList<MediaModel> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<MediaModel> media) {
        this.media = media;
    }

    public ArrayList<DifficultyModel> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(ArrayList<DifficultyModel> difficulties) {
        this.difficulties = difficulties;
    }

    public ArrayList<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<CategoryModel> categories) {
        this.categories = categories;
    }

    public ArrayList<MuscleGroupModel> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(ArrayList<MuscleGroupModel> muscleGroups) {
        this.muscleGroups = muscleGroups;
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
