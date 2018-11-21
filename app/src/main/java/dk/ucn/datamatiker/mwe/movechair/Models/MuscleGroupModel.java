package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.ArrayList;
import java.util.List;

public class MuscleGroupModel {
    private String name;
    private List<MuscleModel> muscles;

    public MuscleGroupModel(String name){
        this.name = name;
        this.muscles = new ArrayList<MuscleModel>();
    }

    public MuscleGroupModel(String name, List<MuscleModel> muscles) {
        this.name = name;
        this.muscles = muscles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MuscleModel> getMuscles() {
        return muscles;
    }

}
