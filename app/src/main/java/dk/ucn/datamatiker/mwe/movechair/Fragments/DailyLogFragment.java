package dk.ucn.datamatiker.mwe.movechair.Fragments;

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

import dk.ucn.datamatiker.mwe.movechair.DailyLogAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;


public class DailyLogFragment extends Fragment {

    UserModel user;
    DailyLogAdapter dailyLogAdapter;
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

        user = new DummyData().createUser(20, 5);

        TextView dailyLogsTotal = view.findViewById(R.id.daily_log_total);
        TextView dailyLogsTotalStrides = view.findViewById(R.id.daily_log_strides);

        dailyLogsTotal.setText("Total logs: " + String.valueOf(user.getDailyLogs().size()));

        int strides = 0;
        for(int i = 0; i < user.getDailyLogs().size(); i++){
            strides += user.getDailyLogs().get(i).getStrides();
        }
        dailyLogsTotalStrides.setText("Total strides: " + String.valueOf(strides));

        RecyclerView rvDailyLogs = view.findViewById(R.id.rv_daily_log_items);


        dailyLogAdapter = new DailyLogAdapter(user.getDailyLogs());

        rvDailyLogs.setAdapter(dailyLogAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvDailyLogs.setLayoutManager(linearLayoutManager);

    }
}
