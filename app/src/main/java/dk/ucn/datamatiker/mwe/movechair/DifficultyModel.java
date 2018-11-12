package dk.ucn.datamatiker.mwe.movechair;

public class DifficultyModel {
    private String name;
    private double multiplier;

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public DifficultyModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
