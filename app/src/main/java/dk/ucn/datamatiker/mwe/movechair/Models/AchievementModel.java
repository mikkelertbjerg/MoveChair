package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;

public class AchievementModel implements Serializable {
    private String name;
    private int threshold;
    private AchievementTypeModel achievementType;

    public AchievementModel(String name, int threshold, AchievementTypeModel achievementType) {

        this.name = name;
        this.threshold = threshold;
        this.achievementType = achievementType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public AchievementTypeModel getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(AchievementTypeModel achievementType) {
        this.achievementType = achievementType;
    }
}
