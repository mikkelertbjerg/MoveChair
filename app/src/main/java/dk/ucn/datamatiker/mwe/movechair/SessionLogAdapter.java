package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import dk.ucn.datamatiker.mwe.movechair.Fragments.ExerciseFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutPlanFragment;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;

public class SessionLogAdapter extends RecyclerView.Adapter<SessionLogAdapter.ViewHolder> {


    // Store a member variable for the sessionLog
    private DailyLogModel dailyLog;
    private SessionLogModel sessionLog;

    // Pass in the sessionLogs array into the constructor
    public SessionLogAdapter(DailyLogModel dailyLog) {
        this.dailyLog = dailyLog;
    }

    //Provide a direct reference to each of the views within a data item
    //Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        //public ImageView activityLogIcon;
        public TextView activityLogField1;
        public TextView activityLogField2;
        public TextView activityLogField3;

        public ViewHolder(@NonNull View sessionLogListView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(sessionLogListView);

            //activityLogIcon = (ImageView) activityLogListView.findViewById(R.id.activity_log_icon);
            activityLogField1 = (TextView) sessionLogListView.findViewById(R.id.activity_log_field1);
            activityLogField2 = (TextView) sessionLogListView.findViewById(R.id.activity_log_field2);
            activityLogField3 = (TextView) sessionLogListView.findViewById(R.id.activity_log_field3);
        }
    }

    // ... view holder defined above..

    @NonNull
    @Override
    public SessionLogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View sessionLogListView = inflater.inflate(R.layout.activity_log_item, viewGroup, false);

        // return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(sessionLogListView);

        //Create onClick
        sessionLogListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                final int position = viewHolder.getAdapterPosition();

                //Create bundle with activity id
                Bundle bundle = new Bundle();
                Fragment fragment = null;

                String type = dailyLog.getSessionLogs().get(position).getActivity().getActivityType().getName();
                switch (type){
                    case "Exercise":
                        fragment = new ExerciseFragment();
                        break;

                    case "Workout":
                        fragment = new WorkoutFragment();
                        break;

                    case "Workout Plan":
                        fragment = new WorkoutPlanFragment();
                        break;
                }

                ActivityModel activity = dailyLog.getSessionLogs().get(position).getActivity();
                bundle.putSerializable("activity", activity);
                fragment.setArguments(bundle);
                mainActivity.switchFragment(fragment);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        TextView activityLogField1 = viewHolder.activityLogField1;
        TextView activityLogField2 = viewHolder.activityLogField2;
        TextView activityLogField3 = viewHolder.activityLogField3;

        // Get the data model based on position
        sessionLog = dailyLog.getSessionLogs().get(position);

        // Set item views based on your views and data model
        activityLogField1.setText("Activity Title: " + sessionLog.getActivity().getName());
        activityLogField2.setText("Activity Type: " + sessionLog.getActivity().getActivityType().getName());
        activityLogField3.setText("Points: " + sessionLog.getActivity().getPoints());

    }
    @Override
    public int getItemCount () {
        return dailyLog.getSessionLogs().size();
    }
}
