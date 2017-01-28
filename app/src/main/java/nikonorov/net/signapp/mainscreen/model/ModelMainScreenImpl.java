package nikonorov.net.signapp.mainscreen.model;

import javax.inject.Inject;

import nikonorov.net.signapp.App;
import nikonorov.net.signapp.data.DataSource;
import nikonorov.net.signapp.mainscreen.presenter.PresenterMainScreen;

/**
 * Created by vitaly on 27.01.17.
 */

public class ModelMainScreenImpl implements ModelMainScreen {

    private PresenterMainScreen presenter;

    @Inject
    DataSource dataSource;

    public ModelMainScreenImpl(PresenterMainScreen presenter) {
        this.presenter = presenter;
        App.component.inject(this);
    }

    @Override
    public void logout() {
        dataSource.clearToken();
    }
}
