package nikonorov.net.signapp.authscreen.model;

/**
 * Entity for transporting auth data of user in app
 */

public class AuthData {

    public final String login;
    public final String pass;

    public AuthData(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
}
