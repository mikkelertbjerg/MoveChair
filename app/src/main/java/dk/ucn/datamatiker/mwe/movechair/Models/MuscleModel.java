package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;

public class MuscleModel implements Serializable {
    private String name;

    public MuscleModel(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

}
