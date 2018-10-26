package ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard;

import ke.co.kuzalabs.usermanagementportal.data.models.requests.AddUserRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.AddUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.SingleUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import ke.co.kuzalabs.usermanagementportal.ui.base.BasePresenter;
import ke.co.kuzalabs.usermanagementportal.ui.base.BaseView;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 20:54
 */
public interface DashboardContract {
    interface View extends BaseView<Presenter> {
        /**
         * called when request was a success
         *
         * @param usersResponse UsersResponse
         */
        void onUsersSuccessfullyFecthed(UsersResponse usersResponse);

        /**
         * called when there are not users in the be
         */
        void onUsersNotFound();


        /**
         * called when user added successfully
         *
         * @param addUserResponse
         * @param image
         */
        void onUserAddedSuccessfully(AddUserResponse addUserResponse, String image);

        /**
         * called when user added successfully
         *
         * @param addUserResponse
         */
        void onUserEditedSuccessfully(UsersResponse.DataBean addUserResponse);

        void errorFetchingUsers(String message);

        /**
         * called when user info is found
         *
         * @param response SingleUserResponse
         */
        void onUserProfileFetched(SingleUserResponse response);
    }

    interface Presenter extends BasePresenter {

        /**
         * fetch users from backend
         */
        void fetchUsers();

        /**
         * add user to db
         *
         * @param addUserRequest AddUserRequest
         * @param imageUri       String
         */
        void addUser(AddUserRequest addUserRequest, String imageUri);


        /**
         * edit user to db
         *
         * @param addUserRequest AddUserRequest
         * @param imageUri       String
         */
        void editUser(AddUserRequest addUserRequest, String imageUri);


        /**
         * fetch user profile
         */
        void fetchUserProfile();
    }

    /**
     * listens to feedback from server
     */
    interface HomeFragmentListener {

        void onUsersNotFoud();

        void onUsersFetched(UsersResponse usersResponse);

        void addNewUser(AddUserResponse addUserResponse, String imagee);


        void onUserEditted(UsersResponse.DataBean addUserResponse);
    }


    /**
     * listen for data of user
     */
    interface ProfileListener {
        void onDataFectched(SingleUserResponse singleUserResponse);
    }
}
