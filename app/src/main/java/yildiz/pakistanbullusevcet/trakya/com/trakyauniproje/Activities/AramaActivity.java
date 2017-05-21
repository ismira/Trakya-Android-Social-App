package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.AramaFragment;

/**
 * Created by NFL on 21.05.2017.
 */

public class AramaActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return AramaFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent i = new Intent(context, AramaActivity.class);
        return i;
    }
}
