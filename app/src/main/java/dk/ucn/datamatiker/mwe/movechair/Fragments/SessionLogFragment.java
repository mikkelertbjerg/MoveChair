package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dk.ucn.datamatiker.mwe.movechair.R;

public class SessionLogFragment extends Fragment implements View.OnClickListener {


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_sessiong_log, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        //WorkoutModel activity = (WorkoutModel) getArguments().getSerializable("Activity Log");




    }

    @Override
    public void onClick(View v) {

    }
}
