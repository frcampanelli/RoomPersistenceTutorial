package wpi.team1021.roompersistencetutorial.airportDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters(DateConverter.class)
public interface TicketDao {

    @Query("SELECT * From Ticket")
    LiveData<List<Ticket>> findAllTickets();

    @Query("SELECT * " +
            "From Ticket " +
            "INNER JOIN Flight ON Ticket.flight_id = Flight.id " +
            "INNER JOIN Passenger ON Ticket.passenger_id = Passenger.id ")
    LiveData<List<TicketWithPassengerAndFlight>> findAllWithPassengerAndFlight();

    @Query("SELECT * " +
            "FROM Flight " +
            "INNER JOIN Ticket ON Ticket.flight_id = Flight.id " +
            "INNER JOIN Passenger on Passenger.id = Ticket.passenger_id " +
            "WHERE Passenger.name LIKE :firstName " +
            "AND Flight.landingTime > :landingTime ")
    LiveData<List<TicketWithPassengerAndFlight>> findTicketsByPassengerAndLandingTime(String firstName, Date landingTime);

    @Insert()
    void insertTicket(Ticket ticket);

    @Query("DELETE FROM Ticket")
    void deleteAll();
}
