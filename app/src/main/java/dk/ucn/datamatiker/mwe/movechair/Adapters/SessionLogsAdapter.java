package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Fragments.ExerciseFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutPlanFragment;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.LoadActivityIconTask;

@RequiresApi(api = Build.VERSION_CODES.P)
public class SessionLogsAdapter extends RecyclerView.Adapter<SessionLogsAdapter.ViewHolder>  {

    // Store a member variable for the sessionLog
    private List<SessionLogModel> sessionLogs;

    public SessionLogsAdapter(List<SessionLogModel> sessionLogs) {
        this.sessionLogs = sessionLogs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView activityLogIcon;
        public TextView activityLogField1;
        public TextView activityLogField2;
        public TextView activityLogField3;

        public ViewHolder(@NonNull View sessionLogListView) {
            super(sessionLogListView);

            activityLogIcon = sessionLogListView.findViewById(R.id.activity_log_icon);
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


        //Create onClick
        activityListView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                Fragment fragment = null;
                final int position = viewHolder.getAdapterPosition();
                String type = sessionLogs.get(position).getActivity().getActivityType().getName();

                switch (type) {
                    case "Exercise":
                        fragment = new ExerciseFragment();
                        break;
                    case "Workout":
                        fragment = new WorkoutFragment();
                        break;
                    case "Workout Plan":
                        fragment = new WorkoutPlanFragment();
                        break;
                    default:
                        break;
                }


                Bundle bundle = new Bundle();
                ActivityModel activity = sessionLogs.get(position).getActivity();
                bundle.putSerializable("activity", activity);
                fragment.setArguments(bundle);
                mainActivity.switchFragment(fragment);
            }
        });

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull SessionLogsAdapter.ViewHolder viewHolder, int position) {

        ImageView activityLogIcon = viewHolder.activityLogIcon;
        TextView activityLogField1 = viewHolder.activityLogField1;
        TextView activityLogField2 = viewHolder.activityLogField2;
        TextView activityLogField3 = viewHolder.activityLogField3;

        // Get the data model based on position

        // Get the data model based on position
        SessionLogModel sessionLog = sessionLogs.get(position);

        // Set item views based on your views and data model
        if(!sessionLog.getActivity().getMediaByType("img").isEmpty()){
            LoadActivityIconTask task = new LoadActivityIconTask(activityLogIcon);
            task.execute(sessionLog.getActivity().getMediaByType("img").get(0).getPath());
        }
        else{
            activityLogIcon.setImageResource(R.drawable.ic_workout);
        }
        activityLogField1.setText("Activity Title: " + sessionLog.getActivity().getName());
        activityLogField2.setText("Activity Type: " + sessionLog.getActivity().getActivityType().getName());
        activityLogField3.setText("Points: " + sessionLog.getActivity().getPoints());

    }
    @Override
    public int getItemCount () {
        return sessionLogs.size();
    }
}
