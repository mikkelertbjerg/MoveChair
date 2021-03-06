package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ProgressBar;

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
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
@RequiresApi(api = Build.VERSION_CODES.P)

public class GetNextWorkoutTask extends AsyncJsonTask<WorkoutModel> {
    private int userId;

    public GetNextWorkoutTask(AsyncJsonResponse delegate, Type type, int userId) {
        super(delegate, type);
        this.userId = userId;
        this.controller = type.getTypeName().substring(type.getTypeName().lastIndexOf(".")+1);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected WorkoutModel doInBackground(Object[] objects) {
        HttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").build();

        WorkoutModel result = null;
        String myUrl = "http://jvo-web.dk/index.php?controller=" + this.controller.replace("Model", "") + "s" + "&action=selectnextworkout&user_id=" + userId;
        myUrl = myUrl.replaceAll(" ", "%20");
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
