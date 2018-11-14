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


public class FilterFragment extends Fragment {
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

        String[] filters = createDummyData();
        Spinner activitySpinner = (Spinner) view.findViewById(R.id.filter_spinner);

        ArrayList<StateVO> listVOs = new ArrayList<>();

        for(int i = 0; i < filters.length; i++){
            StateVO stateVO = new StateVO();
            stateVO.setTitle(filters[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        FilterAdapter filterAdapter = new FilterAdapter(getContext(), 0, listVOs);
        activitySpinner.setAdapter(filterAdapter);
    }

    private String[] createDummyData(){
         String[] filters = new String[]{"Filters", "Equipment", "Muscle Groups", "Categories"};
        return filters;
    }
}
