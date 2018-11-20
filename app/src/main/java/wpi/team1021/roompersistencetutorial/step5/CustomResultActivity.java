package wpi.team1021.roompersistencetutorial.step5;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import wpi.team1021.R;


public class CustomResultActivity extends AppCompatActivity {

    private CustomResultViewModel mShowUserViewModel;

    private TextView mFlightsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_activity3);
        mFlightsTextView = findViewById(R.id.flights_tv);

        mShowUserViewModel = ViewModelProviders.of(this).get(CustomResultViewModel.class);

        populateDb();

        subscribeUiFlights();
    }

    private void populateDb() {
        mShowUserViewModel.createDb();
    }

    private void subscribeUiFlights() {
        mShowUserViewModel.getTicketsResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String result) {
                mFlightsTextView.setText(result);
            }
        });
    }

    public void onRefreshBtClicked3(View view) {
        populateDb();
        subscribeUiFlights();
    }
}
