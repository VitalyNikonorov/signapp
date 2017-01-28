package nikonorov.net.signapp.authscreen.model;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import nikonorov.net.signapp.App;
import nikonorov.net.signapp.authscreen.presenter.PresenterAuthScreen;
import nikonorov.net.signapp.data.DataSource;
import nikonorov.net.signapp.network.NetworkManager;
import nikonorov.net.signapp.network.entity.NetworkResponse;
import nikonorov.net.signapp.utils.Logger;
import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */

public class ModelAuthScreenImpl implements ModelAuthScreen {

    private AuthData lastData = null;
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
        if (data == null){
            if (lastData == null){
                Logger.e(this.getClass().getName(), new Throwable());
                return Observable.empty();
            } else {
                return networkManager.requestOneTimePass(lastData);
            }
        } else {
            lastData = data;
            return networkManager.requestOneTimePass(data);
        }
    }

    @Override
    public Observable<NetworkResponse> enterByCode(AuthData data) {
        return networkManager.enterByCode(data);
    }

    @Override
    public Observable<NetworkResponse> enterByRegularPass(AuthData data) {
        return networkManager.enterByRegularPass(data);
    }

    @Override
    public void saveToken(String token) {
        dataSource.saveToken(token);
    }

    @Nullable
    @Override
    public String checkToken() {
        return dataSource.getToken();
    }
}
