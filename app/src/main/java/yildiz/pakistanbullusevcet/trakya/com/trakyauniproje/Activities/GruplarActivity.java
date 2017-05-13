package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.GruplarFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.LoginFragment;

/**
 * Created by Deno on 16.04.2017.
 */

public class GruplarActivity extends SingleFragmentActivity {
    private static final String EXTRA_OGRENCI_ID = OgrenciProfilActivity.class.getName(); // TODO: 27.2.2017
    @Override
    protected Fragment createFragment() {
        return GruplarFragment.newInstance();
    }

    public static Intent newIntent(Context context, Long ogrenciID) {
        Intent intent = new Intent(context, GruplarActivity.class);
        intent.putExtra(EXTRA_OGRENCI_ID, ogrenciID);
        return intent;
    }
}
