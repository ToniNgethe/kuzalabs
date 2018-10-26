package ke.co.kuzalabs.usermanagementportal.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.activities.login.LoginActivity;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 06:18
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.imageView_profile)
    ImageView imageViewProfile;
    @BindView(R.id.textVie_names)
    TextView textVieNames;
    Unbinder unbinder;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        /* fetch profile when created*/
        ((DashboardActivity) getActivity()).fetchProfile();

        ((DashboardActivity) getActivity()).initProfileListener(singleUserResponse -> {
            /* set data */
            Glide.with(getActivity())
                    .load(singleUserResponse.getData().getAvatar())
                    .into(imageViewProfile);

            textVieNames.setText(String.format("%s %s", singleUserResponse.getData().getFirst_name(), singleUserResponse.getData().getLast_name()));
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exit, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
