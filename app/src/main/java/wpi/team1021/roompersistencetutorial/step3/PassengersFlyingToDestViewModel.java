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

        // TODO: Assign books to the 'findBooksBorrowedByName' query.
        passengers = null;
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}
