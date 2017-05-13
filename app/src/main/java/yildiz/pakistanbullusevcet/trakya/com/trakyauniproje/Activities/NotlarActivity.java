package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.LoginFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.NotlarFragment;

/**
 * Created by Deno on 24.04.2017.
 */

public class NotlarActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return NotlarFragment.newInstance();
    }

    public static Intent newIntent(Context context){
        Intent i = new Intent(context,NotlarActivity.class);
        return i;
    }
}
