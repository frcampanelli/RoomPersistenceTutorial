package wpi.team1021.roompersistencetutorial.step2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import wpi.team1021.R;
import wpi.team1021.roompersistencetutorial.airportDB.AppDatabase;
import wpi.team1021.roompersistencetutorial.airportDB.Passenger;
import wpi.team1021.roompersistencetutorial.airportDB.utils.DatabaseInitializer;

public class BadShowUserActivity extends AppCompatActivity {

    private AppDatabase mDb;

    private TextView mPassengersTextView;

    private static void showListInUI(final @NonNull List<Passenger> passengers,
                                     final TextView passengerTextView) {
        StringBuilder sb = new StringBuilder();
        for (Passenger passenger: passengers) {
            sb.append(String.format(Locale.US,
                    "%s %s", passenger.name, passenger.lastName));
            sb.append("\n");
        }
        passengerTextView.setText(sb.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_activity);

        mPassengersTextView = findViewById(R.id.passengers_tv);

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
        // This activity is executing a query on the main thread, making the UI perform badly.
        List<Passenger> passengers = mDb.passengerModel().findPassengersFlyingToDestSync("New York");
        showListInUI(passengers, mPassengersTextView);
    }

    public void onRefreshBtClicked(View view) {
        mPassengersTextView.setText("");
        fetchData();
    }
}
