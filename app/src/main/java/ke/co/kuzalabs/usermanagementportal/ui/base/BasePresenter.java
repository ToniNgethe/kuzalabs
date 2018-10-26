package ke.co.kuzalabs.usermanagementportal.ui.base;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:23
 */
public interface BasePresenter {

    void start();

    /**
     * should be invoked when on on destroy in view is called
     */
    void onDestroy();
}

