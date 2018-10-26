package ke.co.kuzalabs.usermanagementportal.ui.activities.login;

import ke.co.kuzalabs.usermanagementportal.ui.base.BasePresenter;
import ke.co.kuzalabs.usermanagementportal.ui.base.BaseView;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:19
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {
        /**
         * invoked when authentication was a success
         */
        void onLoginSuccessfully();
    }


    interface Presenter extends BasePresenter {
        /**
         * Authenticate user
         *
         * @param email    String
         * @param password String
         */
        void loginUser(String email, String password);
    }
}
