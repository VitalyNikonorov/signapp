package nikonorov.net.signapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nikonorov.net.signapp.R;

/**
 * Created by vitaly on 27.01.17.
 */

@Module
public class DataSourceImpl implements DataSource {

    private Context appContext;

    private SharedPreferences sharedPreferences;
    public DataSourceImpl() {
    }

    public DataSourceImpl(Context appContext) {
            this.appContext = appContext;
    }

    @Provides
    @Singleton
    public DataSourceImpl provideDataSource(Context appContext){
        return new DataSourceImpl(appContext);
    }



    @Override
    public void saveToken(String token) {
        sharedPreferences = appContext.getSharedPreferences(appContext.getString(R.string.shared_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(appContext.getString(R.string.shared_token), token);
        editor.commit();
    }



    @Override
    @Nullable
    public String getToken() {
        sharedPreferences = appContext.getSharedPreferences(appContext.getString(R.string.shared_file), Context.MODE_PRIVATE);
        return sharedPreferences.getString(appContext.getString(R.string.shared_token), null);
    }

    @Override
    public void clearToken() {
        saveToken(null);
    }
}
