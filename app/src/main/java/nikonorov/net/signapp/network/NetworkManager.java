package nikonorov.net.signapp.network;

import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.network.entity.NetworkResponse;
import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */


public interface NetworkManager {

    Observable<NetworkResponse> requestOneTimePass(AuthData data);
    Observable<NetworkResponse> enterByCode(AuthData data);
    Observable<NetworkResponse> enterByRegularPass(AuthData data);

}
