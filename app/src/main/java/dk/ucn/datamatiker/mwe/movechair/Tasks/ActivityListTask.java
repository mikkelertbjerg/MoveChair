package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.os.AsyncTask;
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
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;

public class ActivityListTask extends AsyncTask<String, Integer, List<ActivityModel>> {
    private final String activityType;

    public interface AsyncJsonResponse {
        void processFinish(List<ActivityModel> res);
    }
    public ActivityListTask(dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask.AsyncJsonResponse delegate, String activityType) {
        this.delegate = delegate;
        this.activityType = activityType;
    }
    public dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask.AsyncJsonResponse delegate;

    @Override
    protected void onPostExecute(List<ActivityModel> activities) {
        delegate.processFinish(activities);
    }

    @Override
    protected List<ActivityModel> doInBackground(String... strings) {
        HttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36").build();

        List<ActivityModel> result = null;
        Log.d("activityType: ", activityType);
        String myUrl = "http://jvo-web.dk/index.php?controller=" + activityType + "&json";
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
                switch (activityType.toLowerCase()) {
                    case "exercises":
                        listType = new TypeToken<List<ExerciseModel>>() {
                        }.getType();
                        break;

                    case "workouts":
                        listType = new TypeToken<List<WorkoutModel>>() {
                        }.getType();
                        break;

                    case "workout plans":
                        listType = new TypeToken<List<WorkoutPlanModel>>() {
                        }.getType();
                        break;
                }
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