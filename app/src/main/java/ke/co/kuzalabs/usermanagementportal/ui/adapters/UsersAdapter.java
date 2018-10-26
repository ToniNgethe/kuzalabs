package ke.co.kuzalabs.usermanagementportal.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.kuzalabs.usermanagementportal.R;
import ke.co.kuzalabs.usermanagementportal.data.models.responses.UsersResponse;
import ke.co.kuzalabs.usermanagementportal.ui.activities.dashboard.DashboardActivity;
import ke.co.kuzalabs.usermanagementportal.ui.fragments.UserDialogFragment;
import ke.co.kuzalabs.usermanagementportal.ui.views.CircleImageView;
import ke.co.kuzalabs.usermanagementportal.utils.ConsoleUtills;

/**
 * Created by ngethe on 26/10/2018
 * Day  : Friday
 * Time : 07:02
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersVH> {

    private List<UsersResponse.DataBean> data = new ArrayList<>();
    private Context context;

    public UsersAdapter() {
    }


    /**
     * set data to adapter
     *
     * @param data List<UsersResponse.DataBean>
     */
    public void setData(List<UsersResponse.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * add new user to currently existing ones
     *
     * @param dataBean UsersResponse.DataBean
     */
    public void addUser(UsersResponse.DataBean dataBean) {
        data.add(dataBean);
        notifyDataSetChanged();
    }

    /**
     * update modified object
     *
     * @param dataBean
     */
    public void updateUser(UsersResponse.DataBean dataBean) {
        /*get position*/
        ConsoleUtills.print("Modified user : " + new Gson().toJson(dataBean));
        int postion = 0;
        /* loop to get the positon of modified object using its id */
        for (UsersResponse.DataBean d : data) {
            if (d.getId() == dataBean.getId()) {
                postion = data.indexOf(d);

                /* set new data */
                data.set(postion, dataBean);

                break;
            }
        }


        notifyItemChanged(postion + 1);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new UsersVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_users, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersVH usersVH, int i) {
        UsersResponse.DataBean dataBean = data.get(i);

        /* set data to view */
        usersVH.textViewUserName.setText(String.format("%s %s", dataBean.getFirst_name(), dataBean.getLast_name()));
        if (dataBean.getRole() == null)
            usersVH.textViewUserRole.setText("Not Set");
        else
            usersVH.textViewUserRole.setText(dataBean.getRole());

        Glide.with(context)
                .load(dataBean.getAvatar())
                .into(usersVH.imageViewProfile);

        usersVH.imageButtonUserRole.setOnClickListener(view -> {

            //creating a popup menu
            PopupMenu popup = new PopupMenu(context, usersVH.imageButtonUserRole);
            //inflating menu from xml resource
            popup.inflate(R.menu.users_menu);
            //adding click listener
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.user_delete:

                        /* remove user from list */
                        data.remove(dataBean);
                        notifyDataSetChanged();

                        return true;

                    case R.id.user_edit:

                        DialogFragment dialogFragment = UserDialogFragment.newInstance(UserDialogFragment.EDIT_USER, dataBean);
                        dialogFragment.show(((DashboardActivity) context).getSupportFragmentManager(), "add_user_dialog");

                        return true;
                    default:
                        return false;
                }
            });
            //displaying the popup
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class UsersVH extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_profile)
        CircleImageView imageViewProfile;
        @BindView(R.id.textView_user_name)
        TextView textViewUserName;

        @BindView(R.id.textView_user_role)
        TextView textViewUserRole;
        @BindView(R.id.imageButton_user_role)
        ImageButton imageButtonUserRole;

        public UsersVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
