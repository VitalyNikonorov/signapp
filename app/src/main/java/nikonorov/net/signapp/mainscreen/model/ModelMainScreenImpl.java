package nikonorov.net.signapp.mainscreen.model;

import nikonorov.net.signapp.mainscreen.presenter.PresenterMainScreen;

/**
 * Created by vitaly on 27.01.17.
 */

public class ModelMainScreenImpl implements ModelMainScreen {

    private PresenterMainScreen presenter;

    public ModelMainScreenImpl(PresenterMainScreen presenter) {
        this.presenter = presenter;
    }

    @Override
    public void logout() {

    }
}
