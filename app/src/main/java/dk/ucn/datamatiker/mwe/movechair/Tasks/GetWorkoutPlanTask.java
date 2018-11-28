package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.os.AsyncTask;
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
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;

public class GetWorkoutPlanTask extends AsyncTask<Integer, Integer, WorkoutPlanModel> {
    private final int workoutPlanId;

    public interface AsyncJsonResponse {
        void processFinish(WorkoutPlanModel res);
    }
    public GetWorkoutPlanTask(AsyncJsonResponse delegate, int workoutPlanId) {
        this.delegate = delegate;
        this.workoutPlanId = workoutPlanId;
    }
    public AsyncJsonResponse delegate;

    @Override
    protected void onPostExecute(WorkoutPlanModel workoutPlan) {
        delegate.processFinish(workoutPlan);
    }

    @Override
    protected WorkoutPlanModel doInBackground(Integer... integers) {
        HttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").build();

        WorkoutPlanModel result = null;
        String myUrl = "http://jvo-web.dk/index.php?controller=workout plans&action=select&id=" + workoutPlanId;
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
                Type listType = null;

                result = gson.fromJson(reader, WorkoutPlanModel.class);

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
