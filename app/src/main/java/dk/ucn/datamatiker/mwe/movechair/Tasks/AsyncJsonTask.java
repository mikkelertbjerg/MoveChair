package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Type;

public abstract class AsyncJsonTask<T> extends AsyncTask {

    public Type type;
    public String controller;
    public AsyncJsonResponse delegate;

    public interface AsyncJsonResponse {
        void processFinish(Object o);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public AsyncJsonTask (AsyncJsonResponse delegate, Type type) {

        this.delegate = delegate;
        this.type = type;
    }

    @Override
    protected void onPostExecute(Object o) {
        delegate.processFinish(o);
    }

    protected abstract T doInBackground(Object[] objects);

}
