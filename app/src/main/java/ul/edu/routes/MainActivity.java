package ul.edu.routes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // quick and dirty way to store the login state
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
        // start MapsActivity only when the user is logged
        if (login) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You must sign-in first", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
