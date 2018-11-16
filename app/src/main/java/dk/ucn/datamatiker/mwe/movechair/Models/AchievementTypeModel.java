package dk.ucn.datamatiker.mwe.movechair.Models;

public class AchievementTypeModel {
    private String name;
    private int iconId;

    public AchievementTypeModel(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    public int getIconId() {

        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
