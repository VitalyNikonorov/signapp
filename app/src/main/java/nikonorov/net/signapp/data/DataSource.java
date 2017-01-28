package nikonorov.net.signapp.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vitaly on 27.01.17.
 */

@Module
public class DataSource {

    private Context appContext;

    public DataSource() {
    }

    public DataSource(Context appContext) {
            this.appContext = appContext;
    }


    @Provides
    @Singleton
    public DataSource provideDataSource(Context appContext){
        return new DataSource(appContext);
    }
}
