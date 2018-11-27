package dk.ucn.datamatiker.mwe.movechair;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import com.google.android.exoplayer2.ExoPlayerFactory;
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
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.upstream.DataSource;

import java.util.ArrayList;
import java.util.List;

public class ActivityGOTestActivity extends Activity {

    TrackSelector trackSelector = new DefaultTrackSelector();

    private PlayerView playerView;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotest);
        setupPlayer();
    }

    public void setupPlayer()
    {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        playerView = findViewById(R.id.player_view);

        //initialize the player with default configurations
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        //Assign simpleExoPlayerView
        playerView.setPlayer(player);

        String Gif1 = "https://giant.gfycat.com/UnpleasantAdorableBunny.webm";
        String Gif2 = "https://giant.gfycat.com/SnarlingGreedyGrouse.webm";
        String Gif3 = "https://i.gyazo.com/c630088e78b81a57cfcf5cfacee25aaa.mp4";

        List<String> asianWomen = new ArrayList<>();
        asianWomen.add(Gif1);
        asianWomen.add(Gif2);
        asianWomen.add(Gif3);

        List<MediaSource> videoList = loopMeDaddy(asianWomen);

        ConcatenatingMediaSource videoSource = new ConcatenatingMediaSource();
        videoSource.addMediaSources(videoList);
        // Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }
    public List<MediaSource> loopMeDaddy(List<String> s)
    {

        DataSource.Factory dataSourceFactory =
        new DefaultDataSourceFactory(this, Util.getUserAgent(this, "MoveChair"));

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();



        List<MediaSource> iteratedList = new ArrayList<>();

        for (String item: s)
        {
            Uri uri = Uri.parse(item);
            MediaSource videoSource = new ExtractorMediaSource(uri,
                    dataSourceFactory, extractorsFactory, null, null);
            iteratedList.add(videoSource);
        }
    return iteratedList;

    }

}
