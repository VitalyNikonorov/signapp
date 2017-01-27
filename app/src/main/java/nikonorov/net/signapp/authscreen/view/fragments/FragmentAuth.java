package nikonorov.net.signapp.authscreen.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.view.AuthActivity;

/**
 * Created by vitaly on 27.01.17.
 */

public class FragmentAuth extends Fragment {

    private TextView description;
    private EditText loginField;
    private EditText passField;
    private TextView additionalBtn;
    private TextView loginTip;
    private Button mainActionBtn;

    private FragmentType type;

    public void setType(FragmentType type) {
        this.type = type;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mainActionBtn = (Button) view.findViewById(R.id.login_action_btn);
        mainActionBtn.setTag(getResources().getString(R.string.tag_main_btn));
        mainActionBtn.setOnClickListener((AuthActivity)getActivity());

        additionalBtn = (TextView) view.findViewById(R.id.additional_btn);
        additionalBtn.setTag(getResources().getString(R.string.tag_additional_btn));
        additionalBtn.setOnClickListener((AuthActivity) getActivity());

        description = (TextView) view.findViewById(R.id.login_description);
        loginField = (EditText) view.findViewById(R.id.login_login_field);
        passField = (EditText) view.findViewById(R.id.login_pass_field);
        loginTip = (TextView) view.findViewById(R.id.login_tip_tv);

        return view;
    }

    private void initView() {
        description.setText(type.description);
        loginField.setHint(type.fieldHints[0]);

        if (type.fieldHints.length == 1){
            passField.setVisibility(View.GONE);
        } else {
            passField.setVisibility(View.VISIBLE);
            passField.setHint(type.fieldHints[1]);
        }

        mainActionBtn.setText(type.mainBtnCaption);
        additionalBtn.setText(type.additionalBtnCaption);
        loginTip.setText(type.tip);
    }
}
