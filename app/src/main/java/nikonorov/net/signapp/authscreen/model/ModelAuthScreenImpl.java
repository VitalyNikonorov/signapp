package nikonorov.net.signapp.authscreen.model;

import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreen;

/**
 * Created by vitaly on 27.01.17.
 */

public class ModelAuthScreenImpl implements ModelAuthScreen {

    private PresenterAuthScreen presenter;

    public ModelAuthScreenImpl(PresenterAuthScreen presenter) {
        this.presenter = presenter;
    }
}
