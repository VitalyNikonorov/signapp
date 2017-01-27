package nikonorov.net.signapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.utils.Logger;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by vitaly on 27.01.17.
 */
@Module
public class NetworkMock implements NetworkManager {
    private final long DELAY = 1500l;
    private final String RIGHT_PHONE_EMAIL = "1@1.com";
    private final String RIGHT_EMAIL = "a@a.com";

    private final String PHONE = " телефон +7916*****89";
    private final String EMAIL = " почту a@a.com";


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

    @Override
    public Observable<NetworkResponse> requestOnTimePass(AuthData data) {

        return Observable.just(data.login)
                .delay(DELAY, TimeUnit.MILLISECONDS)
                .map(new Func1<String, NetworkResponse>() {
                    @Override
                    public NetworkResponse call(String s) {
                        if (isNetworkAvailable()){

                            if (RIGHT_EMAIL.equals(s)){
                                return new NetworkResponse(CodeResponse.OK, EMAIL);
                            } else if (RIGHT_PHONE_EMAIL.equals(s)){
                                return new NetworkResponse(CodeResponse.OK, PHONE);
                            } else {
                                return new NetworkResponse(CodeResponse.WRONG_EMAIL, appContext.getString(R.string.wrong_email));
                            }
                        } else {
                            return new NetworkResponse(CodeResponse.NETWORK_ERROR, appContext.getString(R.string.network_error_description));
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
