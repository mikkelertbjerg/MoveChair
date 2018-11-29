package dk.ucn.datamatiker.mwe.movechair.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Models.AchievementModel;
import dk.ucn.datamatiker.mwe.movechair.R;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView achievementTitle;
        public TextView achievementThreshold;
        public ImageView achievementIcon;


        public ViewHolder(View achievementItemView) {
            super(achievementItemView);

            achievementTitle = achievementItemView.findViewById(R.id.achievement_title);
            achievementThreshold = achievementItemView.findViewById(R.id.achievement_threshold);
            achievementIcon = achievementItemView.findViewById(R.id.achievement_default);
        }
    }
        private List<AchievementModel> achievements;

        public AchievementAdapter(List<AchievementModel> achievements) {
            this.achievements = achievements;
        }

    @Override
    public AchievementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        final View achievementListView = inflater.inflate(R.layout.achievement_single, parent, false);

        // Return a new holder instance
        final ViewHolder viewHolder = new ViewHolder(achievementListView);

        //Create onClick
        //TODO OnClick goes to detailed view of achievement


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        AchievementModel achievementModel = achievements.get(position);

        // Set item views based on your views and data model
        TextView achievementItemTitle = viewHolder.achievementTitle;
        TextView achievementItemThreshold = viewHolder.achievementThreshold;
        ImageView achievementItemIcon = viewHolder.achievementIcon;

        achievementItemTitle.setText("Title: " + achievementModel.getName());
        //TODO The current value "24" represents the users progress on the achievement
        achievementItemThreshold.setText("24 / " + String.valueOf(achievementModel.getThreshold()));
        achievementItemIcon.setImageResource(achievementModel.getAchievementType().getIconId());
    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }
}