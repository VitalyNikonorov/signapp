package nikonorov.net.signapp.authscreen.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by vitaly on 27.01.17.
 */

public abstract class AuthFragment extends Fragment {

    protected FragmentType type;

    public void setType(FragmentType type) {
        this.type = type;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView();
    }

    protected abstract void initView();
}
