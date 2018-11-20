package wpi.team1021.roompersistencetutorial.airportDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

public class TicketWithPassengerAndFlight {
    public String id;

    public String seat;

    @ColumnInfo(name="flight_id")
    public String flightId;

    public String origin;

    public String destination;

    @TypeConverters(DateConverter.class)
    public Date takeoffTime;

    @TypeConverters(DateConverter.class)
    public Date landingTime;

    @ColumnInfo(name="passenger_id")
    public String passengerId;

    @ColumnInfo(name="name")
    public String passengerName;

    @ColumnInfo(name="lastName")
    public String passengerLastName;

    public int age;
}
