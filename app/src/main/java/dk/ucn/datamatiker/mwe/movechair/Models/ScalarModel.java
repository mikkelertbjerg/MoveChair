package dk.ucn.datamatiker.mwe.movechair.Models;

public class ScalarModel {
    private String unit;
    private String value;

    public ScalarModel(String unit, String value) {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
