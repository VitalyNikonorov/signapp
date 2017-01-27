package nikonorov.net.signapp.authscreen.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.view.AuthActivity;

/**
 * Created by vitaly on 27.01.17.
 */

public class FragmentOnePassLogin extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        View mainActionBtn = view.findViewById(R.id.login_action_btn);
        mainActionBtn.setTag(getResources().getString(R.string.tag_main_btn));
        mainActionBtn.setOnClickListener((AuthActivity)getActivity());
        return view;
    }
}
