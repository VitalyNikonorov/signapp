package nikonorov.net.signapp.network.entity;

/**
 * Enum of responses from server (if it will be got in app)
 */

public enum CodeResponse {

    OK,
    WRONG_EMAIL,
    WRONG_CODE,
    WRONG_EMAIL_OR_PASS,
    NETWORK_ERROR;
}
