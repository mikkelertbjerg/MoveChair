package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DailyLogModel implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("session_logs")
    @Expose
    private List<SessionLogModel> sessionLogs;
    @SerializedName("strides")
    @Expose
    private int strides;

    public DailyLogModel(List<SessionLogModel> sessionLogs, int strides) {
        this.sessionLogs = sessionLogs;
        this.strides = strides;
    }

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
}
