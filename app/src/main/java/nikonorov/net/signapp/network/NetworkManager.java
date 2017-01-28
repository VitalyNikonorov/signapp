package nikonorov.net.signapp.network;

import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.network.entity.NetworkResponse;
import rx.Observable;

/**
 * Main entity for working with network
 * it should be done by any way, fir example with retrofit library
 */


public interface NetworkManager {

    Observable<NetworkResponse> requestOneTimePass(AuthData data);
    Observable<NetworkResponse> enterByCode(AuthData data);
    Observable<NetworkResponse> enterByRegularPass(AuthData data);

}
