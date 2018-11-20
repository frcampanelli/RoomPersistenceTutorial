/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wpi.team1021.roompersistencetutorial.step4_solution;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import wpi.team1021.R;
import wpi.team1021.roompersistencetutorial.airportDB.Flight;

import java.util.List;

public class TypeConvertersActivity extends AppCompatActivity {

    private TypeConvertersViewModel mViewModel;

    private TextView mFlightsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_activity2);
        mFlightsTextView = findViewById(R.id.flights_tv);

        // Get a reference to the ViewModel for this screen.
        mViewModel = ViewModelProviders.of(this).get(TypeConvertersViewModel.class);

        // Update the UI whenever there's a change in the ViewModel's data.
        subscribeUiFlights();
    }

    public void onRefreshBtClicked2(View view) {
        mViewModel.createDb();
    }

    private void subscribeUiFlights() {
        mViewModel.getFlights().observe(this, new Observer<List<Flight>>() {
            @Override
            public void onChanged(@NonNull final List<Flight> flights) {
                showFlightsInUi(flights);
            }
        });
    }

    private void showFlightsInUi(final @NonNull List<Flight> flights) {
        StringBuilder sb = new StringBuilder();

        for (Flight flight : flights) {
            sb.append("Flight id: ");
            sb.append(flight.id);
            sb.append(", origin: ");
            sb.append(flight.origin);
            sb.append(", destination: ");
            sb.append(flight.destination);
            sb.append("\n");

        }
        mFlightsTextView.setText(sb.toString());
    }
}
