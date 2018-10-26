package ke.co.kuzalabs.usermanagementportal.ui.activities.login;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.LoginRequest;
import ke.co.kuzalabs.usermanagementportal.data.services.AuthService;
import ke.co.kuzalabs.usermanagementportal.utils.ErrorUtils;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:19
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private AuthService authService;
    private CompositeDisposable compositeDisposable;

    @Inject
    public LoginPresenter(AuthService authService, LoginContract.View view) {
        this.authService = authService;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loginUser(String email, String password) {

        /* display progress bar */
        view.displayProgressDialog(true, "Authenticating user...");

        /* request */
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        /* call */
        Disposable disposable = authService.loginUser(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            /* if success - save token*/
                            view.onLoginSuccessfully();
                        },
                        throwable -> {
                            /* an error was encountered */
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
        compositeDisposable.dispose();
    }
}
