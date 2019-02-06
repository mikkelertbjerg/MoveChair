package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParameterVisualizationModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("threshold")
    @Expose
    private float threshold;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    private float value;

    public ParameterVisualizationModel(String name, String description, String unit, float threshold, String imagePath, float value) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.threshold = threshold;
        this.imagePath = imagePath;
        this.value = value;
    }

    public ParameterVisualizationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
