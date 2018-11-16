package dk.ucn.datamatiker.mwe.movechair.Models;

public class MuscleModel {
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
