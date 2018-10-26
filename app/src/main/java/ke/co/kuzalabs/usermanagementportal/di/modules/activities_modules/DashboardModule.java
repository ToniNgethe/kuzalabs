package ke.co.kuzalabs.usermanagementportal.di.modules.activities_modules;

import dagger.Module;
import dagger.Provides;
import ke.co.kuzalabs.usermanagementportal.data.services.UserService;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardContract;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardPresenter;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 06:29
 */
@Module
public  class DashboardModule {

    @Provides
    DashboardContract.View providesView(DashboardActivity dashboardActivity) {
        return dashboardActivity;
    }

    @Provides
    DashboardContract.Presenter providesPresenter(DashboardContract.View view, UserService userService) {
        return new DashboardPresenter(userService, view);
    }
}
