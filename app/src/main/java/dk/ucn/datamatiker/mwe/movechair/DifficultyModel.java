package dk.ucn.datamatiker.mwe.movechair;

public class DifficultyModel {
    private String name;
    private double multiplier;

    public DifficultyModel(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }
}
