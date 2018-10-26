package ke.co.kuzalabs.usermanagementportal.di.modules.activities_modules;

import dagger.Module;
import dagger.Provides;
import ke.co.kuzalabs.usermanagementportal.data.services.AuthService;
import ke.co.kuzalabs.usermanagementportal.ui.activities.register.RegisterActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.register.RegisterContract;
import ke.co.kuzalabs.usermanagementportal.ui.activities.register.RegisterPresenter;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 05:50
 */

@Module
public class RegisterModule {

    @Provides
    RegisterContract.View providesView(RegisterActivity registerActivity) {
        return registerActivity;
    }


    @Provides
    RegisterContract.Presenter providesPresenter(RegisterContract.View view, AuthService authService) {
        return new RegisterPresenter(view, authService);
    }
}
