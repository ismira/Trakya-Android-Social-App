package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.HocaKayitFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.OgrenciKayitFragment;

/**
 * Created by Deno on 8.05.2017.
 */

public class HocaKayitActivity extends SingleFragmentActivity {
    private static final String EXTRA_OGRENCI_ID = OgrenciProfilActivity.class.getName(); // TODO: 27.2.2017
    @Override
    protected Fragment createFragment() {
        return HocaKayitFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, HocaKayitActivity.class);
        return intent;
    }

}
