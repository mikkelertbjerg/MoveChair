package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ParameterVisualizationViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class TravelMapFragment extends Fragment {

    //UI elements
    private TextView unit;
    private TextView value;
    private TextView next_threshold;
    private TextView description;
    private ImageView image;

    //Helpers
    private List<ParameterVisualizationModel> uiPVMs;
    private ScalarModel scalar;

    //VM
    private ParameterVisualizationViewModel mParameterVisualizationViewmodel;

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

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mParameterVisualizationViewmodel = ViewModelProviders.of(this).get(ParameterVisualizationViewModel.class);

        scalar = new ScalarModel("km",20); //HARDCODED FIX!!!

        mParameterVisualizationViewmodel.getParameterVisualizationModelByThreshold(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                updateTravelMap(o);
            }
        }, ParameterVisualizationModel.class, scalar.getValue(), scalar.getUnit()); //HARDCODED VALUE CHANGE LATER YOU MONG

        //UI Elements
        unit = view.findViewById(R.id.map_unit);
        value = view.findViewById(R.id.map_value);
        next_threshold = view.findViewById(R.id.map_next_threshold);
        description = view.findViewById(R.id.map_description);
        image = view.findViewById(R.id.map_image);
    }

    public void updateTravelMap(Object o) {
        uiPVMs = (ArrayList<ParameterVisualizationModel>)o;


        if(uiPVMs != null) {
            for(ParameterVisualizationModel pvm : uiPVMs){
                if(pvm.getThreshold() <= scalar.getValue()){
                    unit.setText(pvm.getUnit());
                    value.setText(String.valueOf(scalar.getValue())); //HARDCODED FIX

                    description.setText(pvm.getDescription());
                    mParameterVisualizationViewmodel.getPVMImage(bitmap -> updateTravelMapImage(bitmap), pvm.getMedia().getPath());
                } else if(pvm.getThreshold() > scalar.getValue()){
                    next_threshold.setText(String.valueOf(pvm.getThreshold()));
                }
            }
        }

    }

    public void updateTravelMapImage(Object o) {
        image.setImageBitmap((Bitmap)o);
    }
}
