package nikonorov.net.signapp.authscreen.view;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

public class AuthActivity extends AppCompatActivity implements ViewAuthScreen, View.OnClickListener {

    private View authScreenContainer;
    private Fragment[] fragments = new Fragment[1];
    private PresenterAuthScreen presenter;
    private Dialog preloaderDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);
        presenter = new PresenterAuthScreenImpl(this);
        authScreenContainer = findViewById(R.id.auth_screen_container);

        fragments[0] = new FragmentOnePassLogin();
        initProgressDialog();
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

    private void initProgressDialog() {
        preloaderDialog = new Dialog(AuthActivity.this);
        preloaderDialog.setContentView(R.layout.preloader_popup);
        preloaderDialog.setCancelable(false);
    }

    @Override
    public void showPreloader() {
        preloaderDialog.show();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(authScreenContainer, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View view) {
        String tag = view.getTag().toString();

        if (getResources().getString(R.string.tag_main_btn).equals(tag)){
            presenter.onMainActionBtnClick();
        } else {
            if (getResources().getString(R.string.tag_additional_btn).equals(tag)){
                presenter.onAdditionalBtnClick();
            }
        }
    }
}
