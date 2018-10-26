package ke.co.kuzalabs.usermanagementportal.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.register.RegisterActivity;
import ke.co.kuzalabs.usermanagementportal.ui.base.BaseActivity;
import ke.co.kuzalabs.usermanagementportal.utils.TopAlertUtils;
import ke.co.kuzalabs.usermanagementportal.utils.ValidationUtills;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.editText_login_password)
    EditText editTextLoginPassword;
    @BindView(R.id.editText_login_email)
    EditText editTextLoginEmail;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        editTextLoginEmail.requestFocus();
    }

    @Override
    public void onLoginSuccessfully() {
        TopAlertUtils.success(this, "Welcome");
        new Handler().postDelayed(() -> {

            /* launch dashboard */
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }, 1000);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick({R.id.textView_login_forgotpass, R.id.button_login, R.id.textView_login_create_acc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textView_login_forgotpass:
                break;
            case R.id.button_login:

                /* validate if fields are empty */
                if (!ValidationUtills.validate(new EditText[]{editTextLoginEmail, editTextLoginPassword})) {
                    return;
                }

                /* validate if email is valid */
                if (!ValidationUtills.isValidEmail(editTextLoginEmail.getText().toString().trim())) {
                    editTextLoginEmail.setError("Invalid email format");
                    return;
                }

                /* password should be 8 letters */
                if (editTextLoginPassword.getText().toString().trim().length() < 8) {
                    editTextLoginPassword.setError("Password should of length 8 or more");
                    return;
                }

                /*authenticate user */
                presenter.loginUser(editTextLoginEmail.getText().toString(), editTextLoginPassword.getText().toString());

                break;
            case R.id.textView_login_create_acc:

                /* redirect to register */
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
