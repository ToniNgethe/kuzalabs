package ke.co.kuzalabs.usermanagementportal.utils;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ke.co.kuzalabs.usermanagementportal.R;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:20
 *
 *
 *  This class contains all Alerts and pop ups u need
 */
public class DialogUtills {
    /*  display error  */
    public static void showError(Context ctx, String message) {
        new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText(message)
                .show();
    }

    /**
     * Error Dialog
     *
     * @param ctx     Context
     * @param message String
     * @return SweetAlertDialog
     */
    public static SweetAlertDialog showErrorWithButton(Context ctx, String message) {
        return new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText(message);
    }

    /**
     * Displays success dialog with custom message
     *
     * @param ctx     Activity/Fragment
     * @param message String
     */
    public static void showSuccess(Context ctx, String message) {
        new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Success")
                .setContentText(message)
                .show();
    }

    /**
     * Shows user passed information
     * contains the app logo.. change drawable location
     *
     * @param ctx     Activity/Fragment
     * @param title   String
     * @param message String
     * @return SweetAlertDialog
     */
    public static SweetAlertDialog showInformation(Context ctx, String title, String message) {

        return new SweetAlertDialog(ctx, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setCustomImage(R.mipmap.ic_launcher);
    }

    /**
     * returns a warning SweetAlert dialog
     *
     * @param ctx   Fragment/Activity
     * @param title String
     * @param msg   String
     * @return SweetAlertDialog
     */
    public static SweetAlertDialog showWarning(Context ctx, String title, String msg) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText(title)
                .setContentText(msg);
        return sweetAlertDialog;
    }

    /**
     * returns success SweetAlert dialog
     *
     * @param ctx Fragment/Activity
     * @param msg String
     * @return SweetAlertDialog
     */
    public static SweetAlertDialog successWithButton(Context ctx, String msg) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitleText("Success")
                .setContentText(msg);

        return sweetAlertDialog;
    }

    /**
     * display progress dialog
     *
     * @param ctx     Fragment/Activity
     * @param message String
     * @return SweetAlert
     */
    public static SweetAlertDialog showProgress(Context ctx, String message) {

        SweetAlertDialog pDialog = new SweetAlertDialog(ctx, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(R.color.colorAccent);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setTitleText(ctx.getString(R.string.loading_text));
        pDialog.setContentText(message);
        return pDialog;

    }
}
