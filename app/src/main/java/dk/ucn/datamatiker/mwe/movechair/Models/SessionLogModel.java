package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SessionLogModel implements Serializable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("activity")
    @Expose
    private ActivityModel activity;

    @SerializedName("date")
    @Expose
    private Date date;

    public SessionLogModel(int id, ActivityModel activity, Date date) {
        this.id = id;
        this.activity = activity;
        this.date = date;
    }

    public SessionLogModel(ActivityModel activity) {
        this.activity = activity;
    }

    public SessionLogModel() {
    }

    public ActivityModel getActivity() {
        return activity;
    }

    public void setActivity(ActivityModel activity) {
        this.activity = activity;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat (format);
        return dateFormat.format(this.date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }
}
