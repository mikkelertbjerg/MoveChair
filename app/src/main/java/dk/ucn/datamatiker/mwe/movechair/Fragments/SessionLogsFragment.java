package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.se.omapi.Session;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Adapters.SessionLogsAdapter;
import dk.ucn.datamatiker.mwe.movechair.Helpers.ProgressHelper;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.SessionLogListTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ActivityListViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.SessionLogsViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class SessionLogsFragment extends Fragment {

    private SessionLogsViewModel mViewModel;
    private List<SessionLogModel> sessionLogs;
    private RecyclerView rvSessionLogs;
    private UserModel user = UserHelper.getUser();
    private ProgressHelper progressHelper;

    //UI elements
    private TextView sessionLogTotal;
    private View mSessionLogView;
    private View mProgressView;

    public static SessionLogsFragment newInstance() {
        return new SessionLogsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session_logs, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressHelper = new ProgressHelper();
        mSessionLogView = view.findViewById(R.id.sessionlog_view);
        mProgressView = view.findViewById(R.id.progress);

        mViewModel = ViewModelProviders.of(this).get(SessionLogsViewModel.class);

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(user.getName() + ": Sessions");

        rvSessionLogs = view.findViewById(R.id.rv_session_log_items);

        sessionLogTotal = view.findViewById(R.id.session_log_total);
        TextView sessionLogTotalActivities = view.findViewById(R.id.session_log_total_activities);

        sessionLogTotalActivities.setText("Data: ");

        List<SessionLogModel> sessionlogs = new ArrayList<>();

        SessionLogsAdapter sessionLogsAdapter = new SessionLogsAdapter(sessionlogs);
        rvSessionLogs.setAdapter(sessionLogsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        rvSessionLogs.setLayoutManager(linearLayoutManager);

        //Call getWorkoutmethod on ViewModel that starts the async task which retrives data from DB
        mViewModel.getSessionLogs(new AsyncJsonTask.AsyncJsonResponse() {
            @Override
            public void processFinish(Object o) {
                onGetSessionLogs((List<SessionLogModel>) o);
                progressHelper.showProgress(false, mSessionLogView, mProgressView, getContext());
            }
        }, SessionLogModel.class, user.getId());

    }

    public void onGetSessionLogs(List<SessionLogModel> sessionLogs) {
        if(sessionLogs != null) {
            this.sessionLogs = sessionLogs;

            sessionLogTotal.setText(String.valueOf("Total Sessions: " + sessionLogs.size()));
            SessionLogsAdapter sessionLogsAdapter = new SessionLogsAdapter(sessionLogs);
            rvSessionLogs.setAdapter(sessionLogsAdapter);
        } else {
            Toast.makeText(getContext(), "SessionLogs er tom", Toast.LENGTH_LONG);
        }
    }
}
