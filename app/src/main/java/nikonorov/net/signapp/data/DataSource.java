package nikonorov.net.signapp.data;

import android.support.annotation.Nullable;

/**
 * Created by vitaly on 28.01.17.
 */

public interface DataSource {

    void saveToken(String token);
    @Nullable String getToken();
    void clearToken();
}
