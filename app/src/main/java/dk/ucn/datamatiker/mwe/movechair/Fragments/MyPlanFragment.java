package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.Adapters.MyPlanAdapter;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
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

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Instantiate the views and the view models
        mMyPlanViewModel = ViewModelProviders.of(this).get(MyPlanViewModel.class);
        rvWorkouts = view.findViewById(R.id.my_plan_recyclerview);

        // Null check on the current user
        if (UserHelper.getUser() != null) {
            UserModel user = UserHelper.getUser();

            // Sets the toolbar title if the user exists
            ((AppCompatActivity) getActivity()).getSupportActionBar()
                    .setTitle( "Plan of " + user.getName() );

            // Try running the AsyncTask which fetches the users My Plan
            if (Integer.valueOf(user.getId()) > 0)
            mMyPlanViewModel.getMyPlan(new AsyncJsonTask.AsyncJsonResponse() {

                @Override
                public void processFinish(Object o) {
                    onGetMyPlan((List<WorkoutPlanModel>) o);
                }

            }, WorkoutPlanModel.class, user.getId());
        }

        // Setup the recyclerviews LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWorkouts.setLayoutManager(linearLayoutManager);
    }


    public void onGetMyPlan(List<WorkoutPlanModel> o) {
        List<WorkoutModel> temp = new ArrayList<>();
        for (int i = 0; i < o.size(); i++) {
            temp.addAll(o.get(i).getWorkouts());
        }
        ActivityAdapter mAdapter = new ActivityAdapter((List<ActivityModel>)(List<?>) temp);
        rvWorkouts.setAdapter(mAdapter);
    }
}
