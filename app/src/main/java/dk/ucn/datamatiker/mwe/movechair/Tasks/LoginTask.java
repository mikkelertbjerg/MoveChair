package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;

public class LoginTask extends AsyncJsonTask<UserModel> {
    private final String email;
    private final String password;
    public AsyncJsonResponse delegate;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public LoginTask(AsyncJsonTask.AsyncJsonResponse delegate, Type type, String email, String password) {
        super(delegate, type);
        this.email = email;
        this.password = password;
        this.controller = "login";
    }

    @Override
    protected UserModel doInBackground(Object[] objects) {
        HttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").build();
        UserModel result = null;
        HttpUriRequest request = RequestBuilder.get()
                .setUri("http://jvo-web.dk/index.php?controller=" + this.controller +
                        "&email=" + this.email +
                        "&password=" + this.password
                )
                .setHeader(HttpHeaders.ACCEPT, "application/json")
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
                result = gson.fromJson(reader, this.type);
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
