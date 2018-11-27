package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Fragments.ExerciseFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutFragment;
import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutPlanFragment;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutPlanModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutPlanViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutViewModel;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> implements Serializable {
    Context context;

    // Store a member variable for the activities
    private List<ActivityModel> activities;

    // Pass in the activities array into the constructor
    public ActivityAdapter (List<ActivityModel> activities){
        this.activities = activities;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        //public ImageView activityItemIcon;
        public TextView activityItemTitle;
        public TextView activityFieldOne;
        public TextView activityFieldTwo;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View activityItemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(activityItemView);

            //activityItemIcon = (ImageView) activityItemView.findViewById(R.id.activity_item_icon);
            activityItemTitle = (TextView) activityItemView.findViewById(R.id.activity_title);
            activityFieldOne = (TextView) activityItemView.findViewById(R.id.activity_field_one);
            activityFieldTwo = (TextView) activityItemView.findViewById(R.id.activity_field_two);
        }
    }

        // ... view holder defined above...

    @Override
    public ActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View activityListView = inflater.inflate(R.layout.activity, parent, false);

        // Return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(activityListView);

        //Create onClick
        activityListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                Fragment fragment = null;
                String type = activities.get(0).getActivityType().getName();

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
                //Create bundle with activity ID
                final int position = viewHolder.getAdapterPosition();
                Bundle bundle = new Bundle();
                ActivityModel activity = activities.get(position);
                bundle.putSerializable("activity", activity);
                fragment.setArguments(bundle);
                mainActivity.switchFragment(fragment);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        //ImageView activityItemIcon = viewHolder.activityItemIcon;
        TextView activityItemTitle = viewHolder.activityItemTitle;
        TextView activityFieldOne = viewHolder.activityFieldOne;
        TextView activityFieldTwo = viewHolder.activityFieldTwo;

        // Get the data model based on position
        ActivityModel activity = activities.get(position);
        String type = activities.get(0).getActivityType().getName();

        // Set item views based on your views and data model
        switch(type){
            case "Exercise":
                ExerciseModel exercise = (ExerciseModel) activity;
                activityItemTitle.setText("Title: " + exercise.getName());
                activityFieldOne.setText("Muscle(s): " + exercise.getMuscles());
                activityFieldTwo.setText("Points: " + String.valueOf(exercise.getPoints()));
                break;

            case "Workout":
                WorkoutModel workout = (WorkoutModel) activity;
                activityItemTitle.setText("Title: " + workout.getName());
                activityFieldOne.setText("Difficulty: " + workout.getDifficulty().getName());
                activityFieldTwo.setText("Points: " + String.valueOf(workout.getPoints()));
                break;

                case "Workout Plan":
                    WorkoutPlanModel workoutPlan = (WorkoutPlanModel) activity;
                    activityItemTitle.setText("Title: " + workoutPlan.getName());
                    activityFieldOne.setText("Weeks: " + String.valueOf(workoutPlan.getWorkoutPlanDuration()));
                    activityFieldTwo.setText("Rest days: " + String.valueOf(workoutPlan.getRestDays()));
                    break;

        }
        //activityItemIcon.setImageIcon(activitiesListItem.getImg());
    }

    //Unused method for filtering
    public void updateData(List<ExerciseModel> activities){
            this.activities.clear();
            this.activities.addAll(activities);
            notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }
}