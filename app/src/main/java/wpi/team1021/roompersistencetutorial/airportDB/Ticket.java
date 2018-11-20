package wpi.team1021.roompersistencetutorial.airportDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = {
        @ForeignKey(entity = Flight.class,
                parentColumns = "id",
                childColumns = "flight_id"),

        @ForeignKey(entity = Passenger.class,
                parentColumns = "id",
                childColumns = "passenger_id")})
public class Ticket {
    // Fields can be public or private with getters and setters.
    @PrimaryKey
    @NonNull
    public String id;

    public String seat;

    @ColumnInfo(name="flight_id")
    public String flightId;

    @ColumnInfo(name="passenger_id")
    public String passengerId;

}
