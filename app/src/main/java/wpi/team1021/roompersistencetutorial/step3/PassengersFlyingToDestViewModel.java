package wpi.team1021.roompersistencetutorial.step3;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import wpi.team1021.roompersistencetutorial.airportDB.Passenger;
import wpi.team1021.roompersistencetutorial.airportDB.AppDatabase;
import wpi.team1021.roompersistencetutorial.airportDB.utils.DatabaseInitializer;

public class PassengersFlyingToDestViewModel extends AndroidViewModel {

    public final LiveData<List<Passenger>> passengers;

    private AppDatabase mDb;

    public PassengersFlyingToDestViewModel(Application application) {
        super(application);
        createDb();

        // TODO: Assign books to the 'findPassengersFlyingToDest' query.
        passengers = null;
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}
