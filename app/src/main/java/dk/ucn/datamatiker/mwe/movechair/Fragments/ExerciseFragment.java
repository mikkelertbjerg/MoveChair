package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ActivityListTask;
import dk.ucn.datamatiker.mwe.movechair.Tasks.ExerciseTask;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExoplayerViewModel;

public class ExerciseFragment extends Fragment implements View.OnClickListener, ExerciseTask.AsyncJsonResponse {

    private ExerciseViewModel mExerciseViewModel;
    private ExoplayerViewModel mExoplayerViewModel;
    private UserModel user;
    private PlayerView playerView;
    private SimpleExoPlayer player;

    //UI Elements
    VideoView exercise_video;
    TextView exercise_title;
    TextView exercise_description;
    TextView exercise_points;
    TextView exercise_duration;
    TextView exercise_category;
    TextView exercise_equipment;
    TextView exercise_muscle;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_exercise_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get viewModel
        mExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        mExoplayerViewModel = ViewModelProviders.of(this).get(ExoplayerViewModel.class);

        //Get activity object from fragment arguments
        ExerciseModel activity = (ExerciseModel) getArguments().getSerializable("activity");

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        //Pass the id of the activity to the ViewModel which delegates to task
        mExerciseViewModel.getExercise(this,activity.getId());

        //Find button and set onClick
        Button startExerciseButton = (Button) view.findViewById(R.id.start_exercise_button);
        startExerciseButton.setOnClickListener(this);


        //Instantiate ui elements
/*
        exercise_video = view.findViewById(R.id.exercise_video);
*/
        exercise_title = view.findViewById(R.id.exercise_title);
        exercise_description = view.findViewById(R.id.exercise_description);
        exercise_points = view.findViewById(R.id.exercise_points);
        exercise_duration = view.findViewById(R.id.exercise_duration);
        exercise_category = view.findViewById(R.id.exercise_category);
        exercise_equipment = view.findViewById(R.id.exercise_equipment);
        exercise_muscle = view.findViewById(R.id.exercise_muscles);
        playerView = view.findViewById(R.id.player_view);

    }

    @Override
    public void onClick(View v) {
        {
            tempSetupExoplayer();

        }
    }

    //This method is the callback for our ActivityListTask
    @Override
    public void processFinish(ExerciseModel res) {
        //TODO exercise_video
        exercise_title.setText("Title: " + res.getName());
        exercise_description.setText("Description: " + res.getDescription());
        exercise_points.setText("Points: " + Double.toString(res.getPoints()));
        exercise_duration.setText("Duration: " + Double.toString(res.getDuration()));
        exercise_category.setText("Category: " + res.getCategories());
        exercise_equipment.setText("Equipment: " + res.getEquipment());
        exercise_muscle.setText("Muscle(s): " + res.getMuscles());
    }


    //TODO This method should probably get passed one path and then handle that.
    public void tempSetupExoplayer()
    {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        //initialize the player with default configurations
        player = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector);

        //Assign simpleExoPlayerView
        playerView.setPlayer(player);

        ConcatenatingMediaSource videoSource = new ConcatenatingMediaSource();
        videoSource.addMediaSources(mExoplayerViewModel.setupPlayer());
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
        player.getPlayWhenReady();

    }

}
