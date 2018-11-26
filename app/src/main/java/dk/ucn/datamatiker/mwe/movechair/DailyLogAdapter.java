package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Fragments.SessionLogFragment;
import dk.ucn.datamatiker.mwe.movechair.Models.DailyLogModel;

public class DailyLogAdapter extends RecyclerView.Adapter<DailyLogAdapter.ViewHolder> {


    // Store a member variable for the sessionLogs
    private List<DailyLogModel> dailyLogs;
    private DailyLogModel dailyLog;

    // Pass in the sessionLogs array into the constructor
    public DailyLogAdapter(List<DailyLogModel> dailyLogs) {
        this.dailyLogs = dailyLogs;
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

        public ViewHolder(@NonNull View dailyLogListView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(dailyLogListView);

            //activityLogIcon = (ImageView) dailyLogListView.findViewById(R.id.activity_log_icon);
            activityLogField1 = (TextView) dailyLogListView.findViewById(R.id.activity_log_field1);
            activityLogField2 = (TextView) dailyLogListView.findViewById(R.id.activity_log_field2);
            //activityLogField3 = (TextView) dailyLogListView.findViewById(R.id.activity_log_field3);
        }
    }

    // ... view holder defined above..

    @NonNull
    @Override
    public DailyLogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View dailyLogListView = inflater.inflate(R.layout.activity_log_item, viewGroup, false);

        // return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(dailyLogListView);

        //Create onClick
        dailyLogListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;

                //Create bundle with log ID
                final int position = viewHolder.getAdapterPosition();
                Bundle bundle = new Bundle();
                dailyLog = dailyLogs.get(position);
                bundle.putSerializable("Daily Log", dailyLog);
                SessionLogFragment sessionLogFragment = new SessionLogFragment();
                sessionLogFragment.setArguments(bundle);
                mainActivity.switchFragment(sessionLogFragment);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        TextView activityLogField1 = viewHolder.activityLogField1;
        TextView activityLogField2 = viewHolder.activityLogField2;
        //TextView activityLogField3 = viewHolder.activityLogField3;

        // Get the data model based on position
        dailyLog = dailyLogs.get(position);

        // Set item views based on your views and data model
        activityLogField1.setText("Daily Log: " + dailyLog.getSessionLogs().get(0).getDateFormat("dd-mm-yyyy"));
        activityLogField2.setText("Strides: " +String.valueOf(dailyLog.getStrides()));
        //activityLogField3.setText("");
    }
    @Override
    public int getItemCount () {
        return dailyLogs.size();
    }
}
