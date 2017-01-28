package nikonorov.net.signapp.mainscreen.presenter;

import nikonorov.net.signapp.mainscreen.model.ModelMainScreen;
import nikonorov.net.signapp.mainscreen.model.ModelMainScreenImpl;
import nikonorov.net.signapp.mainscreen.view.ViewMainScreen;

/**
 * Created by vitaly on 27.01.17.
 */

public class PresenterMainScreenImpl implements PresenterMainScreen {

    private ViewMainScreen view;
    private ModelMainScreen model;

    public PresenterMainScreenImpl(ViewMainScreen view) {
        this.view = view;
        this.model = new ModelMainScreenImpl(this);
    }

    @Override
    public void onLogoutClick() {
        model.logout();
        view.openLoginActivity();
    }
}
