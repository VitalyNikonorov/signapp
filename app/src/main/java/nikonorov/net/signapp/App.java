package nikonorov.net.signapp;

import android.app.Application;

import nikonorov.net.signapp.data.DataSourceImpl;
import nikonorov.net.signapp.di.AppComponent;
import nikonorov.net.signapp.di.AppModule;
import nikonorov.net.signapp.di.DaggerAppComponent;
import nikonorov.net.signapp.network.NetworkMock;

/**
 * Main application class
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
                .dataSourceImpl(new DataSourceImpl())
                .networkMock(new NetworkMock())
                .build();
    }
}
