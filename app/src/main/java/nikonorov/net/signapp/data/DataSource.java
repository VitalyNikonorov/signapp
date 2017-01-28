package nikonorov.net.signapp.data;

import android.support.annotation.Nullable;

/**
 * Main entity for working with data
 * here will be api for working with db if it will be need
 */

public interface DataSource {

    void saveToken(String token);
    @Nullable String getToken();
    void clearToken();
}
