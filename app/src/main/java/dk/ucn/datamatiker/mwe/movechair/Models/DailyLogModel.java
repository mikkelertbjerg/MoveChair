package dk.ucn.datamatiker.mwe.movechair.Models;

import java.util.Date;
import java.util.List;

public class DailyLogModel {
    private List<SessionLogModel> sessionLogs;
    private int strides;
    private Date date = new Date();

    public DailyLogModel(List<SessionLogModel> sessionLogs) {
        this.sessionLogs = sessionLogs;
    }

    public DailyLogModel(int strides) {
        this.strides = strides;
    }

    public DailyLogModel() {
    }

    public List<SessionLogModel> getSessionLogs() {
        return sessionLogs;
    }

    public void setSessionLogs(List<SessionLogModel> sessionLogs) {
        this.sessionLogs = sessionLogs;
    }

    public int getStrides() {
        return strides;
    }

    public void setStrides(int strides) {
        this.strides = strides;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
