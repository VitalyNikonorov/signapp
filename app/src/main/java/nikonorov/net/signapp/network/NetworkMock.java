package nikonorov.net.signapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vitaly on 27.01.17.
 */
@Module
public class NetworkMock implements NetworkManager {

    public NetworkMock() {
    }

    public NetworkMock(Context appContext) {
        this.appContext = appContext;
    }

    private Context appContext;

    @Provides
    @Singleton
    public NetworkManager provideNetworkMock(Context appContext){
        return new NetworkMock(appContext);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
