package ke.co.kuzalabs.usermanagementportal.di.modules.activities_modules;

import dagger.Module;
import dagger.Provides;
import ke.co.kuzalabs.usermanagementportal.data.services.AuthService;
import ke.co.kuzalabs.usermanagementportal.ui.activities.login.LoginActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.login.LoginContract;
import ke.co.kuzalabs.usermanagementportal.ui.activities.login.LoginPresenter;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:43
 * <p>
 * Provide dependencies to be used in login presenter and login activity
 */
@Module
public class LoginModule {

    @Provides
    LoginContract.View provideView(LoginActivity loginActivity) {
        return loginActivity;
    }

    @Provides
    LoginContract.Presenter providePresenter(LoginContract.View view, AuthService authService) {
        return new LoginPresenter(authService, view);
    }
}
