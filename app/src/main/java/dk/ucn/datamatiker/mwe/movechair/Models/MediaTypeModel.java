package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;

public class MediaTypeModel implements Serializable {
    private String name;

    public MediaTypeModel(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
