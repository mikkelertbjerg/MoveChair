package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class TravelMapFragment extends Fragment {

    //UI elements
    private TextView unit;
    private TextView value;
    private TextView next_threshold;

    public TravelMapFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_travel_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //UI Elements
        unit = view.findViewById(R.id.map_unit);
        value = view.findViewById(R.id.map_value);
        next_threshold = view.findViewById(R.id.map_next_threshhold);


    }
}
