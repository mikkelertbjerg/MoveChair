package dk.ucn.datamatiker.mwe.movechair;

public class ExerciseModel extends ActivityModel{

    private String name;
    private String description;
    private String imgPath;
    private String videoPath;
    private double points;

    public ExerciseModel(String title, String description, int id) {
        super(title, description, id);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getImgPath(){
        return imgPath;
    }

    public void setImgPath(String imgPath){
        this.imgPath = imgPath;
    }

    public String getVideoPath(){
        return videoPath;
    }

    public void setVideoPath(){
        this.videoPath = videoPath;
    }

    public double getPoints(){
        return points;
    }

    public void setPoints(double points){
        this.points = points;
    }
}
