package nikonorov.net.signapp.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import nikonorov.net.signapp.App;
import nikonorov.net.signapp.data.DataSource;
import nikonorov.net.signapp.network.NetworkManager;

/**
 * Service for synchronization
 *
 */

public class SyncService extends Service {

    @Inject
    DataSource dataSource;

    @Inject
    NetworkManager networkManager;

    @Override
    public void onCreate() {
        super.onCreate();
        App.component.inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
