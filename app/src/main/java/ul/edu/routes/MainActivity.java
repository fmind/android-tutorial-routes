package ul.edu.routes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // store login state (quick & dirty)
    public static boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void startSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);

        startActivity(intent);
    }

    public void startMapsActivity(View view) {
        if (login) {
            Intent intent = new Intent(this, MapsActivity.class);

            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You must sign in to access this activity", Toast.LENGTH_SHORT);

            toast.show();
        }
    }
}
