package nikonorov.net.signapp.authscreen.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreen;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreenImpl;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentOnePassLogin;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;

/**
 * Created by vitaly on 27.01.17.
 */

public class AuthActivity extends AppCompatActivity implements ViewAuthScreen {

    private View authScreenContainer;
    private Fragment[] fragments = new Fragment[1];
    private PresenterAuthScreen presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);
        presenter = new PresenterAuthScreenImpl(this);
        authScreenContainer = findViewById(R.id.auth_screen_container);

        fragments[0] = new FragmentOnePassLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void setFragment(FragmentType fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.auth_screen_container, fragments[fragment.id]);
        transaction.commit();
    }
}
