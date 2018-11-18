package wpi.team1021.roompersistencetutorial.airportDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface PassengerDao {
    @Query("select * from passenger")
    List<Passenger> loadAllPassengers();

    @Query("select * from passenger where id = :id")
    Passenger loadPassengerById(int id);

    @Query("select * from passenger where name = :firstName and lastName = :lastName")
    List<Passenger> findPassengerByNameAndLastName(String firstName, String lastName);

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

    @Query("SELECT * FROM Passenger WHERE :age == :age") // TODO: Fix this!
    List<Passenger> findPassengersYoungerThan(int age);

    @Query("SELECT * FROM Passenger WHERE age < :age")
    List<Passenger> findPassengersYoungerThanSolution(int age);

    @Query("DELETE FROM Passenger")
    void deleteAll();
}
