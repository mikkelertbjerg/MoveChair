package dk.ucn.datamatiker.mwe.movechair.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.List;

public class UserModel implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birth_date")
    @Expose
    private Date birthDate;
    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("height")
    @Expose
    private double height;
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

    @SerializedName("sessionlogs")
    @Expose
    private List<SessionLogModel> sessionLogs;

    @SerializedName("strides")
    @Expose
    private List<StridesModel> strides;

    public List<WorkoutPlanModel> getWorkoutPlans() {
        return workoutPlans;
    }

    public void setWorkoutPlans(List<WorkoutPlanModel> workoutPlans) {
        this.workoutPlans = workoutPlans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge(){
        Date currentDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int bd = Integer.parseInt(formatter.format(this.birthDate));
        int cd = Integer.parseInt(formatter.format(currentDate));
        int age = (cd - bd) / 10000;
        return age;
    }

    public boolean isUserStarted(){
        return this.height > 0 &&
                this.weight > 0 &&
                this.birthDate.getTime() > 0;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
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

    public List<SessionLogModel> getSessionLogs() {
        return sessionLogs;
    }

    public void setSessionLogs(List<SessionLogModel> sessionLogs) {
        this.sessionLogs = sessionLogs;
    }

    public List<StridesModel> getStrides() {
        return strides;
    }

    public void setStrides(List<StridesModel> strides) {
        this.strides = strides;
    }
}

