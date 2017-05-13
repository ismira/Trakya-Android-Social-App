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
    private static final String EXTRA_OGRENCI_ID = OgrenciProfilActivity.class.getName(); // TODO: 27.2.2017
    @Override
    protected Fragment createFragment() {
        return OgrencininHocalariFragment.newInstance();
    }

    public static Intent newIntent(Context context, Long ogrenciID) {
        Intent intent = new Intent(context, OgrencininHocalariActivity.class);
        intent.putExtra(EXTRA_OGRENCI_ID, ogrenciID);
        return intent;
    }
}
