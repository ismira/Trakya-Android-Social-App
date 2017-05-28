package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.MesajListesiFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.MesajlarFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.NotlarFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.OgrenciPanelFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments.OgrenciProfilFragment;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

public class TabbedActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, TabbedActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);


        mViewPager = (ViewPager) findViewById(R.id.activity_tabbed_view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabbed_activity_tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(NotlarFragment.newInstance(),"Duyurular");
        adapter.addFragment(OgrenciProfilFragment.newInstance(Tools.getID()), "Profil");
        adapter.addFragment(MesajListesiFragment.newInstance(),"Mesajlar");
        adapter.addFragment(OgrenciPanelFragment.newInstance(),"Panel");

        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item_menu, menu);
        return true;
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mListFragment = new ArrayList<>();
        private List<String> mListTitleFragment = new ArrayList<>();

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mListTitleFragment.add(title);
            mListFragment.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mListFragment.get(position);
        }

        @Override
        public int getCount() {
            return mListFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mListTitleFragment.get(position);
        }
    }

}
