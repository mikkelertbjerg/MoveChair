package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecyclerViewActivitiesAdapter extends RecyclerView.Adapter<RecyclerViewActivitiesAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        //public ImageView activityItemIcon;
        public TextView activityItemTitle;
        public TextView activityItemDescription;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View activityItemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(activityItemView);

            //activityItemIcon = (ImageView) activityItemView.findViewById(R.id.activity_item_icon);
            activityItemTitle = (TextView) activityItemView.findViewById(R.id.activity_item_title);
            activityItemDescription = (TextView) activityItemView.findViewById(R.id.activity_item_description);
        }
    }

        // ... view holder defined above...

        // Store a member variable for the contacts
        private List<ActivitiesListItems> activityItems;

        // Pass in the contact array into the constructor
        public RecyclerViewActivitiesAdapter(List<ActivitiesListItems> activityItems) {
            this.activityItems = activityItems;
        }

    @Override
    public RecyclerViewActivitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View activityListView = inflater.inflate(R.layout.activities_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(activityListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ActivitiesListItems activitiesListItem = activityItems.get(position);

        // Set item views based on your views and data model
        //ImageView activityItemIcon = viewHolder.activityItemIcon;
        TextView activityItemTitle = viewHolder.activityItemTitle;
        TextView activityItemDescription = viewHolder.activityItemDescription;
        //activityItemIcon.setImageIcon(activitiesListItem.getImg());
        activityItemTitle.setText(activitiesListItem.getTitle());
        activityItemDescription.setText(activitiesListItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }
}