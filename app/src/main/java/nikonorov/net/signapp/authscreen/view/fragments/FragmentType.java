package nikonorov.net.signapp.authscreen.view.fragments;

import nikonorov.net.signapp.R;

/**
 * Created by vitaly on 27.01.17.
 */

public enum FragmentType {
    ONE_PASS_FRAGMENT(
            0,
            R.string.entering,
            R.string.login_description_title,
            new int[]{R.string.email},
            R.string.get_code,
            R.string.pass_entering,
            R.string.login_tip),

    ENTER_ONE_PASS_FRAGMENT(
            1,
            R.string.entering,
            R.string.login_description_title,
            new int[] {R.string.code},
            R.string.enter,
            R.string.resend_code,
            R.string.empty),

    REGULAR_PASS_FRAGMENT(
            2,
            R.string.entering,
            R.string.login_description_title,
            new int[] {R.string.email, R.string.pass},
            R.string.enter,
            R.string.without_pass_entering,
            R.string.login_tip);

    public final int id;
    public final int title;
    public final int description;
    public final int[] fieldHints;
    public final int mainBtnCaption;
    public final int additionalBtnCaption;
    public final int tip;


    FragmentType(int id, int title, int description, int[] fieldHints, int mainBtnCaption, int additionalBtnCaption, int tip) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.fieldHints = fieldHints;
        this.mainBtnCaption = mainBtnCaption;
        this.additionalBtnCaption = additionalBtnCaption;
        this.tip = tip;
    }
}
