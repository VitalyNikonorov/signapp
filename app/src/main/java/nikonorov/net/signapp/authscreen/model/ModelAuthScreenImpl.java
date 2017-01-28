package nikonorov.net.signapp.authscreen.model;

import javax.inject.Inject;

import nikonorov.net.signapp.App;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreen;
import nikonorov.net.signapp.data.DataSource;
import nikonorov.net.signapp.network.NetworkManager;
import nikonorov.net.signapp.network.entity.NetworkResponse;
import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */

public class ModelAuthScreenImpl implements ModelAuthScreen {

    private PresenterAuthScreen presenter;
    @Inject
    DataSource dataSource;

    @Inject
    NetworkManager networkManager;

    public ModelAuthScreenImpl(PresenterAuthScreen presenter) {
        App.component.inject(this);
        this.presenter = presenter;
    }

    @Override
    public Observable<NetworkResponse> requestOneTimePass(AuthData data) {
        return networkManager.requestOnTimePass(data);
    }

    @Override
    public Observable<NetworkResponse> enterByCode(AuthData data) {
        return networkManager.enterByCode(data);
    }

    @Override
    public Observable<NetworkResponse> enterByRegularPass(AuthData data) {
        return networkManager.enterByRegularPass(data);
    }
}
