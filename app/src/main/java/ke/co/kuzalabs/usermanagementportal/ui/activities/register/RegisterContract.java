package ke.co.kuzalabs.usermanagementportal.ui.activities.register;

import ke.co.kuzalabs.usermanagementportal.ui.base.BasePresenter;
import ke.co.kuzalabs.usermanagementportal.ui.base.BaseView;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 22:47
 */
public class RegisterContract {

    public interface View extends BaseView<Presenter> {
        /**
         * method called when registration was a success
         */
        void onSuccessFullyRegistration();
    }

    public interface Presenter extends BasePresenter {

        /**
         * register user
         *
         * @param username String
         * @param email    String
         * @param password String
         */
        void registerUser(String username, String email, String password);

    }
}
