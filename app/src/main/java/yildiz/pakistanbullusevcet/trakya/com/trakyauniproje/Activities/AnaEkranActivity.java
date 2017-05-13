package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.AnaEkranFragment;

/**
 * Created by NFL on 12.05.2017.
 */

public class AnaEkranActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return AnaEkranFragment.newInstance();
    }
}
