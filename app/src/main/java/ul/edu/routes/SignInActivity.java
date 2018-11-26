package ul.edu.routes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";

    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient client;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // TODO: configure sign-in to request the user's ID, email address, and basic profile

        // TODO: build a GoogleSignInClient with the options specified by gso
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO: if the user is already signed, account will be non-null
        account = null;

        updateUI(account);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO: result returned from launching the Intent from getSignInAccountFromIntent(...)
        if (requestCode == RC_SIGN_IN) {
            // the task returned is always completed (no need to attach a listener)
        }
    }

    public void signIn(View view) {
        Intent signInIntent = client.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut(View view) {
        client.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        handleSignOutResult(task);
                    }});
    }

    public void updateUI(GoogleSignInAccount account) {
        if (account == null) {
            MainActivity.login = false;
            Log.i(TAG, "User logged out");
            Toast.makeText(getApplicationContext(), "Sign-out", Toast.LENGTH_LONG).show();
        } else {
            MainActivity.login = true;
            Log.i(TAG, "User logged in");
            Log.d(TAG, "Name: " + account.getDisplayName());
            Log.d(TAG, "Email: " + account.getEmail());
            Toast.makeText(getApplicationContext(), "Sign-in: " + account.getEmail(), Toast.LENGTH_LONG).show();
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        // TODO: handle sign in result in case the user log in (or not)
    }

    private void handleSignOutResult(Task<Void> completedTask) {
        // TODO: handle sign out in case the user log out
    }
}
