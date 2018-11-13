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

    public ExerciseModel(String name, String description, int id, double points, int duration,
                         ArrayList<MediaModel> media,
                         ArrayList<DifficultyModel> difficulties,
                         ArrayList<CategoryModel> categories,
                         ArrayList<MuscleGroupModel> muscleGroups,
                         ArrayList<EquipmentModel> equipment) {
        super(name, description, id);
        this.points = points;
        this.setDuration(duration);
        this.media = media;
        this.difficulties = difficulties;
        this.categories = categories;
        this.muscleGroups = muscleGroups;
        this.equipment = equipment;
    }

    public ExerciseModel(String name, String description, int id, double points, int duration) {
        super(name, description, id);
        this.points = points;
        this.setDuration(duration);
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

    public String printDifficulties() {
        String temp = "";
        for(int i = 0; i < this.difficulties.size(); i++){
            temp += this.difficulties.get(i).getName();

            if (i < this.difficulties.size()-1) {
                temp += ", ";
            }
        }

        return temp;
    }

    public ArrayList<CategoryModel> getCategories() {
        return categories;
    }

    public String printCategories(){
        String temp = "";
        for(int i = 0; i < this.categories.size(); i++){
            temp += this.categories.get(i).getName();

            if(i < this.difficulties.size()-1){
                temp += ", ";
            }
        }
        return temp;
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

    public String printMuscleGroups(){
        String temp = "";
        for(int i = 0; i < this.muscleGroups.size(); i++){
            temp += this.muscleGroups.get(i).getName();

            if(i < this.muscleGroups.size()-1){
                temp += ", ";
            }
        }
        return temp;
    }

    public ArrayList<EquipmentModel> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<EquipmentModel> equipment) {
        this.equipment = equipment;
    }

    public String printEquipment(){
        String temp = "";
        for(int i = 0; i < this.equipment.size(); i++){
            temp += this.equipment.get(i).getName();

            if(i < this.equipment.size()-1){
                temp += ", ";
            }
        }
        return temp;
    }

    public double getPoints(){
        return points;
    }

    public void setPoints(double points){
        this.points = points;
    }

}
