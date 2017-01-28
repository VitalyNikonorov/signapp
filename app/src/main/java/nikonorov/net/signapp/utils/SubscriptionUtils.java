package nikonorov.net.signapp.utils;

import android.support.annotation.NonNull;

import rx.Subscription;

/**
 * Created by vitaly on 28.01.17.
 */

public class SubscriptionUtils {

    public static void prepareSubscription(@NonNull Subscription subscription){
        if (!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

}
