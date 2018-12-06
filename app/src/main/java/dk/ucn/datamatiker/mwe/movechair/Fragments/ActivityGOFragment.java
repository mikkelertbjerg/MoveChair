package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
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
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExoplayerViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.SessionLogsViewModel;

public class ActivityGOFragment extends Fragment {

    private ExoplayerViewModel mExoplayerViewModel;
    private WorkoutModel workout;
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private CountDownTimer c;
    private List<String> s;
    private List<Long> durationList;
    static int nextExercise = 0;
    private SessionLogsViewModel mSessionLogViewModel;
    private boolean paused;
    private Long remaining;

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
        mSessionLogViewModel = ViewModelProviders.of(this).get(SessionLogsViewModel.class);

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

        //List used for the timer to work, combination of exercise duration and rest duration.
        durationList = mExoplayerViewModel.getExerciseDuration(workout);

        //Set it to loop
        player.setRepeatMode(Player.REPEAT_MODE_ONE);
        player.setPlayWhenReady(true);

        player.addListener(new Player.DefaultEventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playWhenReady == true && playbackState == Player.STATE_READY) {
                    c.cancel();
                    if (remaining != 0) {
                        Toast.makeText(getContext(), "Video Cancelled", Toast.LENGTH_SHORT).show();
                        paused = false;
                        player.seekTo(player.getCurrentWindowIndex(), remaining);
                        testWorkoutTimerCombo(remaining, 100, nextExercise);
                        c.start();
                    }
                }
                else if (playWhenReady == false && playbackState == Player.STATE_READY) {
                    paused = true;
                    c.cancel();
                }
            }
        });

        testWorkoutTimerCombo(durationList.get(0) * 1000, 100, 0);

        c.start();


    }

    public String testWorkoutTimerCombo(final long duration, long interval, int next)
    {
        remaining = duration;
        c = new CountDownTimer(duration, interval) {


            @Override
            public void onTick(long millisUntilFinished) {
                if(!paused) {
                    remaining = millisUntilFinished;
                    Toast.makeText(getContext(), (millisUntilFinished/1000)+"/"+remaining, Toast.LENGTH_SHORT).show();
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onFinish() {
                if (paused) {
                    //player.seekTo(index, (durationList.get(nextExercise).longValue() - remaining));
                    Toast.makeText(getContext(), "Video Cancelled", Toast.LENGTH_SHORT).show();
                }
                else if (player.getCurrentWindowIndex() == durationList.size()-1) {
                    mSessionLogViewModel.addSessionLog(new AsyncJsonTask.AsyncJsonResponse() {
                        @Override
                        public void processFinish(Object o) {
                            Toast.makeText(getContext(),(String)o, Toast.LENGTH_LONG);
                        }
                    },String.class, workout.getId());

                    player.release();
                    HomeFragment fragment = new HomeFragment();
                    MainActivity mainActivity = (MainActivity) getContext();
                    mainActivity.switchFragment(fragment);


                } else if(player.getCurrentWindowIndex() < durationList.size() - 1) {
                    player.seekTo(player.getCurrentWindowIndex() + 1, C.TIME_UNSET);
                    testWorkoutTimerCombo(durationList.get(++nextExercise) * 1000, 100, nextExercise);
                    c.start();
                }
                /*else if (currentTimeline.getWindow(currentWindowIndex, currentWindow, false).isDynamic) {
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
