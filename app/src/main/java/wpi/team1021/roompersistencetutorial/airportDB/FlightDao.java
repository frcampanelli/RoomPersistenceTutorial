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

    @Query("SELECT * FROM Flight " +
            "INNER JOIN Ticket ON Ticket.Flight_id = Flight.id " +
            "INNER JOIN Passenger on Passenger.id = Ticket.passenger_id " +
            "WHERE Passenger.name LIKE :passengerName"
    )
    LiveData<List<Flight>> findFlightsBorrowedByName(String passengerName);

    @Query("SELECT * FROM Flight " +
            "INNER JOIN Ticket ON Ticket.Flight_id = Flight.id " +
            "INNER JOIN Passenger on Passenger.id = Ticket.passenger_id " +
            "WHERE Passenger.name LIKE :passengerName " +
            "AND Ticket.endTime > :after "
    )
    LiveData<List<Flight>> findFlightsBorrowedByNameAfter(String passengerName, Date after);

    @Query("SELECT * FROM Flight " +
            "INNER JOIN Ticket ON Ticket.Flight_id = Flight.id " +
            "INNER JOIN Passenger on Passenger.id = Ticket.passenger_id " +
            "WHERE Passenger.name LIKE :passengerName"
    )
    List<Flight> findFlightsBorrowedByNameSync(String passengerName);

    @Query("SELECT * FROM Flight " +
            "INNER JOIN Ticket ON Ticket.Flight_id LIKE Flight.id " +
            "WHERE Ticket.passenger_id LIKE :passengerId "
    )
    LiveData<List<Flight>> findFlightsBorrowedByPassenger(String passengerId);

    @Query("SELECT * FROM Flight " +
            "INNER JOIN Ticket ON Ticket.Flight_id LIKE Flight.id " +
            "WHERE Ticket.passenger_id LIKE :passengerId " +
            "AND Ticket.endTime > :after "
    )
    LiveData<List<Flight>> findFlightsBorrowedByPassengerAfter(String passengerId, Date after);

    @Query("SELECT * FROM Flight " +
            "INNER JOIN Ticket ON Ticket.Flight_id LIKE Flight.id " +
            "WHERE Ticket.passenger_id LIKE :passengerId "
    )
    List<Flight> findFlightsBorrowedByPassengerSync(String passengerId);

    @Query("SELECT * FROM Flight")
    LiveData<List<Flight>> findAllFlights();


    @Query("SELECT * FROM Flight")
    List<Flight> findAllFlightsSync();

    @Insert(onConflict = IGNORE)
    void insertFlight(Flight Flight);

    @Update(onConflict = REPLACE)
    void updateFlight(Flight Flight);

    @Query("DELETE FROM Flight")
    void deleteAll();
}
