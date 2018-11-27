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

import dk.ucn.datamatiker.mwe.movechair.Models.DailyLogModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.SessionLogAdapter;

public class SessionLogFragment extends Fragment{

    SessionLogAdapter sessionLogAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_sessiong_log, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get object from fragment arguments
        DailyLogModel dailyLog = (DailyLogModel) getArguments().getSerializable("Daily Log");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Daily Log: " + dailyLog.getSessionLogs().get(0).getDateFormat("dd/MM/yyyy"));

        TextView sessionLogTotal = view.findViewById(R.id.session_log_total);
        TextView sessionLogTotalActivities = view.findViewById(R.id.session_log_total_activities);

        sessionLogTotal.setText(String.valueOf("Total Sessions: " + dailyLog.getSessionLogs().size()));
        sessionLogTotalActivities.setText("Data: ");

        RecyclerView rvSessionLogs = view.findViewById(R.id.rv_session_log_items);
        sessionLogAdapter = new SessionLogAdapter(dailyLog);
        rvSessionLogs.setAdapter(sessionLogAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvSessionLogs.setLayoutManager(linearLayoutManager);

    }
}
