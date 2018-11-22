package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OptionsModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("appearance")
    @Expose
    private AppearanceModel appearance;
    @SerializedName("language")
    @Expose
    private LanguageModel language;
    @SerializedName("unit_type")
    @Expose
    private UnitTypeModel unitType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppearanceModel getAppearance() {
        return appearance;
    }

    public void setAppearance(AppearanceModel appearance) {
        this.appearance = appearance;
    }

    public LanguageModel getLanguage() {
        return language;
    }

    public void setLanguage(LanguageModel language) {
        this.language = language;
    }

    public UnitTypeModel getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeModel unitType) {
        this.unitType = unitType;
    }

}
