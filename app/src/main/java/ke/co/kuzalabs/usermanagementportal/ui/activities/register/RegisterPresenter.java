package ke.co.kuzalabs.usermanagementportal.ui.activities.register;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.RegisterRequest;
import ke.co.kuzalabs.usermanagementportal.data.services.AuthService;
import ke.co.kuzalabs.usermanagementportal.utils.ErrorUtils;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 22:47
 */
public class RegisterPresenter implements RegisterContract.Presenter {

    private CompositeDisposable compositeDisposable;
    private RegisterContract.View view;
    private AuthService authService;


    @Inject
    public RegisterPresenter(RegisterContract.View view, AuthService authService) {
        this.view = view;
        this.authService = authService;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void registerUser(String username, String email, String password) {
        /* progress */
        view.displayProgressDialog(true, "Registering user...");

        /* create request */
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);

        Disposable disposable = authService.registerUser(registerRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        registerResponse -> view.onSuccessFullyRegistration(),
                        throwable -> {
                            /* error encountered */
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
        /* clear compositable on activity destroy */
        compositeDisposable.dispose();
    }
}
