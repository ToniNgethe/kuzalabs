package ke.co.kuzalabs.usermanagementportal.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ngethe on 25/10/2018
 * Day : 25
 * Time : 20:50
 */

@Module(includes = {NetworkModule.class})
public class AppModules {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

}
