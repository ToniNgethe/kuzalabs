package ke.co.kuzalabs.usermanagementportal.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.ui.activities.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /* redirect after 1 sec to login area */
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();
        }, 1000);
    }
}
