package wpi.team1021.roompersistencetutorial.step1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import java.util.List;
import java.util.Locale;

import wpi.team1021.R;
import wpi.team1021.roompersistencetutorial.airportDB.AppDatabase;
import wpi.team1021.roompersistencetutorial.airportDB.utils.DatabaseInitializer;
import wpi.team1021.roompersistencetutorial.airportDB.Flight;

public class UsersActivity extends AppCompatActivity {

    private AppDatabase mDb;

    private TextView mFlightsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_activity1);

        mFlightsTextView = findViewById(R.id.flights_tv);

        // Note: Db references should not be in an activity.
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        populateDb();

        fetchData();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
        List<Flight> flights = mDb.flightModel().findAllFlightsByDestination("New York");
        for (Flight flight : flights) {
            sb.append(String.format(Locale.US,
                    "Flight id: %s, origin: %s, destination: %s\n", flight.id, flight.origin, flight.destination));
        }
        mFlightsTextView.setText(sb);
    }
}
