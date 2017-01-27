package nikonorov.net.signapp.authscreen.model;

import nikonorov.net.signapp.network.NetworkResponse;
import rx.Observable;

/**
 * Created by vitaly on 27.01.17.
 */

public interface ModelAuthScreen {

    Observable<NetworkResponse> requestOnTimePass(String email);

}
