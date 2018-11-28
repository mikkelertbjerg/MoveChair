package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.MainActivity;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExoplayerViewModel;

public class ActivityGOFragment extends Fragment {

    private ExoplayerViewModel mExoplayerViewModel;
    private WorkoutModel workout;
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private CountDownTimer c;
    private List<String> s;
    static int nextExercise = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_go, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get viewModel
        mExoplayerViewModel = ViewModelProviders.of(this).get(ExoplayerViewModel.class);

        //Get views
        playerView = view.findViewById(R.id.player_view);

        //Get activity object from fragment arguments
        workout = (WorkoutModel) getArguments().getSerializable("workout");

        s = mExoplayerViewModel.getExercisePaths(workout);


        tempSetupExoplayer(s);

    }

    public void tempSetupExoplayer(List<String> s)
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

        //Testing

        ConcatenatingMediaSource videoSource = new ConcatenatingMediaSource();
        videoSource.addMediaSources(mExoplayerViewModel.mediasourceConversion(s));
        // Prepare the player with the source.
        player.prepare(videoSource);


        //Set it to loop
        player.setRepeatMode(Player.REPEAT_MODE_ONE);
        player.setPlayWhenReady(true);

        //TODO FOR TESTING PLZ DELETE LATER
        workout.getExercises().get(0).setDuration(10);
        workout.getExercises().get(1).setDuration(10);
        testWorkoutTimerCombo(Double.valueOf(workout.getExercises().get(0).getDuration()).longValue(), 5000, 0);

        c.start();


    }

    public String testWorkoutTimerCombo(long duration, long interval, int next)
    {

        c = new CountDownTimer(duration * 1000, interval) {


            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (player.getCurrentWindowIndex() == workout.getExercises().size()-1) {

                  /*  Toast toast = Toast.makeText(getContext(), "Din workout er færdig!", Toast.LENGTH_LONG);
                    toast.show();*/

                    HomeFragment fragment = new HomeFragment();
                    MainActivity mainActivity = (MainActivity) getContext();
                    mainActivity.switchFragment(fragment);

                } else {
                    player.seekTo(player.getCurrentWindowIndex() + 1, C.TIME_UNSET);
                    testWorkoutTimerCombo(Double.valueOf(workout.getExercises().get(nextExercise).getDuration()).longValue(), 500, nextExercise++);
                    c.start();


                } /*else if (currentTimeline.getWindow(currentWindowIndex, currentWindow, false).isDynamic) {
               player.seekTo(currentWindowIndex, C.TIME_UNSET);
           }*/

            }
        };

/*
        KeyEvent.KEYCODE_MEDIA_NEXT
*/
     return "pindis";
    }

}
