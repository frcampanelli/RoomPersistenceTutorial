package com.example.android.persistence.codelab.airportDB;

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

    @Query("SELECT Ticket.id, Flight.flightNumber, Passenger.name, Ticket.seat " +
            "From Ticket " +
            "INNER JOIN Flight ON Ticket.flight_id = Flight.id " +
            "INNER JOIN Passenger ON Ticket.passenger_id = Passenger.id ")
    LiveData<List<TicketWithPassengerAndFlight>> findAllWithPassengerAndFlight();

    @Query("SELECT Ticket.id, Flight.flightNumber as flightNumber, Passenger.name as name, Ticket.seat " +
            "FROM Flight " +
            "INNER JOIN Ticket ON Ticket.flight_id = Flight.id " +
            "INNER JOIN Passenger on Passenger.id = Ticket.passenger_id " +
            "WHERE Passenger.name LIKE :userName " +
            "AND Ticket.endTime > :after ")
    LiveData<List<TicketWithPassengerAndFlight>> findTicketsByNameAfter(String userName, Date after);

    @Insert()
    void insertTicket(Ticket ticket);

    @Query("DELETE FROM Ticket")
    void deleteAll();
}
