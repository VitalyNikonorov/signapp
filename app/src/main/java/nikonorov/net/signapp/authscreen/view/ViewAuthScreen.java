package nikonorov.net.signapp.authscreen.view;

import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;

/**
 * Created by vitaly on 27.01.17.
 */

public interface ViewAuthScreen {

    void setFragment(FragmentType type, boolean needAddToStack);
    void showPreloader(int stringId);
    void showErrorMessage(String message);
    void hidePreloader();
    AuthData getAuthData(FragmentType type);
    void setDescription(String s, FragmentType type);
    void onLoggedIn();

}
