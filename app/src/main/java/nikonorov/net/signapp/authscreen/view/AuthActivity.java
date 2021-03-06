package nikonorov.net.signapp.authscreen.view;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreen;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreenImpl;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentAuth;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;
import nikonorov.net.signapp.mainscreen.view.MainActivity;

/**
 * Created by vitaly on 27.01.17.
 */

public class AuthActivity extends AppCompatActivity implements ViewAuthScreen, View.OnClickListener {

    private View authScreenContainer;
    private FragmentAuth[] fragments = new FragmentAuth[FragmentType.values().length];
    private PresenterAuthScreen presenter;
    private Dialog preloaderDialog;
    private TextView preloaderMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);
        presenter = new PresenterAuthScreenImpl(this);
        authScreenContainer = findViewById(R.id.auth_screen_container);

        fragments[FragmentType.ONE_PASS_FRAGMENT.id] = new FragmentAuth();
        fragments[FragmentType.ONE_PASS_FRAGMENT.id].setType(FragmentType.ONE_PASS_FRAGMENT);
        fragments[FragmentType.ENTER_ONE_PASS_FRAGMENT.id] = new FragmentAuth();
        fragments[FragmentType.ENTER_ONE_PASS_FRAGMENT.id].setType(FragmentType.ENTER_ONE_PASS_FRAGMENT);
        fragments[FragmentType.REGULAR_PASS_FRAGMENT.id] = new FragmentAuth();
        fragments[FragmentType.REGULAR_PASS_FRAGMENT.id].setType(FragmentType.REGULAR_PASS_FRAGMENT);

        initProgressDialog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void setFragment(FragmentType fragment, boolean needAddToStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.auth_screen_container, fragments[fragment.id]);
        if (needAddToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        fragments[fragment.id].refreshView();
    }

    private void initProgressDialog() {
        preloaderDialog = new Dialog(AuthActivity.this);
        preloaderDialog.setContentView(R.layout.preloader_popup);
        preloaderDialog.setCancelable(false);
        preloaderMsg = (TextView) preloaderDialog.findViewById(R.id.preloader_msg);
    }

    @Override
    public void showPreloader(int stringId) {
        preloaderMsg.setText(stringId);
        preloaderDialog.show();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(authScreenContainer, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void hidePreloader() {
        if (preloaderDialog != null) {
            preloaderDialog.hide();
        }
    }

    @Override
    public AuthData getAuthData(FragmentType type) {
        return fragments[type.id].getAuthData();
    }

    @Override
    public void setDescription(String s, FragmentType type) {
        fragments[type.id].setDescription(s);
    }

    @Override
    public void onLoggedIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        preloaderDialog.dismiss();
        preloaderDialog = null;
        this.finish();
    }

    public void onFragmentRestored(FragmentType type){
        presenter.onFragmentRestored(type);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
