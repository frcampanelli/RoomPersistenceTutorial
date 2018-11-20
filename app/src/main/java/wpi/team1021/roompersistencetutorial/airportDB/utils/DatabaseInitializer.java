package wpi.team1021.roompersistencetutorial.airportDB.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import wpi.team1021.roompersistencetutorial.airportDB.AppDatabase;
import wpi.team1021.roompersistencetutorial.airportDB.Flight;
import wpi.team1021.roompersistencetutorial.airportDB.Passenger;
import wpi.team1021.roompersistencetutorial.airportDB.Ticket;

import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

    // Simulate a blocking operation delaying each Ticket insertion with a delay:
    private static final int DELAY_MILLIS = 500;

    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static void addTicket(final AppDatabase db, final String id,
                                  final Passenger passenger, final Flight flight, final String seat) {
        Ticket ticket = new Ticket();
        ticket.id = id;
        ticket.flightId = flight.id;
        ticket.passengerId = passenger.id;
        ticket.seat = seat;
        db.ticketModel().insertTicket(ticket);
    }

    private static Flight addFlight(final AppDatabase db, final String id, final String origin,
                                    final String destination, final Date takeoffTime, final Date landingTime) {
        Flight flight = new Flight();
        flight.id = id;
        flight.origin = origin;
        flight.destination = destination;
        flight.takeoffTime = takeoffTime;
        flight.landingTime = landingTime;
        db.flightModel().insertFlight(flight);
        return flight;
    }

    private static Passenger addPassenger(final AppDatabase db, final String id, final String name,
                                final String lastName, final int age) {
        Passenger user = new Passenger();
        user.id = id;
        user.age = age;
        user.name = name;
        user.lastName = lastName;
        db.passengerModel().insertPassenger(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db) {
        db.ticketModel().deleteAll();
        db.passengerModel().deleteAll();
        db.flightModel().deleteAll();

        Passenger passenger1 = addPassenger(db, "1", "Jason", "Seaver", 40);
        Passenger passenger2 = addPassenger(db, "2", "Mike", "Seaver", 12);
        addPassenger(db, "3", "Carol", "Seaver", 15);

        Flight flight1 = addFlight(db, "1", "Boston", "New York", getTodayPlusDays(0), getTodayPlusDays(1));
        Flight flight2 = addFlight(db, "2", "Michigan", "Houston", getTodayPlusDays(0), getTodayPlusDays(1));
        Flight flight3 = addFlight(db, "3", "Miami", "Detroit", getTodayPlusDays(0), getTodayPlusDays(0));
        Flight flight4 = addFlight(db, "4", "Seattle", "New York", getTodayPlusDays(0), getTodayPlusDays(2));
        addFlight(db, "5", "Los Angeles", "Hawaii", getTodayPlusDays(0), getTodayPlusDays(3));
        try {
            // Tickets are added with a delay, to have time for the UI to react to changes.

            Date today = getTodayPlusDays(0);
            Date yesterday = getTodayPlusDays(-1);
            Date twoDaysAgo = getTodayPlusDays(-2);
            Date lastWeek = getTodayPlusDays(-7);
            Date twoWeeksAgo = getTodayPlusDays(-14);

            addTicket(db, "1", passenger1, flight1, "1A");
            Thread.sleep(DELAY_MILLIS);
            addTicket(db, "2", passenger2, flight1, "1B");
            Thread.sleep(DELAY_MILLIS);
            addTicket(db, "3", passenger2, flight2, "17D");
            Thread.sleep(DELAY_MILLIS);
            addTicket(db, "4", passenger2, flight3, "8D");
            Thread.sleep(DELAY_MILLIS);
            addTicket(db, "5", passenger2, flight4, "20C");
            Log.d("DB", "Added tickets");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
