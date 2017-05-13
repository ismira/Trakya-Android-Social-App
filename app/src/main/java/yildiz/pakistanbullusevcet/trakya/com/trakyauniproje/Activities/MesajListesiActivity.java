package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.MesajListesiFragment;

/**
 * Created by NFL on 12.05.2017.
 */

public class MesajListesiActivity extends SingleFragmentActivity  {

    @Override
    protected Fragment createFragment() {
        //şimdilik 5 numaralı ve hoca olan kişiye...

        return MesajListesiFragment.newInstance();
    }
    // parametre vermiyoruz zaten Tools'tan kendisi çekiyor.
    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, MesajListesiActivity.class);
        return i;
    }


}
