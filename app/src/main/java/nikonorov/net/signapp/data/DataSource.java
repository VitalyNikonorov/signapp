package nikonorov.net.signapp.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vitaly on 27.01.17.
 */

@Module
public class DataSource {

    public DataSource() {
    }

    @Provides
    @Singleton
    public DataSource provideDataSource(){
        return new DataSource();
    }
}
