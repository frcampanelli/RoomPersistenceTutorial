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
        mFlights = mDb.flightModel().findAllFlightsByLandingTime(tomorrow);
    }
}
