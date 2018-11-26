package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SessionLogModel{
    private ActivityModel activity;
    private Date date;

    public SessionLogModel(ActivityModel activity, Date date) {
        this.activity = activity;
        this.date = date;
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

    public void setDate(Date date) {
        this.date = date;
    }
}
