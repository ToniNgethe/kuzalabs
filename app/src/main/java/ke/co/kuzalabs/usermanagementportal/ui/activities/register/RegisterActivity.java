package ke.co.kuzalabs.usermanagementportal.ui.activities.register;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.ui.base.BaseActivity;
import ke.co.kuzalabs.usermanagementportal.utils.CommonUtills;
import ke.co.kuzalabs.usermanagementportal.utils.TopAlertUtils;
import ke.co.kuzalabs.usermanagementportal.utils.ValidationUtills;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editText_login_username)
    EditText editTextLoginUsername;
    @BindView(R.id.editText_register_email)
    EditText editTextRegisterEmail;
    @BindView(R.id.editText_register_pass)
    EditText editTextRegisterPass;
    @BindView(R.id.editText_register_pass_confirm)
    EditText editTextRegisterPassConfirm;

    @Inject
    RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        /* set up toolbar */
        CommonUtills.backHomeToolbar(toolbar, "", this);
    }

    @OnClick(R.id.button_register)
    public void onViewClicked() {

        /* validate fields */
        if (!ValidationUtills.validate(new EditText[]{editTextLoginUsername, editTextRegisterEmail, editTextRegisterPass, editTextRegisterPassConfirm})) {
            return;
        }


        String passs = editTextRegisterPass.getText().toString().trim();
        String passConfirm = editTextRegisterPassConfirm.getText().toString().trim();

        /*  validate passwords if they are the same*/
        if (!passs.equals(passConfirm)) {
            editTextRegisterPass.setError("Password do not match");
            editTextRegisterPassConfirm.setError("Password do not match");
            return;
        }

        String email = editTextRegisterEmail.getText().toString().trim();

        /* validate email */
        if (!ValidationUtills.isValidEmail(email)) {
            editTextRegisterEmail.setError("Invalid email format");
            return;
        }

        /* carry out registration */
        presenter.registerUser(editTextLoginUsername.getText().toString().trim(), email, passs);

    }

    @Override
    public void onSuccessFullyRegistration() {
        /* display success toast and redirect to login */
        TopAlertUtils.success(this, "Registration  was a success. Login to continue");
        new Handler().postDelayed(this::finish, 2000);

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /* dismiss if home button is clicked */
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
