package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;

public class LoginTask extends AsyncTask<String, Integer, UserModel> {
    private final String email;

    public interface AsyncJsonResponse {
        void processFinish(UserModel res);
    }
    public LoginTask(AsyncJsonResponse delegate, String email) {
        this.delegate = delegate;
        this.email = email;
    }
    public AsyncJsonResponse delegate;

    @Override
    protected void onPostExecute(UserModel userModel) {
        delegate.processFinish(userModel);
    }

    @Override
    protected UserModel doInBackground(String... strings) {
        HttpClient client = HttpClients.custom()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .build();
        UserModel result = null;
        HttpUriRequest request = RequestBuilder.get()
                .setUri("http://jvo-web.dk/index.php?controller=login&email="+this.email+"&json")
                .setHeader(HttpHeaders.ACCEPT, "*/*")
                .build();
        //Perform the request and check the status code
        try {
            HttpResponse response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == 200) {

                HttpEntity httpEntity = response.getEntity();
                InputStream content = httpEntity.getContent();
                Reader reader = new InputStreamReader(content);
                Gson gson = new Gson();
                result = gson.fromJson(reader, UserModel.class);
                String string = reader.toString();
                String cont = content.toString();
                content.close();

            } else {
                String debugMsg = response.getStatusLine().getReasonPhrase();
                Log.e("getReasonPhrase:", debugMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
