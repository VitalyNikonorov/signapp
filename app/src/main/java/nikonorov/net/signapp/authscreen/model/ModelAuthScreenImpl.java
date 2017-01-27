package nikonorov.net.signapp.authscreen.model;

import javax.inject.Inject;

import nikonorov.net.signapp.App;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreen;
import nikonorov.net.signapp.data.DataSource;

/**
 * Created by vitaly on 27.01.17.
 */

public class ModelAuthScreenImpl implements ModelAuthScreen {

    private PresenterAuthScreen presenter;
    @Inject
    DataSource dataSource;

    public ModelAuthScreenImpl(PresenterAuthScreen presenter) {
        App.component.inject(this);
        this.presenter = presenter;
    }
}
