package wpi.team1021.roompersistencetutorial.step1_solution;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import java.util.List;
import java.util.Locale;

import wpi.team1021.R;
import wpi.team1021.roompersistencetutorial.db.AppDatabase;
import wpi.team1021.roompersistencetutorial.db.User;
import wpi.team1021.roompersistencetutorial.db.utils.DatabaseInitializer;

public class UsersActivity extends AppCompatActivity {

    private AppDatabase mDb;

    private TextView mYoungUsersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.db_activity1);

        mYoungUsersTextView = findViewById(R.id.young_users_tv);

        // Note: Db references should not be in an activity.
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        populateDb();

        fetchData();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
        List<User> youngUsers = mDb.userModel().findUsersYoungerThanSolution(35);
        for (User youngUser : youngUsers) {
            sb.append(String.format(Locale.US,
                    "%s, %s (%d)\n", youngUser.lastName, youngUser.name, youngUser.age));
        }
        mYoungUsersTextView.setText(sb);
    }
}
