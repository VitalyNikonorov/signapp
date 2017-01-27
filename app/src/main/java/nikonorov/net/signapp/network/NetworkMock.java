package nikonorov.net.signapp.network;

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

    @Provides
    @Singleton
    public NetworkManager provideNetworkMock(){
        return new NetworkMock();
    }
}
