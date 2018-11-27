package dk.ucn.datamatiker.mwe.movechair.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DailyLogModel implements Serializable {
    private List<SessionLogModel> sessionLogs;
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
