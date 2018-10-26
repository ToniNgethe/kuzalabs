package ke.co.kuzalabs.usermanagementportal.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ke.co.kuzalabs.usermanagementportal.di.modules.activities_modules.DashboardModule;
import ke.co.kuzalabs.usermanagementportal.di.modules.activities_modules.LoginModule;
import ke.co.kuzalabs.usermanagementportal.di.modules.activities_modules.RegisterModule;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.login.LoginActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.register.RegisterActivity;

/**
 * Created by ngethe on 25/10/2018
 * Day : 25
 * Time : 20:51
 */

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();


    @ContributesAndroidInjector(modules = RegisterModule.class)
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = DashboardModule.class)
    abstract DashboardActivity bindDashboardActivity();
}
