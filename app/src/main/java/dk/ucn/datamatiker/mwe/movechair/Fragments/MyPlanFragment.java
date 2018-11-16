package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.MyPlanAdapter;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.MyPlanViewModel;

public class MyPlanFragment extends Fragment {

    private MyPlanViewModel mMyPlanViewModel;
    private RecyclerView rvWorkouts;

/*    public static MyPlanFragment newInstance() {
        return new MyPlanFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_plan_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMyPlanViewModel = ViewModelProviders.of(this).get(MyPlanViewModel.class);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Plan");
        // TODO: Use the ViewModel
        WorkoutPlanModel wp = (WorkoutPlanModel)mMyPlanViewModel.getMyPlan(1);

        //Fill out dummy data
        // Create adapter passing in the sample user data
        MyPlanAdapter adapter = new MyPlanAdapter(wp.getWorkouts());
        // Attach the adapter to the recyclerview to populate items
        rvWorkouts = getActivity().findViewById(R.id.my_plan_recyclerview);
        rvWorkouts.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWorkouts.setLayoutManager(linearLayoutManager);
    }

}
