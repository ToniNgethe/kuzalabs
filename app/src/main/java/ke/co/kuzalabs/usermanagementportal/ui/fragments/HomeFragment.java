package ke.co.kuzalabs.usermanagementportal.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.AddUserResponse;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardContract;
import ke.co.kuzalabs.usermanagementportal.ui.adapters.UsersAdapter;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 06:17
 */
public class HomeFragment extends Fragment implements DashboardContract.HomeFragmentListener {

    @BindView(R.id.progressBar_home_fragment)
    ProgressBar progressBarHomeFragment;
    @BindView(R.id.textView_home_error)
    TextView textViewHomeError;
    @BindView(R.id.rv_home_fragment)
    RecyclerView rvHomeFragment;
    Unbinder unbinder;


    private UsersAdapter usersAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        /* necessary to inflate this frag menu */
        setHasOptionsMenu(true);
        /* fetch users */
        /* init adapter */
        usersAdapter = new UsersAdapter();

        progressBarHomeFragment.setVisibility(View.GONE);
        textViewHomeError.setVisibility(View.GONE);
        rvHomeFragment.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* init listener */
        ((DashboardActivity) Objects.requireNonNull(getActivity())).initHomeFragmentListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        ((DashboardActivity) Objects.requireNonNull(getActivity())).destroyPresenterHomeFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_fragment_add, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {

            /* open add user dialog */
            DialogFragment dialogFragment = UserDialogFragment.newInstance(UserDialogFragment.ADD_USER, null);
            dialogFragment.show(getChildFragmentManager(), "add_user_dialog");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment d = getChildFragmentManager().findFragmentByTag("add_user_dialog");
        if (d instanceof UserDialogFragment) {
            d.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onUsersFetched(UsersResponse usersResponse) {
        progressBarHomeFragment.setVisibility(View.GONE);
        textViewHomeError.setVisibility(View.GONE);
        rvHomeFragment.setVisibility(View.VISIBLE);

        /* linear manager */
        rvHomeFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        /* set data to adapter */
        usersAdapter.setData(usersResponse.getData());

        rvHomeFragment.setAdapter(usersAdapter);
    }

    @Override
    public void addNewUser(AddUserResponse addUserResponse, String imagee) {

        UsersResponse.DataBean dataBean = new UsersResponse.DataBean();
        dataBean.setId(Integer.parseInt(addUserResponse.getId()));
        dataBean.setRole(addUserResponse.getJob());
        dataBean.setLast_name(addUserResponse.getName());
        dataBean.setFirst_name(addUserResponse.getName());
        dataBean.setAvatar(imagee);

        /* update users liist */
        usersAdapter.addUser(dataBean);


    }

    @Override
    public void onUserEditted(UsersResponse.DataBean addUserResponse) {
        usersAdapter.updateUser(addUserResponse);
    }

    @Override
    public void onUsersNotFoud() {
        progressBarHomeFragment.setVisibility(View.GONE);
        textViewHomeError.setVisibility(View.VISIBLE);
        rvHomeFragment.setVisibility(View.GONE);

        textViewHomeError.setText("Users not found!");
    }

}
