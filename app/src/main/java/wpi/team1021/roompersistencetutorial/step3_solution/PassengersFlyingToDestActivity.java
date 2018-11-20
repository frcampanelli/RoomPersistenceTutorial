package wpi.team1021.roompersistencetutorial.step3_solution;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import wpi.team1021.R;
import wpi.team1021.roompersistencetutorial.airportDB.Passenger;

public class PassengersFlyingToDestActivity extends AppCompatActivity {

    private PassengersFlyingToDestViewModel mViewModel;

    private TextView mPassengersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_activity);
        mPassengersTextView = findViewById(R.id.passengers_tv);

        // Get a reference to the ViewModel for this screen.
        mViewModel = ViewModelProviders.of(this).get(PassengersFlyingToDestViewModel.class);

        // Update the UI whenever there's a change in the ViewModel's data.
        subscribeUiPassengers();
    }

    public void onRefreshBtClicked(View view) {
        mViewModel.createDb();
    }

    private void subscribeUiPassengers() {
        mViewModel.passengers.observe(this, new Observer<List<Passenger>>() {
            @Override
            public void onChanged(@NonNull final List<Passenger> passengers) {
                showPassengersInUi(passengers);
            }
        });
    }

    private void showPassengersInUi(final @NonNull List<Passenger> passengers) {
        StringBuilder sb = new StringBuilder();

        for (Passenger passenger: passengers) {
            sb.append(String.format(Locale.US,
                    "%s %s", passenger.name, passenger.lastName));
            sb.append("\n");
        }
        mPassengersTextView.setText(sb.toString());
    }
}
