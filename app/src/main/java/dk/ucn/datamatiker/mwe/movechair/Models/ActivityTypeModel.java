package dk.ucn.datamatiker.mwe.movechair.Models;

public class ActivityTypeModel {
    private int id;
    private String name;

    public ActivityTypeModel(String name, int id){
        this.id = id;
        this.name = name;
    }

    public ActivityTypeModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
