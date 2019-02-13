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

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
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
    private ParameterVisualizationModel uiPVM;

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

        mParameterVisualizationViewmodel.getParameterVisualizationModelByThreshold(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                updateTravelMap(o);
            }
        }, ParameterVisualizationModel.class, 60); //HARDCODED VALUE CHANGE LATER YOU MONG

        //UI Elements
        unit = view.findViewById(R.id.map_unit);
        value = view.findViewById(R.id.map_value);
        next_threshold = view.findViewById(R.id.map_next_threshold);
        description = view.findViewById(R.id.map_description);
        image = view.findViewById(R.id.map_image);
    }

    public void updateTravelMap(Object o) {
        uiPVM = (ParameterVisualizationModel)o;
        if(uiPVM != null) {
            mParameterVisualizationViewmodel.getPVMImage(o1 -> updateUI(o1), uiPVM.getMedia().getPath());
        }
    }

    public void updateUI(Object o) {
        unit.setText(uiPVM.getUnit());
        value.setText("123"); //HARDCODED FIX
        next_threshold.setText("missing");
        description.setText(uiPVM.getDescription());
        image.setImageBitmap((Bitmap)o);
    }
}
