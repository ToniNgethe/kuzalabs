package ke.co.kuzalabs.usermanagementportal.ui.base;

import android.support.v7.app.AppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ke.co.kuzalabs.usermanagementportal.utils.DialogUtills;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:26
 */
public class BaseActivity extends AppCompatActivity {
    private SweetAlertDialog progress;
    /**
     * display progress ba
     *
     * @param display
     * @param message
     */
    public void displayProgressDialog(boolean display, String message) {
        if (display) {
            progress = DialogUtills.showProgress(this, message);
            progress.show();
        } else
            progress.dismissWithAnimation();
    }

    /**
     * display error message
     * @param message
     */
    public void onError(String message) {
        DialogUtills.showError(this, message);
    }
}
