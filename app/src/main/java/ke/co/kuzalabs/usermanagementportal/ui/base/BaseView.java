package ke.co.kuzalabs.usermanagementportal.ui.base;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:23
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void onError(String errorMessage);

    void displayProgressDialog(boolean diplay, String message);

}
