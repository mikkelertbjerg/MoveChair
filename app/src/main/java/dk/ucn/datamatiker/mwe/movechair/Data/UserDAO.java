package dk.ucn.datamatiker.mwe.movechair.Data;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;

public class UserDAO implements DAOIF<UserModel> {

    private DAOIF<UserModel> dao;

    @Override
    public void getConnection(DAOIF<UserModel> db) {
        this.dao = db;
    }

    @Override
    public UserModel getItem(int id) {
        return dao.getItem(id);
    }

    public UserModel login(String email, String password) {
        URL url = null;
        InputStreamReader reader = null;
        try {
            url = new URL("http://mikkelertbjerg.dk/moveChair/index.php?controller=login&json" + "&email=" + email + "&password=" + password);
            Log.d("req url: ", url.toString());
            reader = new InputStreamReader(url.openStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(reader, UserModel.class);
    }

}
