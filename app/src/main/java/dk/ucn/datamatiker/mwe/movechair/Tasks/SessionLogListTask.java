package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;

public class SessionLogListTask extends AsyncJsonTask<List<SessionLogModel>> {

    private AsyncJsonResponse delegate;
    private int user_id;


    @RequiresApi(api = Build.VERSION_CODES.P)
    public SessionLogListTask(AsyncJsonResponse delegate, Type type, int id) {
        super(delegate, type);
        this.user_id = id;
        this.controller = type.getTypeName().substring(type.getTypeName().lastIndexOf(".")+1);
    }

    @Override
    protected List<SessionLogModel> doInBackground(Object[] object) {
        HttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").build();

        List<SessionLogModel> result = null;
        String myUrl = "http://jvo-web.dk/index.php?controller="+ this.controller.replace("Model", "") + "s" +"&action=selectallsessionlogsfromuser&user_id=" + this.user_id;
        HttpUriRequest request = RequestBuilder.get()
                .setUri(myUrl)
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
                Type listType = null;
                //List type
                listType = new TypeToken<List<SessionLogModel>>() {}.getType();
                result = gson.fromJson(reader, listType);
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

