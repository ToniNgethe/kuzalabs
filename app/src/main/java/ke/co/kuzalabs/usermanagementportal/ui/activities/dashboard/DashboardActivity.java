package ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.AddUserRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.AddUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.SingleUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import ke.co.kuzalabs.usermanagementportal.ui.base.BaseActivity;
import ke.co.kuzalabs.usermanagementportal.ui.fragments.ProfileFragment;
import ke.co.kuzalabs.usermanagementportal.ui.fragments.HomeFragment;
import ke.co.kuzalabs.usermanagementportal.ui.fragments.UserDialogFragment;

public class DashboardActivity extends BaseActivity implements DashboardContract.View {


    private static int FRAGMENT_INDEX = 0;
    private static String CURRENT_TAG = "frag tag";
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.container)
    ConstraintLayout container;

    @Inject
    DashboardContract.Presenter presenter;

    private DashboardContract.HomeFragmentListener homeFragmentListener;
    private DashboardContract.ProfileListener profileListener;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:

                /* if already selected */
                if (FRAGMENT_INDEX == 0) {
                    return true;
                }

                presenter.fetchUsers();
                /* home fragment */
                FRAGMENT_INDEX = 0;
                replaceFragment();

                return true;
            case R.id.navigation_dashboard:

                /* if already selected */
                if (FRAGMENT_INDEX == 1) {
                    return true;
                }

                /* home fragment */
                FRAGMENT_INDEX = 1;
                replaceFragment();

                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        presenter.fetchUsers();

        /* set up toolbar */
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle("");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /* home fragment default */
        FRAGMENT_INDEX = 0;
        replaceFragment();
    }


    /**
     * @return selected fragment
     */
    private Fragment getHomeFragment() {
        switch (FRAGMENT_INDEX) {
            case 0:
                CURRENT_TAG = "HomeFragment";
                return HomeFragment.newInstance();
            case 1:
                CURRENT_TAG = "ProfileFragment";
                return ProfileFragment.newInstance();
            default:
                CURRENT_TAG = "HomeFragment";
                return HomeFragment.newInstance();
        }
    }

    /**
     * replace fragment with selected one
     */
    public void replaceFragment() {
        Runnable mPendingRunnable = () -> {
            // update the main content by replacing fragments
            Fragment fragment = getHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame_layout, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
        };

        // If mPendingRunnable is not null, then add to the message queue
        new Handler().post(mPendingRunnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f instanceof HomeFragment) {
                f.onActivityResult(requestCode, resultCode, data);
            } else if (f instanceof UserDialogFragment) {
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    /**
     * called from home fragment
     *
     * @param homeFragmentListener
     */
    public void initHomeFragmentListener(DashboardContract.HomeFragmentListener homeFragmentListener) {
        this.homeFragmentListener = homeFragmentListener;
    }

    @Override
    public void onUsersSuccessfullyFecthed(UsersResponse usersResponse) {
        homeFragmentListener.onUsersFetched(usersResponse);
    }

    @Override
    public void onUsersNotFound() {
        homeFragmentListener.onUsersNotFoud();
    }

    @Override
    public void onUserAddedSuccessfully(AddUserResponse addUserResponse, String image) {
        homeFragmentListener.addNewUser(addUserResponse, image);
    }

    @Override
    public void onUserEditedSuccessfully(UsersResponse.DataBean editedUser) {
        homeFragmentListener.onUserEditted(editedUser);
    }


    @Override
    public void errorFetchingUsers(String message) {
        super.onError(message);
    }

    @Override
    public void onUserProfileFetched(SingleUserResponse response) {
        profileListener.onDataFectched(response);
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void destroyPresenterHomeFragment() {
        presenter.onDestroy();
    }

    /**
     * add new user
     *
     * @param addUserRequest addUserRequest
     * @param s              String
     */
    public void addUser(AddUserRequest addUserRequest, String s) {
        presenter.addUser(addUserRequest, s);
    }

    /**
     * edit selected user
     *
     * @param addUserRequest AddUserRequest
     * @param s
     */
    public void editUser(AddUserRequest addUserRequest, String s) {
        presenter.editUser(addUserRequest, s);
    }

    /**
     * attach listener to Profile fragment
     *
     * @param profileListener
     */
    public void initProfileListener(DashboardContract.ProfileListener profileListener) {
        this.profileListener = profileListener;
    }

    /**
     * called from profile fragment to inti call to get user data
     */
    public void fetchProfile() {
        presenter.fetchUserProfile();
    }
}
