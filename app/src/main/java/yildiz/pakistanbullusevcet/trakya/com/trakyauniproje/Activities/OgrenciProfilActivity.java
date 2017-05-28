package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.OgrenciProfilFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

/**
 * Created by NFL on 3.4.2017.
 */

public class OgrenciProfilActivity extends SingleFragmentActivity {
@Override
    protected Fragment createFragment() {
        return OgrenciProfilFragment.newInstance(Tools.getID());
    }



    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, OgrenciProfilActivity.class);
        return intent;
    }
}
