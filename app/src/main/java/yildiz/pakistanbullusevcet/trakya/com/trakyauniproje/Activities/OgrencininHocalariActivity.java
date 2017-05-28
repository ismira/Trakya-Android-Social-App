package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.GruplarFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.OgrencininHocalariFragment;

/**
 * Created by Deno on 8.05.2017.
 */

public class OgrencininHocalariActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return OgrencininHocalariFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, OgrencininHocalariActivity.class);
        return intent;
    }
}
