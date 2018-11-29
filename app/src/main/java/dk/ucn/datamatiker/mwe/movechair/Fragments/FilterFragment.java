package dk.ucn.datamatiker.mwe.movechair.Fragments;

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

import dk.ucn.datamatiker.mwe.movechair.Adapters.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Adapters.FilterAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.FilterModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.R;


public class FilterFragment extends Fragment implements View.OnClickListener {
    List<ActivityModel> activities;
    List<List<FilterModel>> filters = new ArrayList<>();
    private List<FilterModel> equipmentItems;
    private List<FilterModel> muscleGroupItems;
    private List<FilterModel> muscleItems;
    private List<FilterModel> difficultyItems;
    private SharedPreferences sharedPreferences;
    private ActivityAdapter activityAdapter;
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
        activities = (ArrayList<ActivityModel>)getArguments().getSerializable("Activities");
        activityAdapter = (ActivityAdapter) getArguments().getSerializable("activityAdapter");
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

/*    private List<ActivityModel> filterActivities(List<ActivityModel> activities){
        List<ActivityModel> tempList = new ArrayList<>();
        switch(activities.get(0).getClass().getSimpleName()){
            case "Exercise":
                for(int i = 0; i < activities.size(); i++) {
                    ExerciseModel exerciseModel = (ExerciseModel) activities.get(i);
                    boolean passed = true;
                    for (int j = 0; j < exerciseModel.getMuscles().size(); j++) {
                        if (sharedPreferences.getBoolean(exerciseModel.getMuscles().get(j).getName(), false) == false) {
                            String temp = "false";
                            if (sharedPreferences.getBoolean(exerciseModel.getMuscles().get(j).getName(), false)) {
                                temp = "true";
                            }
                            Log.d("musclegroup", "musclegroup" + exerciseModel.getEquipment().get(j).getName() + "" + temp);
                            passed = false;
                        }
                    }
                    for (int j = 0; j < exerciseModel.getEquipment().size(); j++) {
                        if (sharedPreferences.getBoolean(exerciseModel.getEquipment().get(j).getName(), false) == false) {
                            String temp = "false";
                            if (sharedPreferences.getBoolean(exerciseModel.getEquipment().get(j).getName(), false)) {
                                temp = "true";
                            }
                            Log.d("equipment", "equipment" + exerciseModel.getEquipment().get(j).getName() + "" + temp);
                            passed = false;
                        }
                    }
                    if(passed) {
                        tempList.add(activities.get(i));
                    }
                }
                break;

            case "Workout":

                break;

            case "WorkoutPlan":

                    break;
        }
        Log.d("list size", tempList.size() + "");
        return tempList;
    }*/

    private void getFilters(){
        for(int i = 0; i < filters.size(); i++){
            for(int j = 1; j < filters.get(i).size(); j++){
                filters.get(i).get(j).setSelected(sharedPreferences.getBoolean(filters.get(i).get(j).getName(), false));
            }
        }
    }

    private void setFilters(){
        sharedPreferencesEditor = sharedPreferences.edit();
        for(int i = 0; i < filters.size(); i++){
            for(int j = 1; j < filters.get(i).size(); j++){
                sharedPreferencesEditor.putBoolean(filters.get(i).get(j).getName(), filters.get(i).get(j).getSelected());
                sharedPreferencesEditor.commit();
            }
        }
    }

    @Override
    public void onClick(View v) {
        setFilters();
        // TODO this was handled generic
/*        ActivityAdapter activityAdapter = new ActivityAdapter(filterActivities(activities));
        activityAdapter.updateData(filterActivities(activities));*/
        getFragmentManager().popBackStack();
    }


    private List<FilterModel> createFilterItems(List<String> filterValues){
        List<FilterModel> filterModels = new ArrayList<>();
        for(int i = 0; i < filterValues.size(); i++){
            FilterModel filterModel = new FilterModel();
            filterModel.setName(filterValues.get(i));
            filterModel.setSelected(false);
            filterModels.add(filterModel);
        }
        return filterModels;
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
