package nikonorov.net.signapp.network;

import nikonorov.net.signapp.authscreen.model.AuthData;
import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */


public interface NetworkManager {

    Observable<NetworkResponse> requestOnTimePass(AuthData data);

}
