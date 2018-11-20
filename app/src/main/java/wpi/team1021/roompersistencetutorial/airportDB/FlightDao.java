package wpi.team1021.roompersistencetutorial.airportDB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

import wpi.team1021.roompersistencetutorial.db.Book;

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

/*    @Query("SELECT Flight.* " +
            "FROM Flight " +
            "JOIN Ticket ON Ticket.flight_id = Flight.id " +
            "JOIN Passenger ON Ticket.passenger_id = Passenger.id " +
            "WHERE Passenger.name LIKE :passengerName"
    )
    LiveData<List<Flight>> findFlightsTakenByPassenger(String passengerName);*/

    @Insert(onConflict = IGNORE)
    void insertFlight(Flight Flight);

    @Update(onConflict = REPLACE)
    void updateFlight(Flight Flight);

    @Query("DELETE FROM Flight")
    void deleteAll();
}
