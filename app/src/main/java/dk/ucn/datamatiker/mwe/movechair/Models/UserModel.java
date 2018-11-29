package dk.ucn.datamatiker.mwe.movechair.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("hashed_password")
    @Expose
    private String hashedPassword;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("gender")
    @Expose
    private GenderModel gender;
    @SerializedName("options")
    @Expose
    private OptionsModel options;
    @SerializedName("workoutplans")
    @Expose
    private List<WorkoutPlanModel> workoutPlans;
    @SerializedName("strides")
    @Expose
    private List<StridesModel> strides;
    @SerializedName("session_logs")
    @Expose
    private List<SessionLogModel> sessionLogs;

    public UserModel(String id, String name, String age, String weight, String height, String email, String hashedPassword, String salt, GenderModel gender, OptionsModel options, List<WorkoutPlanModel> workoutPlans, List<StridesModel> strides, List<SessionLogModel> sessionLogs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.gender = gender;
        this.options = options;
        this.workoutPlans = workoutPlans;
        this.strides = strides;
        this.sessionLogs = sessionLogs;
    }

    public UserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public GenderModel getGender() {
        return gender;
    }

    public void setGender(GenderModel gender) {
        this.gender = gender;
    }

    public OptionsModel getOptions() {
        return options;
    }

    public void setOptions(OptionsModel options) {
        this.options = options;
    }

    public List<WorkoutPlanModel> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<WorkoutPlanModel> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public List<StridesModel> getStrides() {
        return strides;
    }

    public void setStrides(List<StridesModel> strides) {
        this.strides = strides;
    }

    public List<SessionLogModel> getSessionLogs() {
        return sessionLogs;
    }

    public void setSessionLogs(List<SessionLogModel> sessionLogs) {
        this.sessionLogs = sessionLogs;
    }
}

