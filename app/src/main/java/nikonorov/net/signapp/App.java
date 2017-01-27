package nikonorov.net.signapp;

import android.app.Application;

import nikonorov.net.signapp.data.DataSource;
import nikonorov.net.signapp.di.AppComponent;
import nikonorov.net.signapp.di.AppModule;
import nikonorov.net.signapp.di.DaggerAppComponent;

/**
 * Created by vitaly on 27.01.17.
 */

public class App extends Application {

    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataSource(new DataSource())
                .build();
    }

}
