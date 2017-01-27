package nikonorov.net.signapp.authscreen.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nikonorov.net.signapp.R;

/**
 * Created by vitaly on 27.01.17.
 */

public class AuthActivity extends AppCompatActivity implements ViewAuthScreen {

    private View authScreenContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);
        authScreenContainer = findViewById(R.id.auth_screen_container);
    }
}
