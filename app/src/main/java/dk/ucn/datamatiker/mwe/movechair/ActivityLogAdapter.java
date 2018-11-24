package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.DailyLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;

public class ActivityLogAdapter extends RecyclerView.Adapter<ActivityLogAdapter.ViewHolder> {


    // Store a member variable for the sessionLogs
    private List<DailyLogModel> dailyLogs;

    // Pass in the sessionLogs array into the constructor
    public ActivityLogAdapter(List<DailyLogModel> dailyLogs) {
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

        public ViewHolder(@NonNull View activityLogListView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(activityLogListView);

            //activityLogIcon = (ImageView) activityLogListView.findViewById(R.id.activity_log_icon);
            activityLogField1 = (TextView) activityLogListView.findViewById(R.id.activity_log_field1);
            activityLogField2 = (TextView) activityLogListView.findViewById(R.id.activity_log_field2);
            activityLogField3 = (TextView) activityLogListView.findViewById(R.id.activity_log_field3);
        }
    }

    // ... view holder defined above..

    @NonNull
    @Override
    public ActivityLogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View activityLogListView = inflater.inflate(R.layout.activity_log_item, viewGroup, false);

        // return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(activityLogListView);

        //Create onClick

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        TextView activityLogField1 = viewHolder.activityLogField1;
        TextView activityLogField2 = viewHolder.activityLogField2;
        TextView activityLogField3 = viewHolder.activityLogField3;

        // Get the data model based on position
        DailyLogModel dailyLog = this.dailyLogs.get(position);
        activityLogField1.setText(dailyLog.getSessionLogs().get(0).getDateFormat("dd-mm-yyyy"));
        activityLogField2.setText("Total strides: " +String.valueOf(dailyLog.getStrides()));
    }
    @Override
    public int getItemCount () {
        return dailyLogs.size();
    }
}
