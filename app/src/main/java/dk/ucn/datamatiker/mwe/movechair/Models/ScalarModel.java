package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;

public class ScalarModel implements Serializable {
    private String unit;
    private float value;

    public ScalarModel(String unit, float value) {
        this.unit = unit;
        this.value = value;
    }

    public ScalarModel() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
