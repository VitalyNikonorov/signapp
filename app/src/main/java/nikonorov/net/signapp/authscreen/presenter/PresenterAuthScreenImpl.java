package nikonorov.net.signapp.authscreen.presenter;

import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.authscreen.model.ModelAuthScreen;
import nikonorov.net.signapp.authscreen.model.ModelAuthScreenImpl;
import nikonorov.net.signapp.authscreen.view.ViewAuthScreen;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;
import nikonorov.net.signapp.network.NetworkResponse;
import nikonorov.net.signapp.utils.Logger;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by vitaly on 27.01.17.
 */

public class PresenterAuthScreenImpl implements PresenterAuthScreen {

    private ViewAuthScreen view;
    private ModelAuthScreen model;
    private FragmentType currentFragment;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public PresenterAuthScreenImpl(ViewAuthScreen view) {
        this.view = view;
        model = new ModelAuthScreenImpl(this);
    }



    @Override
    public void onStart() {
        changeFragment(FragmentType.ONE_PASS_FRAGMENT, false);
    }

    @Override
    public void onStop() {
        subscriptions.unsubscribe();
    }

    private void changeFragment(FragmentType type, boolean needAddToStack){
        view.setFragment(type, needAddToStack);
        currentFragment = type;
    }

    @Override
    public void onMainActionBtnClick() {
        switch (currentFragment){
            case ONE_PASS_FRAGMENT:{
                view.showPreloader();
                AuthData data = view.getAuthData(currentFragment);

                Subscription subscription = model.requestOnTimePass(data).subscribe(new Observer<NetworkResponse>() {
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

                subscriptions.add(subscription);
                break;
            }
            case ENTER_ONE_PASS_FRAGMENT: {

                break;
            }
            case REGULAR_PASS_FRAGMENT: {

                break;
            }
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
                changeFragment(FragmentType.REGULAR_PASS_FRAGMENT, true);
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
