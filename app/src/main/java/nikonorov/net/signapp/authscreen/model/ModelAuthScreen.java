package nikonorov.net.signapp.authscreen.model;

import android.support.annotation.Nullable;

import nikonorov.net.signapp.network.entity.NetworkResponse;
import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */

public interface ModelAuthScreen {

    Observable<NetworkResponse> requestOneTimePass(AuthData data);
    Observable<NetworkResponse> enterByCode(AuthData data);
    Observable<NetworkResponse> enterByRegularPass(AuthData data);
    void saveToken(String token);
    @Nullable String checkToken();

}
