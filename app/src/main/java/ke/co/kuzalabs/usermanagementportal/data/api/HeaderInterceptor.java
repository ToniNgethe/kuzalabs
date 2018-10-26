package ke.co.kuzalabs.usermanagementportal.data.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by toni on 3/29/18.
 * day : Thursday
 * time : 9:42 AM
 */

public class HeaderInterceptor implements Interceptor {
    private static final String ACCEPT_HEADER = "Accept";
    private static final String JSON_TYPE = "application/json";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder().addHeader(ACCEPT_HEADER, JSON_TYPE).build();
        return chain.proceed(request);
    }
}
