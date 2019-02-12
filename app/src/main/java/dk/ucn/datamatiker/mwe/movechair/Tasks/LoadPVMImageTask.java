package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.InputStream;
import java.lang.reflect.Type;

public class LoadPVMImageTask extends AsyncTask {

    public AsyncJsonTask.AsyncJsonResponse delegate;
    private String url;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public LoadPVMImageTask(AsyncJsonTask.AsyncJsonResponse delegate, String url) {
        this.url = url;
        this.delegate = delegate;
    }

    @Override
    protected Bitmap doInBackground(Object[] objects) {
        Bitmap img = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return img;
    }

    @Override
    protected void onPostExecute(Object o) {
        delegate.processFinish(o);
    }
}
