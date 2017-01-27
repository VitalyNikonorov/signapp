package nikonorov.net.signapp.network;

import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */


public interface NetworkManager {

    Observable<NetworkResponse> requestOnTimePass(String email);

}
