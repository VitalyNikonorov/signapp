package nikonorov.net.signapp.authscreen.presenter;

import android.support.annotation.NonNull;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.authscreen.model.ModelAuthScreen;
import nikonorov.net.signapp.authscreen.model.ModelAuthScreenImpl;
import nikonorov.net.signapp.authscreen.view.ViewAuthScreen;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;
import nikonorov.net.signapp.network.entity.NetworkResponse;
import nikonorov.net.signapp.utils.Logger;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by vitaly on 27.01.17.
 */

public class PresenterAuthScreenImpl implements PresenterAuthScreen {

    private enum SubscriptionsType{
        ONE_TIME_PASS(0),
        ENTER_BY_CODE(1),
        ENTER_BY_PASS(2);

        SubscriptionsType(int id) {
            this.id = id;
        }

        public final int id;
    }

    private ViewAuthScreen view;
    private ModelAuthScreen model;
    private FragmentType currentFragment;
    private Subscription[] subscriptions;

    public PresenterAuthScreenImpl(ViewAuthScreen view) {
        this.view = view;
        model = new ModelAuthScreenImpl(this);
        subscriptions = new Subscription[SubscriptionsType.values().length];

        for (int i = 0; i < subscriptions.length; i++){
            subscriptions[i] = Subscriptions.empty();
        }
        currentFragment = FragmentType.ONE_PASS_FRAGMENT;
    }

    private void unSubscribeAll(){
        for (Subscription subscription : subscriptions) {
            prepareSubscription(subscription);
        }
    }

    @Override
    public void onStart() {
        changeFragment(currentFragment, false);
        unSubscribeAll();
    }

    @Override
    public void onStop() {
        unSubscribeAll();
        view.hidePreloader();
    }

    private void changeFragment(FragmentType type, boolean needAddToStack){
        view.setFragment(type, needAddToStack);
        currentFragment = type;
    }

    @Override
    public void onMainActionBtnClick() {
        switch (currentFragment){
            case ONE_PASS_FRAGMENT:{
                view.showPreloader(currentFragment.preloaderMsg);
                AuthData data = view.getAuthData(currentFragment);

                prepareSubscription(subscriptions[SubscriptionsType.ONE_TIME_PASS.id]);

                subscriptions[SubscriptionsType.ONE_TIME_PASS.id] = model.requestOneTimePass(data).subscribe(new Observer<NetworkResponse>() {
                    @Override
                    public void onCompleted() {
                        view.hidePreloader();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidePreloader();
                        Logger.e(PresenterAuthScreen.class.getName(), e);
                    }

                    @Override
                    public void onNext(NetworkResponse networkResponse) {
                        view.hidePreloader();
                        switch (networkResponse.code){
                            case OK: {
                                view.setDescription(networkResponse.msg, FragmentType.ENTER_ONE_PASS_FRAGMENT);
                                view.setFragment(FragmentType.ENTER_ONE_PASS_FRAGMENT, true);
                                break;
                            }
                            case NETWORK_ERROR:
                            case WRONG_EMAIL:{
                                view.showErrorMessage(networkResponse.msg);
                                break;
                            }
                        }
                    }
                });
                break;
            }
            case ENTER_ONE_PASS_FRAGMENT: {

                view.showPreloader(currentFragment.preloaderMsg);
                AuthData data = view.getAuthData(currentFragment);

                prepareSubscription(subscriptions[SubscriptionsType.ENTER_BY_CODE.id]);

                subscriptions[SubscriptionsType.ENTER_BY_CODE.id] = model.enterByCode(data).subscribe(new Observer<NetworkResponse>() {
                    @Override
                    public void onCompleted() {
                        view.hidePreloader();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidePreloader();
                        Logger.e(PresenterAuthScreen.class.getName(), e);
                    }

                    @Override
                    public void onNext(NetworkResponse networkResponse) {
                        view.hidePreloader();
                        switch (networkResponse.code){
                            case OK: {
                                view.onLoggedIn();
                                break;
                            }
                            case NETWORK_ERROR:
                            case WRONG_CODE:{
                                view.showErrorMessage(networkResponse.msg);
                                break;
                            }
                        }
                    }
                });
                break;
            }

            case REGULAR_PASS_FRAGMENT: {
                view.showPreloader(currentFragment.preloaderMsg);
                AuthData data = view.getAuthData(currentFragment);

                prepareSubscription(subscriptions[SubscriptionsType.ENTER_BY_PASS.id]);

                subscriptions[SubscriptionsType.ENTER_BY_PASS.id] = model.enterByRegularPass(data).subscribe(new Observer<NetworkResponse>() {
                    @Override
                    public void onCompleted() {
                        view.hidePreloader();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidePreloader();
                        Logger.e(PresenterAuthScreen.class.getName(), e);
                    }

                    @Override
                    public void onNext(NetworkResponse networkResponse) {
                        view.hidePreloader();
                        switch (networkResponse.code){
                            case OK: {
                                view.onLoggedIn();
                                break;
                            }
                            case NETWORK_ERROR:
                            case WRONG_EMAIL_OR_PASS:{
                                view.showErrorMessage(networkResponse.msg);
                                break;
                            }
                        }
                    }
                });
                break;
            }
        }
    }

    private void prepareSubscription(@NonNull Subscription subscription){
        if (!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void onAdditionalBtnClick() {
        switch (currentFragment){
            case ONE_PASS_FRAGMENT: {
                changeFragment(FragmentType.REGULAR_PASS_FRAGMENT, true);
                break;
            }
            case ENTER_ONE_PASS_FRAGMENT: {
                view.showPreloader(R.string.sending_status);

                prepareSubscription(subscriptions[SubscriptionsType.ONE_TIME_PASS.id]);

                subscriptions[SubscriptionsType.ONE_TIME_PASS.id] = model.requestOneTimePass(null).subscribe(new Observer<NetworkResponse>() {
                    @Override
                    public void onCompleted() {
                        view.hidePreloader();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hidePreloader();
                        Logger.e(PresenterAuthScreen.class.getName(), e);
                    }

                    @Override
                    public void onNext(NetworkResponse networkResponse) {
                        view.hidePreloader();
                        switch (networkResponse.code){
                            case OK: {
                                break;
                            }
                            case NETWORK_ERROR:
                            case WRONG_EMAIL:{
                                view.showErrorMessage(networkResponse.msg);
                                break;
                            }
                        }
                    }
                });
                break;
            }
            case REGULAR_PASS_FRAGMENT: {
                changeFragment(FragmentType.ONE_PASS_FRAGMENT, true);
                break;
            }
        }
    }

    @Override
    public void onFragmentRestored(FragmentType type) {
        currentFragment = type;
    }
}
