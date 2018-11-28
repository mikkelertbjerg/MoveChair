package dk.ucn.datamatiker.mwe.movechair.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.net.Uri;
import android.support.annotation.NonNull;

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
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.R;

public class ExoplayerViewModel extends AndroidViewModel
{


    private PlayerView playerView;
    private SimpleExoPlayer player;


    public ExoplayerViewModel(@NonNull Application application) {
        super(application);
    }

 /*   public List<MediaSource> setupPlayer(List<String> s)
    {
        String Gif1 = "https://giant.gfycat.com/UnpleasantAdorableBunny.webm";
        String Gif2 = "https://giant.gfycat.com/SnarlingGreedyGrouse.webm";
        String Gif3 = "https://i.gyazo.com/c630088e78b81a57cfcf5cfacee25aaa.mp4";

        List<String> videoPathURLs = new ArrayList<>();
        videoPathURLs.add(Gif1);
        videoPathURLs.add(Gif2);
        videoPathURLs.add(Gif3);

       return mediasourceConversion(videoPathURLs);
    }*/


    //TODO Possibly make a method that only returns one mediasource object. Seems redundant to return a list only containing one object.
    public List<MediaSource> mediasourceConversion(List<String> s)
    {

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplication().getApplicationContext(), Util.getUserAgent(getApplication().getBaseContext(), "MoveChair"));

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
