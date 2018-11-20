package wpi.team1021.roompersistencetutorial.airportDB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface FlightDao {

    @Query("select * from Flight where id = :id")
    Flight loadFlightById(int id);


    @Query("SELECT * FROM Flight")
    LiveData<List<Flight>> findAllFlights();


    @Query("SELECT * FROM Flight")
    List<Flight> findAllFlightsSync();

    @Query("SELECT * FROM Flight WHERE :destination = :destination") //TODO fix this query!
    List<Flight> findAllFlightsByDestination(String destination);

    @Query("SELECT * FROM Flight WHERE destination = :destination")
    List<Flight> findAllFlightsByDestinationSolution(String destination);

    @Query("SELECT * FROM Flight WHERE landingTime > :landingTime")
    LiveData<List<Flight>> findAllFlightsByLandingTime(Date landingTime);

    @Insert(onConflict = IGNORE)
    void insertFlight(Flight Flight);

    @Update(onConflict = REPLACE)
    void updateFlight(Flight Flight);

    @Query("DELETE FROM Flight")
    void deleteAll();
}
