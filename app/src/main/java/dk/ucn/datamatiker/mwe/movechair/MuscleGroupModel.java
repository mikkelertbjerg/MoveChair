package dk.ucn.datamatiker.mwe.movechair;

import java.util.ArrayList;

public class MuscleGroupModel {
    private String name;
    private ArrayList<MuscleModel> muscles;

    public MuscleGroupModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MuscleModel> getMuscles() {
        return muscles;
    }

    public void addMuscle(MuscleModel muscleModel) {
        this.muscles.add(muscleModel);
    }
}
