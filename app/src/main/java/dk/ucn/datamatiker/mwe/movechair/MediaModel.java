package dk.ucn.datamatiker.mwe.movechair;

public class MediaModel {
    private String path;
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