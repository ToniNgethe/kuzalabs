package ke.co.kuzalabs.usermanagementportal.data.services;

import io.reactivex.Flowable;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.LoginRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.RegisterRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.LoginResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.RegisterResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 20:59
 */
public interface AuthService {

    @POST("login")
    Flowable<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("register")
    Flowable<RegisterResponse> registerUser(@Body RegisterRequest loginRequest);

}
