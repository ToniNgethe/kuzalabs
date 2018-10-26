package ke.co.kuzalabs.usermanagementportal.di.modules;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ke.co.kuzalabs.usermanagementportal.data.Config;
import ke.co.kuzalabs.usermanagementportal.data.api.HeaderInterceptor;
import ke.co.kuzalabs.usermanagementportal.data.services.AuthService;
import ke.co.kuzalabs.usermanagementportal.data.services.UserService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ngethe on 25/10/2018
 * Time : 20:52
 */
@Module
public class NetworkModule {
    private static final String BASE_URL = "base_url";
    private static Retrofit retrofit;

    @Provides
    @Named(BASE_URL)
    String provideBaseUrl() {
        return Config.API_HOST;
    }

    @Provides
    @Singleton
    HeaderInterceptor provideHeaderInterceptor() {
        return new HeaderInterceptor();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(HeaderInterceptor headerInterceptor,
                                   HttpLoggingInterceptor httpInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(headerInterceptor)
                .addInterceptor(httpInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory, OkHttpClient client) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(callAdapterFactory)
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    @Provides
    @Singleton
    Gson providesGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    UserService providesUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

}
