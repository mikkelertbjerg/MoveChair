package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutFragment;
import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class MyPlanAdapter extends RecyclerView.Adapter<MyPlanAdapter.ViewHolder> {
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView workoutTitle;
        public TextView workoutPoints;
        public TextView workoutDuration;


        public ViewHolder(View myPlanItemView) {
            super(myPlanItemView);

            workoutTitle = myPlanItemView.findViewById(R.id.activity_title);
            workoutPoints = myPlanItemView.findViewById(R.id.activity_field_one);
            workoutDuration = myPlanItemView.findViewById(R.id.activity_field_two);

        }
    }
        private List<WorkoutModel> workouts;

        public MyPlanAdapter(List<WorkoutModel> workouts) {
            this.workouts = workouts;
        }

    @Override
    public MyPlanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View workoutListView = inflater.inflate(R.layout.activity, parent, false);

        // Return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(workoutListView);

        //Create onClick
        //TODO OnClick goes to workout view
        workoutListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                WorkoutFragment fragment = new WorkoutFragment();
                //Create bundle with Exercise ID
                final int position = viewHolder.getAdapterPosition();
                Bundle bundle = new Bundle();
                ActivityModel activity = workouts.get(position);
                bundle.putSerializable("activity", activity);
                fragment.setArguments(bundle);
                mainActivity.switchFragment(fragment);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        WorkoutModel workoutModel = (WorkoutModel) workouts.get(position);

        // Set item views based on your views and data model
        TextView workoutItemTitle = viewHolder.workoutTitle;
        TextView workoutItemPoints = viewHolder.workoutPoints;
        TextView workoutItemDuration = viewHolder.workoutDuration;

        workoutItemTitle.setText("Title: " + workoutModel.getName());
        //TODO The current value "24" represents the users progress on the achievement
        double totalPoints = 0;
        for(int i = 0;i < workoutModel.getExercises().size();i++) {
            totalPoints += ((ExerciseModel) workoutModel.getExercises().get(i)).getPoints();
        }
        workoutItemPoints.setText("Points: " + Double.toString(totalPoints));

        workoutItemDuration.setText("Duration:" + ((WorkoutModel) workoutModel).getDuration());
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
}