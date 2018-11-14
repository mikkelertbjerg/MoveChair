package dk.ucn.datamatiker.mwe.movechair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class FilterFragment extends Fragment {
    private ActivityModel activityModel;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Filters");
        activityModel = (ActivityModel) getArguments().getSerializable("ActivityModel");

        Spinner equipmentSpinner = (Spinner) view.findViewById(R.id.equipment_spinner);
        Spinner muscleGroupsSpinner = (Spinner) view.findViewById(R.id.muscle_groups_spinner);
        Spinner musclesSpinner = (Spinner) view.findViewById(R.id.muscles_spinner);
        Spinner difficultiesSpinner = (Spinner) view.findViewById(R.id.difficulties_spinner);

        List<String> equipmentFilters = createDummyEquipment();
        List<String> muscleGroupFilters = createDummyMuscleGroups();
        List<String> muscleFilters = createDummyMuscles();
        List<String> difficultyFilters = createDummyDifficulties();

        List<StateVO> equipmentVOs = createVOs(equipmentFilters);
        List<StateVO> muscleGroupVOs = createVOs(muscleGroupFilters);
        List<StateVO> muscleVOs = createVOs(muscleFilters);
        List<StateVO> difficultyVOs = createVOs(difficultyFilters);

        FilterAdapter equipmentFilterAdapter = new FilterAdapter(getContext(), 0, equipmentVOs);
        equipmentSpinner.setAdapter(equipmentFilterAdapter);
        FilterAdapter muscleGroupFilterAdapter = new FilterAdapter(getContext(), 0, muscleGroupVOs);
        muscleGroupsSpinner.setAdapter(muscleGroupFilterAdapter);
        FilterAdapter muscleFilterAdapter = new FilterAdapter(getContext(), 0, muscleVOs);
        musclesSpinner.setAdapter(muscleFilterAdapter);
        FilterAdapter difficultyFilterAdapter = new FilterAdapter(getContext(), 0, difficultyVOs);
        difficultiesSpinner.setAdapter(difficultyFilterAdapter);
    }

    private List<StateVO> createVOs(List<String> vos){
        List<StateVO> listVOs = new ArrayList<>();
        for(int i = 0; i < vos.size(); i++){
            StateVO stateVO = new StateVO();
            stateVO.setTitle(vos.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }
        return listVOs;
    }

    private List<String> createDummyEquipment(){
        List<String> filters = new ArrayList<>();
        filters.add("Equipment");
        filters.add("Barbell");
        filters.add("Dumbbell");
        filters.add("Kettlebell");
        return filters;
    }

    private List<String> createDummyMuscleGroups(){
        List<String> filters = new ArrayList<>();
        filters.add("Muscle Groups");
        filters.add("Chest");
        filters.add("Back");
        filters.add("Arms");
        filters.add("Shoulders");
        return filters;
    }

    private List<String> createDummyMuscles(){
        List<String> filters = new ArrayList<>();
        filters.add("Muscles");
        filters.add("Bicep");
        filters.add("Tricep");
        filters.add("Deltoid");
        filters.add("Trapezius");
        return filters;
    }

    private List<String> createDummyDifficulties(){
        List<String> filters = new ArrayList<>();
        filters.add("Difficulties");
        filters.add("Beginner");
        filters.add("Intermediate");
        filters.add("Advanced");
        return filters;
    }
}
