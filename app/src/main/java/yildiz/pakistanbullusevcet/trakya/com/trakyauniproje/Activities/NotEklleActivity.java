package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.NotEkleFragment;

/**
 * Created by NFL on 12.05.2017.
 */

public class NotEklleActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return NotEkleFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, NotEklleActivity.class);
        return i;
    }


}
