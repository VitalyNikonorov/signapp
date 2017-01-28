package nikonorov.net.signapp.di;

import javax.inject.Singleton;

import dagger.Component;
import nikonorov.net.signapp.authscreen.model.ModelAuthScreenImpl;
import nikonorov.net.signapp.data.DataSourceImpl;
import nikonorov.net.signapp.network.NetworkMock;

/**
 * Created by vitaly on 27.01.17.
 */

@Singleton
@Component(modules = {AppModule.class, DataSourceImpl.class, NetworkMock.class})
public interface AppComponent {

    void inject(ModelAuthScreenImpl modelAuthScreen);
}
