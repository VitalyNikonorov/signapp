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

    /**
     * Method for set current fragment type after restoring fragment
     * @param type - current started fragment
     */
    void onFragmentRestored(FragmentType type);

}
