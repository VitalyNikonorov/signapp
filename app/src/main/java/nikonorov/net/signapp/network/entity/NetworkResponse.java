package nikonorov.net.signapp.network.entity;

import nikonorov.net.signapp.network.entity.CodeResponse;

/**
 * Created by vitaly on 28.01.17.
 */

public class NetworkResponse {

    public final CodeResponse code;
    public final String msg;

    public NetworkResponse(CodeResponse code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
