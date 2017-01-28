package nikonorov.net.signapp.mainscreen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.view.AuthActivity;
import nikonorov.net.signapp.mainscreen.presenter.PresenterMainScreen;
import nikonorov.net.signapp.mainscreen.presenter.PresenterMainScreenImpl;

/**
 * Created by vitaly on 27.01.17.
 */

public class MainActivity extends AppCompatActivity implements ViewMainScreen, View.OnClickListener {

    private PresenterMainScreen presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        presenter = new PresenterMainScreenImpl(this);
        findViewById(R.id.logout_btn).setOnClickListener(this);
        getSupportActionBar().setTitle(R.string.exit);
    }

    @Override
    public void onClick(View view) {
        presenter.onLogoutClick();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        this.finish();
    }
}
