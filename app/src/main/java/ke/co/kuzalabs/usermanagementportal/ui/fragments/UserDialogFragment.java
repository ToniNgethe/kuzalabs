package ke.co.kuzalabs.usermanagementportal.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.data.models.requests.AddUserRequest;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.views.CircleImageView;
import ke.co.kuzalabs.usermanagementportal.utils.ConsoleUtills;
import ke.co.kuzalabs.usermanagementportal.utils.ValidationUtills;
import ke.co.kuzalabs.usermanagementportal.utils.image_picker_utils.ImagePicker;


/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 19:20
 */
public class UserDialogFragment extends DialogFragment {

    /*CASES*/
    public static String ADD_USER = "Add user";
    public static String EDIT_USER = "Edit user";

    @BindView(R.id.imageView_adduser)
    CircleImageView imageViewAdduser;
    @BindView(R.id.et_adduser_names)
    EditText etAdduserNames;
    @BindView(R.id.et_adduser_role)
    EditText etAdduserRole;
    @BindView(R.id.textView_action)
    TextView action;

    Unbinder unbinder;

    private ImagePicker imagePicker;
    private String uri;
    private File file;
    private String title;
    private UsersResponse.DataBean dataBean;

    public static UserDialogFragment newInstance(String action, UsersResponse.DataBean dataBean) {
        Bundle args = new Bundle();
        args.putString("action", action);
        args.putSerializable("user", dataBean);
        UserDialogFragment fragment = new UserDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* get action */
        title = getArguments().getString("action");

        assert title != null;
        if (title.equals(EDIT_USER)) {
            dataBean = (UsersResponse.DataBean) getArguments().getSerializable("user");

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_add_user, container, false);
        unbinder = ButterKnife.bind(this, view);

        action.setText(title);

        if (title.equals(EDIT_USER)) {
            /* edit method....so display user data */

            Glide.with(getActivity())
                    .load(dataBean.getAvatar())
                    .into(imageViewAdduser);
            uri = dataBean.getAvatar();
            etAdduserNames.setText(String.format("%s %s", dataBean.getFirst_name(), dataBean.getLast_name()));
            etAdduserRole.setText(etAdduserRole.getText().toString());

        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imageButton_addUser, R.id.imageView_adduser, R.id.button_adduser})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButton_addUser:

                /* dismiss dialog */
                dismiss();

                break;
            case R.id.imageView_adduser:

                /* open image picker */
                /* pick image */
                imagePicker = new ImagePicker(getActivity(),
                        this,
                        imageUri -> {
                            uri = imageUri.toString();
                            /* get file */
                            file = imagePicker.getImageFile();

                            imageViewAdduser.setImageURI(imageUri);

                        }).setWithImageCrop(1, 1);

                /* initiate */
                imagePicker.choosePicture(true);

                break;
            case R.id.button_adduser:


                /* validate fields */
                if (title.equals(ADD_USER))
                    if (uri == null) {
                        Toast.makeText(getActivity(), "Select Profile image", Toast.LENGTH_SHORT).show();
                        return;
                    }

                if (!ValidationUtills.validate(new EditText[]{etAdduserNames, etAdduserRole})) {
                    return;
                }

                AddUserRequest addUserRequest = new AddUserRequest();
                addUserRequest.setJob(etAdduserRole.getText().toString());
                addUserRequest.setName(etAdduserNames.getText().toString());

                if (title.equals(EDIT_USER)) {
                    addUserRequest.setId(String.valueOf(dataBean.getId()));
                }

                /* check if its an edit function */
                if (title.equals(ADD_USER)) {
                    ((DashboardActivity) Objects.requireNonNull(getActivity())).addUser(addUserRequest, uri);
                } else {
                    /* edit action */
                    ((DashboardActivity) Objects.requireNonNull(getActivity())).editUser(addUserRequest, uri);
                }
                dismiss();


                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.handleActivityResult(resultCode, requestCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        imagePicker.handlePermission(requestCode, grantResults);
    }


}


