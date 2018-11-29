package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class SessionLogsAdapter extends RecyclerView.Adapter<SessionLogsAdapter.ViewHolder>  {

    // Store a member variable for the sessionLog
    private List<SessionLogModel> sessionLogs;

    public SessionLogsAdapter(List<SessionLogModel> sessionLogs) {
        this.sessionLogs = sessionLogs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView activityLogField1;
        public TextView activityLogField2;
        public TextView activityLogField3;

        public ViewHolder(@NonNull View sessionLogListView) {
            super(sessionLogListView);

            activityLogField1 = sessionLogListView.findViewById(R.id.activity_log_field1);
            activityLogField2 = sessionLogListView.findViewById(R.id.activity_log_field2);
            activityLogField3 = sessionLogListView.findViewById(R.id.activity_log_field3);
        }
    }

    @NonNull
    @Override
    public SessionLogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View activityListView = inflater.inflate(R.layout.activity_log_item, viewGroup, false);

        // Return a new holder instance
        final SessionLogsAdapter.ViewHolder viewHolder = new SessionLogsAdapter.ViewHolder(activityListView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionLogsAdapter.ViewHolder viewHolder, int position) {

        TextView activityLogField1 = viewHolder.activityLogField1;
        TextView activityLogField2 = viewHolder.activityLogField2;
        TextView activityLogField3 = viewHolder.activityLogField3;

        // Get the data model based on position

        // Get the data model based on position
        SessionLogModel sessionLog = sessionLogs.get(position);

        // Set item views based on your views and data model
        activityLogField1.setText("Activity Title: " + sessionLog.getActivity().getName());
        activityLogField2.setText("Activity Type: " + sessionLog.getActivity().getActivityType().getName());
        activityLogField3.setText("Points: " + sessionLog.getActivity().getPoints());

    }
    @Override
    public int getItemCount () {
        return sessionLogs.size();
    }
}
