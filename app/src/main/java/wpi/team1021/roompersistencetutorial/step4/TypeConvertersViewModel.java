package wpi.team1021.roompersistencetutorial.step4;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wpi.team1021.roompersistencetutorial.airportDB.AppDatabase;
import wpi.team1021.roompersistencetutorial.airportDB.Flight;
import wpi.team1021.roompersistencetutorial.airportDB.utils.DatabaseInitializer;


public class TypeConvertersViewModel extends AndroidViewModel {

    private LiveData<List<Flight>> mFlights;

    private AppDatabase mDb;

    public TypeConvertersViewModel(Application application) {
        super(application);
        createDb();
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);

        // Receive changes
        subscribeToDbChanges();
    }

    public LiveData<List<Flight>> getFlights() {
        return mFlights;
    }

    private void subscribeToDbChanges() {
        // Flights is a LiveData object so updates are observed.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        // TODO: replace this with a query that finds flights that land after tomorrow
        mFlights = mDb.flightModel().findAllFlights();
    }
}
