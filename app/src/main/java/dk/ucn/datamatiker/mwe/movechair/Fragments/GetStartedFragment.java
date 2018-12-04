package dk.ucn.datamatiker.mwe.movechair.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.ucn.datamatiker.mwe.movechair.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetStartedFragment extends Fragment {


    public GetStartedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_started, container, false);
    }

}
