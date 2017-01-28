package nikonorov.net.signapp.data;

/**
 * Created by vitaly on 28.01.17.
 */

public interface DataSource {

    void saveToken(String token);
    String getToken();
    void clearToken();
}
