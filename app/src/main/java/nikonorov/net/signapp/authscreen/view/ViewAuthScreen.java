package nikonorov.net.signapp.authscreen.view;

import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;

/**
 * Created by vitaly on 27.01.17.
 */

public interface ViewAuthScreen {

    void setFragment(FragmentType type, boolean needAddToStack);
    void showPreloader();
    void showErrorMessage(String message);

}
