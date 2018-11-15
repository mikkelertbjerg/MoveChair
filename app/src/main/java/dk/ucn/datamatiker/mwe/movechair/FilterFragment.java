package dk.ucn.datamatiker.mwe.movechair;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class FilterFragment extends Fragment implements View.OnClickListener {
    List<List<FilterItem>> filters = new ArrayList<>();
    private List<FilterItem> equipmentItems;
    private List<FilterItem> muscleGroupItems;
    private List<FilterItem> muscleItems;
    private List<FilterItem> difficultyItems;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

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

        Button setFiltersButton = (Button) view.findViewById(R.id.button_set_filters);
        setFiltersButton.setOnClickListener(this);

        loadSpinners(view);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("MyFilters", Context.MODE_PRIVATE);
        getFilters();
    }

    private void loadSpinners(View view){
        Spinner equipmentSpinner = (Spinner) view.findViewById(R.id.equipment_spinner);
        Spinner muscleGroupsSpinner = (Spinner) view.findViewById(R.id.muscle_groups_spinner);
        Spinner musclesSpinner = (Spinner) view.findViewById(R.id.muscles_spinner);
        Spinner difficultiesSpinner = (Spinner) view.findViewById(R.id.difficulties_spinner);

        equipmentItems = createFilterItems(createDummyEquipment());
        muscleGroupItems = createFilterItems(createDummyMuscleGroups());
        muscleItems = createFilterItems(createDummyMuscles());
        difficultyItems = createFilterItems(createDummyDifficulties());

        FilterAdapter equipmentFilterAdapter = new FilterAdapter(getContext(), 0, equipmentItems);
        equipmentSpinner.setAdapter(equipmentFilterAdapter);
        FilterAdapter muscleGroupFilterAdapter = new FilterAdapter(getContext(), 0, muscleGroupItems);
        muscleGroupsSpinner.setAdapter(muscleGroupFilterAdapter);
        FilterAdapter muscleFilterAdapter = new FilterAdapter(getContext(), 0, muscleItems);
        musclesSpinner.setAdapter(muscleFilterAdapter);
        FilterAdapter difficultyFilterAdapter = new FilterAdapter(getContext(), 0, difficultyItems);
        difficultiesSpinner.setAdapter(difficultyFilterAdapter);

        filters.add(equipmentItems);
        filters.add(muscleGroupItems);
        filters.add(muscleItems);
        filters.add(difficultyItems);
    }

    private void getFilters(){
        for(int i = 0; i < filters.size(); i++){
            for(int j = 1; j < filters.get(i).size(); j++){
                filters.get(i).get(j).setSelected(sharedPreferences.getBoolean(filters.get(i).get(j).getName(), filters.get(i).get(j).getSelected()));
            }
        }
    }

    @Override
    public void onClick(View v) {
        sharedPreferencesEditor = sharedPreferences.edit();
        for(int i = 0; i < filters.size(); i++){
            for(int j = 1; j < filters.get(i).size(); j++){
                sharedPreferencesEditor.putBoolean(filters.get(i).get(j).getName(), filters.get(i).get(j).getSelected());
                sharedPreferencesEditor.commit();
            }
        }

        getFragmentManager().popBackStack();
    }


    private List<FilterItem> createFilterItems(List<String> filterValues){
        List<FilterItem> filterItems = new ArrayList<>();
        for(int i = 0; i < filterValues.size(); i++){
            FilterItem filterItem = new FilterItem();
            filterItem.setName(filterValues.get(i));
            filterItem.setSelected(false);
            filterItems.add(filterItem);
        }
        return filterItems;
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
