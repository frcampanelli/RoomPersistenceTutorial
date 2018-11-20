package wpi.team1021.roompersistencetutorial.airportDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import wpi.team1021.roompersistencetutorial.db.Book;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface PassengerDao {
    @Query("select * from passenger")
    List<Passenger> loadAllPassengers();

    @Query("select * from passenger where id = :id")
    Passenger loadPassengerById(int id);

    @Query("select * from passenger where name = :firstName and lastName = :lastName")
    List<Passenger> findPassengerByNameAndLastName(String firstName, String lastName);

    @Query("SELECT Passenger.id, Passenger.name, Passenger.lastName, Passenger.age FROM Passenger " +
            "JOIN Ticket ON Ticket.passenger_id = Passenger.id " +
            "JOIN Flight ON Ticket.flight_id = Flight.id " +
            "WHERE Flight.destination LIKE :destination"
    )
    List<Passenger> findPassengersFlyingToDestSync(String destination);


    @Query("SELECT Passenger.id, Passenger.name, Passenger.lastName, Passenger.age FROM Passenger " +
            "JOIN Ticket ON Ticket.passenger_id = Passenger.id " +
            "JOIN Flight ON Ticket.flight_id = Flight.id " +
            "WHERE Flight.destination LIKE :destination"
    )
    LiveData<List<Passenger>> findPassengersFlyingToDest(String destination);

    @Insert(onConflict = IGNORE)
    void insertPassenger(Passenger passenger);

    @Delete
    void deletePassenger(Passenger passenger);

    @Query("delete from passenger where name like :badName OR lastName like :badName")
    int deletePassengersByName(String badName);

    @Insert(onConflict = IGNORE)
    void insertOrReplacePassengers(Passenger... passengers);

    @Delete
    void deletePassengers(Passenger passenger1, Passenger passenger2);

    @Query("DELETE FROM Passenger")
    void deleteAll();
}
