package nikonorov.net.signapp.authscreen.presenter;

import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;

/**
 * Created by vitaly on 27.01.17.
 */

public interface PresenterAuthScreen {

    void onStart();
    void onStop();
    void onMainActionBtnClick();
    void onAdditionalBtnClick();
    void onFragmentRestored(FragmentType type);

}
