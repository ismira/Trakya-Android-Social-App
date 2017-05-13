package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.GruplarFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.OgrenciKayitFragment;

/**
 * Created by Deno on 8.05.2017.
 */

public class OgrenciKayitActivty extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return OgrenciKayitFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, OgrenciKayitActivty.class);
        return intent;
    }
}
