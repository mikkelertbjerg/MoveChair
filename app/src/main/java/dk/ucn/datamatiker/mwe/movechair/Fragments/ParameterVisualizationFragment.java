package dk.ucn.datamatiker.mwe.movechair.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.ParameterVisualizationAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ParameterVisualizationModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ParameterVisualizationViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ParameterVisualizationFragment extends Fragment {

    private RecyclerView rvParameterVisualization;
    private ParameterVisualizationViewModel mParameterVisualizationViewModel;
    private ArrayList<ScalarModel> scalars;
    private Context context;


    public ParameterVisualizationFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        return inflater.inflate(R.layout.fragment_parameter_visualization, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scalars = (ArrayList<ScalarModel>)getArguments().getSerializable("scalars");
        mParameterVisualizationViewModel = ViewModelProviders.of(this).get(ParameterVisualizationViewModel.class);

        //Start AsyncTask to retrieve models
        mParameterVisualizationViewModel.getParameterVisualizationModels(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                onGetParameterVisualizationModels(o);
            }
        },ParameterVisualizationModel.class);

        ArrayList<ParameterVisualizationModel> pvm = new ArrayList<>();

        rvParameterVisualization = view.findViewById(R.id.rv_parameter_visualization);

        context = getActivity();

        ParameterVisualizationAdapter parameterVisualizationAdapter = new ParameterVisualizationAdapter(pvm, context, null);
        rvParameterVisualization.setAdapter(parameterVisualizationAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvParameterVisualization.setLayoutManager(linearLayoutManager);
    }

    public void onGetParameterVisualizationModels(Object o) {

        final ArrayList<ParameterVisualizationModel> pvm = (ArrayList<ParameterVisualizationModel>) mParameterVisualizationViewModel.getVisualizationModels((List<ParameterVisualizationModel>)o, scalars);


        mParameterVisualizationViewModel.getPVMImage(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                ParameterVisualizationAdapter parameterVisualizationAdapter = new ParameterVisualizationAdapter(pvm, context, (Bitmap)o);
                rvParameterVisualization.setAdapter(parameterVisualizationAdapter);
            }
        }, pvm.get(0).getMedia().getPath());


    }

}
