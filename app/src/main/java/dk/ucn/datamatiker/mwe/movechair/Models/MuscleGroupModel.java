package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MuscleGroupModel implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("muscles")
    @Expose
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
