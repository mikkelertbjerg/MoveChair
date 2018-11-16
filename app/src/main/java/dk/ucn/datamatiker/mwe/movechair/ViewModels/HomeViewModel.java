package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.arch.lifecycle.ViewModel;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HomeViewModel extends ViewModel {

    public LineGraphSeries<DataPoint> getStrides() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 125),
                new DataPoint(1, 521),
                new DataPoint(2, 353),
                new DataPoint(3, 2156),
                new DataPoint(4, 66),
                new DataPoint(5, 220),
                new DataPoint(6, 23)
        });
        return series;
    }
}
