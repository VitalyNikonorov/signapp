package nikonorov.net.signapp.authscreen.presenter;

import nikonorov.net.signapp.authscreen.model.ModelAuthScreen;
import nikonorov.net.signapp.authscreen.model.ModelAuthScreenImpl;
import nikonorov.net.signapp.authscreen.view.ViewAuthScreen;
import nikonorov.net.signapp.authscreen.view.fragments.FragmentType;

/**
 * Created by vitaly on 27.01.17.
 */

public class PresenterAuthScreenImpl implements PresenterAuthScreen {

    private ViewAuthScreen view;
    private ModelAuthScreen model;
    private FragmentType currentFragment;

    public PresenterAuthScreenImpl(ViewAuthScreen view) {
        this.view = view;
        model = new ModelAuthScreenImpl(this);
    }

    @Override
    public void onStart() {
        changeFragment(FragmentType.ONE_PASS_FRAGMENT, false);
    }

    private void changeFragment(FragmentType type, boolean needAddToStack){
        view.setFragment(type, needAddToStack);
        currentFragment = type;
    }

    @Override
    public void onMainActionBtnClick() {
        switch (currentFragment){
            case ONE_PASS_FRAGMENT:{
                changeFragment(FragmentType.ENTER_ONE_PASS_FRAGMENT, true);
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
