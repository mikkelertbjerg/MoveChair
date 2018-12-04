package dk.ucn.datamatiker.mwe.movechair.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StridesModel implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("date")
    @Expose
    private Date date;

    public StridesModel(int id, int amount, Date date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public StridesModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yy").parse(date);
    }

    public String getDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat (format);
        return dateFormat.format(this.date);
    }
}
