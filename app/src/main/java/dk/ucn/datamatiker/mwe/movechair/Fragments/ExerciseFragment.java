package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.Helpers.ProgressHelper;
import dk.ucn.datamatiker.mwe.movechair.Helpers.UserHelper;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.ExerciseModel;
import dk.ucn.datamatiker.mwe.movechair.Models.MediaModel;
import dk.ucn.datamatiker.mwe.movechair.Models.SessionLogModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExerciseViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.ExoplayerViewModel;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.SessionLogsViewModel;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ExerciseFragment extends Fragment implements View.OnClickListener {

    private ExerciseViewModel mExerciseViewModel;
    private ExoplayerViewModel mExoplayerViewModel;
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private ExerciseModel mExerciseModel;
    private SessionLogsViewModel mSessionLogViewModel;
    private ProgressHelper progressHelper;
    private CountDownTimer countDownTimer;

    //UI Elements
    private TextView exercise_title;
    private TextView exercise_description;
    private TextView exercise_points;
    private TextView exercise_duration;
    private TextView exercise_category;
    private TextView exercise_equipment;
    private TextView exercise_muscle;
    private View mProgressView;
    private View mExerciseView;

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

        //Instantiate ui elements
        exercise_title = view.findViewById(R.id.exercise_title);
        exercise_description = view.findViewById(R.id.exercise_description);
        exercise_points = view.findViewById(R.id.exercise_points);
        exercise_duration = view.findViewById(R.id.exercise_duration);
        exercise_category = view.findViewById(R.id.exercise_category);
        exercise_equipment = view.findViewById(R.id.exercise_equipment);
        exercise_muscle = view.findViewById(R.id.exercise_muscles);
        playerView = view.findViewById(R.id.player_view);
        mProgressView = view.findViewById(R.id.progress);
        mExerciseView = view.findViewById(R.id.exercise_form);

        //Setup the progress helper
        progressHelper = new ProgressHelper();

        //Get viewModel
        mExerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        mExoplayerViewModel = ViewModelProviders.of(this).get(ExoplayerViewModel.class);
        mSessionLogViewModel = ViewModelProviders.of(this).get(SessionLogsViewModel.class);

        //Get activity object from fragment arguments
        ActivityModel activity = (ActivityModel) getArguments().getSerializable("activity");

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(activity.getName());

        //Pass the id of the activity to the ViewModel which delegates to task
        mExerciseViewModel.getExercise(new AsyncJsonTask.AsyncJsonResponse() {

            @Override
            public void processFinish(Object o) {
                onGetExercise((ExerciseModel) o);
                progressHelper.showProgress(false, mExerciseView, mProgressView, getContext());
            }

        }, ExerciseModel.class, activity.getId());

        //Find button and set onClick
        Button startExerciseButton = (Button) view.findViewById(R.id.start_exercise_button);
        startExerciseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        {
            ArrayList<MediaModel> exerciseMedia = (ArrayList) mExerciseModel.getMedia();
            List<String> exercisePaths = new ArrayList<>();
            for (MediaModel m: exerciseMedia)
            {
                if(m.getMediaType().getName().equals("Video"))
                {
                    exercisePaths.add(m.getPath());
                }
            }
            tempSetupExoplayer(exercisePaths);
        }
    }

    //This method is the callback for our ActivityListTask
    public void onGetExercise(ExerciseModel res) {
        //TODO exercise_video
        if (res != null) {
            exercise_title.setText("Title: " + res.getName());
            exercise_description.setText("Description: " + res.getDescription());
            exercise_points.setText("Points: " + Double.toString(res.getPoints()));
            exercise_duration.setText("Duration: " + Double.toString(res.getDuration()));
            exercise_category.setText("Category: " + res.getCategories());
            exercise_equipment.setText("Equipment: " + res.getEquipment());
            exercise_muscle.setText("Muscle(s): " + res.getMuscles());

            mExerciseModel = res;
        }
    }


    //TODO This method should probably get passed one path and then handle that.
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

        ConcatenatingMediaSource videoSource = new ConcatenatingMediaSource();
        videoSource.addMediaSources(mExoplayerViewModel.mediasourceConversion(s));
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setRepeatMode(Player.REPEAT_MODE_ONE);
        player.setPlayWhenReady(true);
        //Starts the timer
        countDownTimer = new CountDownTimer(Double.valueOf(mExerciseModel.getDuration()).longValue() * 1000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                player.release();
                if(UserHelper.getUser() != null){
                    mSessionLogViewModel.addSessionLog(new AsyncJsonTask.AsyncJsonResponse() {
                        @Override
                        public void processFinish(Object o) {
                            Toast.makeText(getContext(),(String)o, Toast.LENGTH_LONG);
                        }
                    },String.class, mExerciseModel.getId());
                }
            }
        }.start();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(player != null){
            player.release();
            countDownTimer.cancel();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(player != null){

            player.release();
            countDownTimer.cancel();
        }
    }
}
