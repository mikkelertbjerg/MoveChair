package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ActivitiesListFragment extends Fragment implements View.OnClickListener {

    ArrayList<ActivitiesListItems> activityItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_activities_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ActivitiesListFragment");
        RecyclerView rvActivities = (RecyclerView) view.findViewById(R.id.rv_activities);

        activityItems = ActivitiesListItems.createActivityListItems(20);
        // Create adapter passing in the sample user data
        RecyclerViewActivitiesAdapter adapter = new RecyclerViewActivitiesAdapter(activityItems);
        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);
        // Set layout manager to position the items
    }

    @Override
    public void onClick(View v) {

    }
}
