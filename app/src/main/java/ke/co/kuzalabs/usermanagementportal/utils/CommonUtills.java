package ke.co.kuzalabs.usermanagementportal.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by toni on 4/13/18.
 * day : Friday
 * time : 11:10 AM
 */

public class CommonUtills {
    /**
     * This method sets back arrow button and sets up toolbar
     * @param toolbar Toolbar
     * @param title String
     * @param ctx Activity
     */
    public static void backHomeToolbar(Toolbar toolbar, String title, Context ctx) {

        ((AppCompatActivity) ctx).setSupportActionBar(toolbar);
        ((AppCompatActivity) ctx).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) ctx).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
