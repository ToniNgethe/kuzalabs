package ke.co.kuzalabs.usermanagementportal.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import ke.co.kuzalabs.usermanagementportal.App;
import ke.co.kuzalabs.usermanagementportal.di.modules.ActivityBuilderModule;
import ke.co.kuzalabs.usermanagementportal.di.modules.AppModules;

@Singleton
@Component(modules = {
        /*internal dagger module*/
        AndroidSupportInjectionModule.class,
        /* this module provide application scoped resources, retrofit, database....*/
        AppModules.class,
        /* we map all our activities here. And Dagger know our activities in compile time.*/
        ActivityBuilderModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
