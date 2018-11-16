package dk.ucn.datamatiker.mwe.movechair.Models;

public class FilterModel {

    private String name;
    private boolean selected;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
