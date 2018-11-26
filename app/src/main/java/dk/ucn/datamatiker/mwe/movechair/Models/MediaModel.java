package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MediaModel implements Serializable {
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("mediaType")
    @Expose
    private MediaTypeModel mediaType;

    public MediaModel(String path, MediaTypeModel mediaType) {
        this.path = path;
        this.mediaType = mediaType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MediaTypeModel getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaTypeModel mediaType) {
        this.mediaType = mediaType;
    }

}
