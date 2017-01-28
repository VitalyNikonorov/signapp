package nikonorov.net.signapp.authscreen.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import nikonorov.net.signapp.R;
import nikonorov.net.signapp.authscreen.model.AuthData;
import nikonorov.net.signapp.authscreen.view.AuthActivity;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

import static nikonorov.net.signapp.utils.SubscriptionUtils.prepareSubscription;

/**
 * Created by vitaly on 27.01.17.
 */

public class FragmentAuth extends Fragment implements TextWatcher {

    private static final int ONE_SECOND_MILLIS = 1000;
    private static final int DELAY_IN_SECONDS = 60;

    private TextView description;
    private EditText loginField;
    private EditText passField;
    private TextView additionalBtn;
    private TextView loginTip;
    private Button mainActionBtn;

    private View rootView;

    private FragmentType type;
    private String sendOn;
    private Subscription subscription = Subscriptions.empty();

    public void setType(FragmentType type) {
        this.type = type;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        description.setText(getString(type.description, sendOn));
        ((AuthActivity)getActivity()).onFragmentRestored(type);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mainActionBtn = (Button) rootView.findViewById(R.id.login_action_btn);
        mainActionBtn.setTag(getResources().getString(R.string.tag_main_btn));
        mainActionBtn.setOnClickListener((AuthActivity)getActivity());

        additionalBtn = (TextView) rootView.findViewById(R.id.additional_btn);
        additionalBtn.setTag(getResources().getString(R.string.tag_additional_btn));
        additionalBtn.setOnClickListener((AuthActivity) getActivity());

        description = (TextView) rootView.findViewById(R.id.login_description);
        loginField = (EditText) rootView.findViewById(R.id.login_login_field);

        loginField.addTextChangedListener(this);

        passField = (EditText) rootView.findViewById(R.id.login_pass_field);
        passField.addTextChangedListener(this);

        loginTip = (TextView) rootView.findViewById(R.id.login_tip_tv);

        return rootView;
    }

    private void checkMainBtnState(){
        if (TextUtils.isEmpty(loginField.getText())){
            mainActionBtn.setEnabled(false);
        } else {
            if (passField.getVisibility() == View.VISIBLE){
                if (TextUtils.isEmpty(passField.getText())){
                    mainActionBtn.setEnabled(false);
                } else {
                    mainActionBtn.setEnabled(true);
                }
            } else {
                mainActionBtn.setEnabled(true);
            }
        }
    }

    public AuthData getAuthData(){
        String login = loginField.getText().toString();
        String pass = null;
        if (passField.getVisibility() == View.VISIBLE){
            pass = passField.getText().toString();
        }
        return new AuthData(login, pass);
    }

    public void setDescription(String s){
        sendOn = s;
    }

    private void setHomeButton(boolean isNeed){
        ((AuthActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(isNeed);
        ((AuthActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(isNeed);
    }

    public void refreshView(){
        if (rootView != null){
            initView();
        }
    }

    private void initView() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(type.title);
        loginField.setHint(type.fieldHints[0]);

        mainActionBtn.setText(type.mainBtnCaption);
        additionalBtn.setText(type.additionalBtnCaption);
        loginTip.setText(type.tip);
        checkMainBtnState();

        switch (type){
            case ONE_PASS_FRAGMENT:
                setHomeButton(false);
                passField.setVisibility(View.GONE);
                description.setText(type.description);
                break;
            case ENTER_ONE_PASS_FRAGMENT:

                setHomeButton(true);
                passField.setVisibility(View.GONE);
                description.setText(getString(type.description, sendOn));
                additionalBtn.setText(getString(R.string.resend_code, String.format(" %d с", DELAY_IN_SECONDS)));
                additionalBtn.setEnabled(false);

                prepareSubscription(subscription);

                subscription = Observable
                        .interval(ONE_SECOND_MILLIS, TimeUnit.MILLISECONDS)
                        .take(DELAY_IN_SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {
                                additionalBtn.setEnabled(true);
                                additionalBtn.setText(getString(R.string.resend_code, ""));
                            }

                            @Override
                            public void onError(Throwable e) {
                                additionalBtn.setEnabled(true);
                            }

                            @Override
                            public void onNext(Long i) {
                                additionalBtn.setText(getString(R.string.resend_code, String.format(" %d с", DELAY_IN_SECONDS - 1 - i)));
                                additionalBtn.setEnabled(false);
                            }
                        });
                break;

            case REGULAR_PASS_FRAGMENT:
                setHomeButton(true);
                description.setText(type.description);
                passField.setVisibility(View.VISIBLE);
                passField.setHint(type.fieldHints[1]);
                break;
        }
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        checkMainBtnState();
    }

    @Override
    public void afterTextChanged(Editable s) {
        checkMainBtnState();
    }
}
