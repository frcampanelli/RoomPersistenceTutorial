package wpi.team1021.roompersistencetutorial.step3_solution;

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

        // Passengers is a LiveData object so updates are observed.
        passengers = mDb.passengerModel().findPassengersFlyingToDest("New York");
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}
