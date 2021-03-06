package dk.ucn.datamatiker.mwe.movechair.Fragments;


import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.AnalyzeAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ScalarModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.AnalyzeViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class AnalyzeFragment extends Fragment {


    //UI elements
    private Button analyze_button;
    private Button map_button;
    private AnalyzeViewModel mAnalyzeViewModel;
    private RecyclerView rvAnalyze;

    //Global variables
    private ArrayList<ScalarModel> scalars;

    public AnalyzeFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_analyze, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvAnalyze = view.findViewById(R.id.analyze_rv);

        mAnalyzeViewModel = ViewModelProviders.of(this).get(AnalyzeViewModel.class);

        scalars = new ArrayList<>();

        AnalyzeAdapter analyzeAdapter = new AnalyzeAdapter(scalars);
        rvAnalyze.setAdapter(analyzeAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvAnalyze.setLayoutManager(linearLayoutManager);

        //UI elements
        analyze_button = view.findViewById(R.id.analyze_button_convert);
        map_button = view.findViewById(R.id.analyze_button_map);

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelMapFragment travelMapFragment = new TravelMapFragment();
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.switchFragment(travelMapFragment);
            }
        });

        mAnalyzeViewModel.getSessionLogs(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                onGetSessionLogs(o);
            }
        },SessionLogModel.class,UserHelper.getUser().getId());
    }

    //When we get logs we get scalars
    public void onGetSessionLogs(Object o) {
        scalars = (ArrayList<ScalarModel>) mAnalyzeViewModel.getScalars((List<SessionLogModel>)o);
        AnalyzeAdapter analyzeAdapter = new AnalyzeAdapter(scalars);
        rvAnalyze.setAdapter(analyzeAdapter);

        analyze_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParameterVisualizationFragment parameterVisualizationFragment = new ParameterVisualizationFragment();
                Bundle b = new Bundle();
                b.putSerializable("scalars", scalars);
                parameterVisualizationFragment.setArguments(b);
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.switchFragment(parameterVisualizationFragment);
            }
        });
    }
}
