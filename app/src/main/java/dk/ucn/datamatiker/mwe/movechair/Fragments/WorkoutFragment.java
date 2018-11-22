package dk.ucn.datamatiker.mwe.movechair.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import dk.ucn.datamatiker.mwe.movechair.ActivityAdapter;
import dk.ucn.datamatiker.mwe.movechair.Models.ActivityModel;
import dk.ucn.datamatiker.mwe.movechair.Models.UserModel;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.R;
import dk.ucn.datamatiker.mwe.movechair.Test.DummyData;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutViewModel;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WorkoutFragment extends Fragment implements View.OnClickListener {

    //TODO DELETE THEESE/TESTING PURPOSE
    private WorkoutModel workout;
    private UserModel user;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private WorkoutViewModel mWorkoutViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WorkoutFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WorkoutFragment newInstance(int columnCount) {
        WorkoutFragment fragment = new WorkoutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_view, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            // TODO dummy data
            recyclerView.setAdapter(new ActivityAdapter(new DummyData().createWorkouts(5)));
        }
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get activity object from fragment arguments
        WorkoutModel activity = (WorkoutModel) getArguments().getSerializable("activity");

        Button startWorkoutButton = (Button) view.findViewById(R.id.start_workout_button);
        startWorkoutButton.setOnClickListener(this);

        //TODO DELETE THIS DUMMY DATA
        user = new DummyData().createUser();

        mWorkoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);

        workout = (WorkoutModel) mWorkoutViewModel.getItem(activity.getId());

        //This makes you able to change toolbar title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(workout.getName());

        TextView workout_title = view.findViewById(R.id.workout_title);
        TextView workout_duration = view.findViewById(R.id.workout_duration);
        //TODO Do we need theese props? Not according to our domain
        //TextView workout_category = view.findViewById(R.id.workout_category);
        //TextView workout_difficulty = view.findViewById(R.id.workout_difficulty);
        //TextView workout_muscle_group = view.findViewById(R.id.workout_muscle_group);
        //TextView workout_equipment = view.findViewById(R.id.workout_equipment);
        TextView workout_description = view.findViewById(R.id.workout_description);

        workout_title.setText("Title: " + workout.getName());
        workout_duration.setText("Duration: " + workout.getWorkoutDuration());
        workout_description.setText("Description: " + workout.getDescription());

        RecyclerView rvActivities = view.findViewById(R.id.rv_exercises);
        // Create adapter passing in the sample user data
        ActivityAdapter adapter = new ActivityAdapter((List<ActivityModel>)(List<?>) workout.getExercises());
        //ActivityAdapter adapter = new ActivityAdapter(workoutModel.getExercises());

        // Attach the adapter to the recyclerview to populate items
        rvActivities.setAdapter(adapter);
        // Set layout manager to position the items
        rvActivities.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onClick(View v) {
        try {
            mWorkoutViewModel.addActivityToUser(user, workout);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(WorkoutModel item);
    }
}
