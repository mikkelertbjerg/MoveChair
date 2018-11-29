package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.DailyLogAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.DailyLogTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.DailyLogViewModel;


public class DailyLogFragment extends Fragment implements DailyLogTask.AsyncJson {

    private UserModel user;
    private DailyLogAdapter dailyLogAdapter;
    private List<DailyLogModel> dailyLogs;
    private DailyLogViewModel mDailyLogViewModel;
    private RecyclerView rvDailyLogs;
    private TextView dailyLogsTotal;
    private TextView dailyLogsTotalStrides;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_daily_log, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Activity Log");

        // set the fragments current user from user helper class
        user = UserHelper.getUser();
        
        // instantiate viewmodel and call its async method for returning daily logs
        mDailyLogViewModel = ViewModelProviders.of(this).get(DailyLogViewModel.class);
        
        // this result will be handled via callbacks to the processFinish method of this class
        mDailyLogViewModel.getDailyLogsByUserId(Integer.valueOf(user.getId()),this);

        dailyLogsTotal = view.findViewById(R.id.daily_log_total);
        dailyLogsTotalStrides = view.findViewById(R.id.daily_log_strides);

        rvDailyLogs = view.findViewById(R.id.rv_daily_log_items);

        dailyLogs = new ArrayList<>();

        dailyLogAdapter = new DailyLogAdapter(dailyLogs);
        rvDailyLogs.setAdapter(dailyLogAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvDailyLogs.setLayoutManager(linearLayoutManager);
    }

    //This method is the callback for our ActivityListTask
    @Override
    public void processFinished(List<DailyLogModel> dailyLogs) {

        dailyLogsTotal.setText("Total logs: " + String.valueOf(dailyLogs.size()));

        int strides = 0;
        for(int i = 0; i < dailyLogs.size(); i++){
            strides += dailyLogs.get(i).getStrides();
        }
        dailyLogsTotalStrides.setText("Total strides: " + String.valueOf(strides));
        dailyLogAdapter = new DailyLogAdapter(dailyLogs);
        rvDailyLogs.setAdapter(dailyLogAdapter);
    }
}
