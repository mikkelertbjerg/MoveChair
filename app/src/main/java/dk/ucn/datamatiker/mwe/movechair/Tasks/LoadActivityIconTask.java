package dk.ucn.datamatiker.mwe.movechair.Tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

@RequiresApi(api = Build.VERSION_CODES.P)
public class LoadActivityIconTask extends AsyncTask<String, Integer, Bitmap> {

    ImageView bitmapImage;

    public LoadActivityIconTask(ImageView bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
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
    protected void onPostExecute(Bitmap img) {
        bitmapImage.setImageBitmap(img);
    }


}
