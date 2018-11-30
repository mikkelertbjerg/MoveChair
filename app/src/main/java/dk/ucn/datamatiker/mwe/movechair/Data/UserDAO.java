package dk.ucn.datamatiker.mwe.movechair.Data;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;

public class UserDAO implements DAOIF<UserModel> {

    private DAOIF<UserModel> dao;
    private UserModel user = null;

    @Override
    public void getConnection(DAOIF<UserModel> db) {
        this.dao = db;
    }

    @Override
    public UserModel getItem(int id) {
        return null;
    }

    public UserModel login(String email, String password) {
        return null;
    }


}
