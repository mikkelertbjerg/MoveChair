package dk.ucn.datamatiker.mwe.movechair;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class ExerciseViewModel extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_exercise_view, container, false);
    }

    //We need to show chosen exercise based on ID from Bundle

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        ActivityModel activity = (ActivityModel)getArguments().getSerializable("activity");
        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getTitle());

        //TODO Replace this with db call to get specific exercise?
        ExerciseModel exerciseModel = new ExerciseModel(activity.getTitle(), activity.getDescription(),activity.getId());
        exerciseModel.setName("Generic Exercise Name");
        exerciseModel.setDescription("Generic Exercise Description");
        exerciseModel.setPoints(5);

        //VideoView exercise_video = view.findViewById(R.id.exercise_video);
        TextView exercise_title = view.findViewById(R.id.exercise_title);
        TextView exercise_description = view.findViewById(R.id.exercise_description);
        TextView exercise_points = view.findViewById(R.id.exercise_points);

        //TODO exercise_video
        exercise_title.setText(exerciseModel.getName());
        exercise_description.setText(exerciseModel.getDescription());
        exercise_points.setText("Points: " + Double.toString(exerciseModel.getPoints()));
    }
}
