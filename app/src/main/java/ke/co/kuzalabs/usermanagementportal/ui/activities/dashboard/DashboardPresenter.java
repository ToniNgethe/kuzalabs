package ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.AddUserRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import ke.co.kuzalabs.usermanagementportal.data.services.UserService;
import ke.co.kuzalabs.usermanagementportal.utils.ConsoleUtills;
import ke.co.kuzalabs.usermanagementportal.utils.ErrorUtils;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 20:54
 */
public class DashboardPresenter implements DashboardContract.Presenter {
    private CompositeDisposable compositeDisposable;
    private UserService userService;
    private DashboardContract.View view;

    @Inject
    public DashboardPresenter(UserService userService, DashboardContract.View view) {
        this.userService = userService;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void fetchUsers() {

        /* progress dialog */
        view.displayProgressDialog(true, "Fetching users");

        Disposable disposable = userService.fetchUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        usersResponse -> {

                            /* check if any users exist */
                            if (usersResponse.getData().size() > 0)
                                view.onUsersSuccessfullyFecthed(usersResponse);
                            else
                                view.onUsersNotFound();

                        },
                        throwable -> {
                            /* on error */
                            view.displayProgressDialog(false, "");
                            view.errorFetchingUsers(ErrorUtils.parseOnFailure(throwable));
                        },
                        () -> view.displayProgressDialog(false, "")
                );

        compositeDisposable.add(disposable);
    }

    @Override
    public void addUser(AddUserRequest addUserRequest, String imageUri) {

        view.displayProgressDialog(true, "Adding user");

        Disposable disposable = userService.addUser(addUserRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        addUserResponse -> view.onUserAddedSuccessfully(addUserResponse, imageUri),
                        throwable -> {
                            view.displayProgressDialog(false, "");
                            view.onError(ErrorUtils.parseOnFailure(throwable));
                        },
                        () -> view.displayProgressDialog(false, "")
                );
        compositeDisposable.add(disposable);

    }

    @Override
    public void editUser(AddUserRequest addUserRequest, String imageUri) {

        view.displayProgressDialog(true, "Editing user");

        Disposable disposable = userService.editUser(addUserRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        addUserResponse -> {

                            UsersResponse.DataBean dataBean = new UsersResponse.DataBean();
                            dataBean.setAvatar(imageUri);
                            dataBean.setRole(addUserResponse.getJob());
                            ConsoleUtills.print("id ->" + addUserRequest.getId());
                            dataBean.setId(Integer.parseInt(addUserRequest.getId()));
                            dataBean.setFirst_name(addUserResponse.getName());

                            view.onUserEditedSuccessfully(dataBean);
                        },
                        throwable -> {
                            view.displayProgressDialog(false, "");
                            view.onError(ErrorUtils.parseOnFailure(throwable));
                        },
                        () -> view.displayProgressDialog(false, "")
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void fetchUserProfile() {
        view.displayProgressDialog(true, "Fetching profile");

        Disposable disposable = userService.getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> view.onUserProfileFetched(response),
                        throwable -> {
                            view.displayProgressDialog(false, "");
                            view.onError(ErrorUtils.parseOnFailure(throwable));
                        },
                        () -> view.displayProgressDialog(false, "")
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
