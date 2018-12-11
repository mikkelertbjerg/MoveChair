package dk.ucn.datamatiker.mwe.movechair;

import android.arch.lifecycle.ViewModelProviders;
import org.junit.Test;

import dk.ucn.datamatiker.mwe.movechair.Fragments.WorkoutFragment;
import dk.ucn.datamatiker.mwe.movechair.Models.WorkoutModel;
import dk.ucn.datamatiker.mwe.movechair.Tasks.AsyncJsonTask;
import dk.ucn.datamatiker.mwe.movechair.ViewModels.WorkoutViewModel;

import static org.junit.Assert.assertEquals;

/*
    This test is currently not implemented correctly
    Attempted to follow this guide: https://medium.com/@v.danylo/simple-way-to-test-asynchronous-actions-in-android-service-asynctask-thread-rxjava-etc-d43b0402e005
    But i believe we need to investigate further to ascertain the correct way of implementing Espresso tests on AsyncTasks
 */

public class TestGetWorkout {

    @Test
    public void testGetWorkoutId() {
        WorkoutFragment fragment = new WorkoutFragment();

        WorkoutViewModel vm = ViewModelProviders.of(fragment.getActivity()).get(WorkoutViewModel.class);

        vm.getWorkout(new AsyncJsonTask.AsyncJsonResponse() {

            @Override
            public void processFinish(Object o) {
                assertEquals(1, ((WorkoutModel) o).getId());
            }
        }, WorkoutModel.class, 1);


    }
}
