package wpi.team1021.roompersistencetutorial.step5_solution;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import wpi.team1021.roompersistencetutorial.airportDB.AppDatabase;
import wpi.team1021.roompersistencetutorial.airportDB.TicketWithPassengerAndFlight;
import wpi.team1021.roompersistencetutorial.airportDB.utils.DatabaseInitializer;


public class CustomResultViewModel extends AndroidViewModel {

    private LiveData<String> mTicketsResult;

    private AppDatabase mDb;

    public CustomResultViewModel(Application application) {
        super(application);
    }

    public LiveData<String> getTicketsResult() {
        return mTicketsResult;
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);

        // Receive changes
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date tomorrow = calendar.getTime();
        LiveData<List<TicketWithPassengerAndFlight>> loans
                = mDb.ticketModel().findTicketsByPassengerAndLandingTime("Mike", tomorrow);

        // Instead of exposing the list of Tickets, we can apply a transformation and expose Strings.
        mTicketsResult = Transformations.map(loans,
                new Function<List<TicketWithPassengerAndFlight>, String>() {
                    @Override
                    public String apply(List<TicketWithPassengerAndFlight> ticketWithPassengerAndFlight) {
                        StringBuilder sb = new StringBuilder();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
                                Locale.US);

                        for (TicketWithPassengerAndFlight ticket : ticketWithPassengerAndFlight) {
                            sb.append(String.format("Passenger: %s, origin: %s, destination: %s\n  " +
                                            "(takeoff: %s, landing: %s)\n\n",
                                    ticket.passengerName,
                                    ticket.origin,
                                    ticket.destination,
                                    simpleDateFormat.format(ticket.takeoffTime),
                                    simpleDateFormat.format(ticket.landingTime)));
                        }
                        return sb.toString();
                    }
                });
    }
}
