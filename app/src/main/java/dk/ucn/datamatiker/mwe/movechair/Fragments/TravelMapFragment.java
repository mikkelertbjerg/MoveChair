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

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ActivityListViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ParameterVisualizationViewModel;

public class TravelMapFragment extends Fragment {

    //UI elements
    private TextView unit;
    private TextView value;
    private TextView next_threshold;
    private ImageView image;

    //Helpers
    private ParameterVisualizationModel uiPVM;
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

        mParameterVisualizationViewmodel.getParameterVisualizationModels(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                updateTravelMap(o);
            }
        },ParameterVisualizationModel.class);

        //UI Elements
        unit = view.findViewById(R.id.map_unit);
        value = view.findViewById(R.id.map_value);
        next_threshold = view.findViewById(R.id.map_next_threshhold);
        image = view.findViewById(R.id.map_image);


    }

    //TODO - Most of the code below this should be moved to a viewModel or Helper class.
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void updateTravelMap(Object o) {
        ArrayList<ParameterVisualizationModel> pvms = (ArrayList<ParameterVisualizationModel>) o;


        //TODO - This is hardcoded for now, change to use DB
        ArrayList<ScalarModel> scalars = new ArrayList<>();
        scalar = new ScalarModel("km", 78);
        scalars.add(scalar);

        ArrayList<ParameterVisualizationModel> uiPVMs = (ArrayList<ParameterVisualizationModel>)mParameterVisualizationViewmodel.getVisualizationModels(pvms,scalars);

        //List of threshold in PVMS
        List<Float> thresholds = new ArrayList<>();

        //Add thresholds to that list
        for(ParameterVisualizationModel p : uiPVMs) {
            thresholds.add(p.getThreshold());
        }

        //Sorting for search
        Collections.sort(thresholds);

        //Find the closestThreshold to the scalar value
        float closestThreshold = closest(scalar.getValue(), thresholds);

        //Choose the corresponding PVM to the closest threshold
        boolean found = false;
        int i = 0;
        while(!found && i < uiPVMs.size()) {
            ParameterVisualizationModel p = uiPVMs.get(i);
            if(p.getThreshold() == closestThreshold) {
                found = true;
                uiPVM = p;
            }
            i++;
        }

        if(uiPVM != null) {
            mParameterVisualizationViewmodel.getPVMImage(o1 -> updateUI(o1), uiPVM.getMedia().getPath());
        }
    }

    public void updateUI(Object o) {
        unit.setText(uiPVM.getUnit());
        value.setText(String.valueOf(scalar.getValue()));
        image.setImageBitmap((Bitmap)o);
    }

    public float closest(float of, List<Float> in) {
        float min = Float.MAX_VALUE;
        float closest = of;

        for (float v : in) {
            final float diff = Math.abs(v - of);

            if (diff < min) {
                min = diff;
                closest = v;
            }
        }

        return closest;
    }
}
