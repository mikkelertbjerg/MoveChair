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
import java.util.Date;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.client.methods.RequestBuilder;
import cz.msebera.android.httpclient.impl.client.HttpClients;

@RequiresApi(api = Build.VERSION_CODES.P)
public class GetStartedTask extends AsyncJsonTask<Boolean> {
    private String controller = "users";
    private int user_id;
    private int gender_id;
    private String birth_date;
    private double weight;
    private double height;

    public GetStartedTask(AsyncJsonResponse delegate, Type type, int user_id, int gender_id, String birth_date, double weight, double height) {
        super(delegate, type);
        this.user_id = user_id;
        this.gender_id = gender_id;
        this.birth_date = birth_date;
        this.weight = weight;
        this.height = height;
    }

    @Override
    protected Boolean doInBackground(Object[] objects) {

        HttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").build();

        boolean result = false;
        String myUrl = "http://jvo-web.dk/index.php?controller="+ this.controller + "&action=updategetstarted&" +
                "user_id=" + this.user_id +
                "&gender_id=" + this.gender_id +
                "&birth_date=" + this.birth_date +
                "&weight=" + this.weight +
                "&height=" + this.height;
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
                result = gson.fromJson(reader, boolean.class);
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
