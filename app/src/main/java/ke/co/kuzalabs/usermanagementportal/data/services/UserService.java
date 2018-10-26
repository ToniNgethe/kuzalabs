package ke.co.kuzalabs.usermanagementportal.data.services;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.AddUserRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.AddUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.EditUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.SingleUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 20:59
 */
public interface UserService {

    @GET("users")
    Flowable<UsersResponse> fetchUsers();

    @POST("users")
    Flowable<AddUserResponse> addUser(@Body AddUserRequest addUserRequest);

    @PUT("users")
    Flowable<EditUserResponse> editUser(@Body AddUserRequest addUserRequest);

    @DELETE("users/{id}")
    Completable deleteUser(@Path("id") String id);

    @GET("users/2")
    Flowable<SingleUserResponse> getUserProfile();

}
