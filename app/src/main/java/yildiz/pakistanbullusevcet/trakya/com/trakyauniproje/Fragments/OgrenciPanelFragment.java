package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.NotEkleActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrencininHocalariActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;

/**
 * Created by NFL on 28.05.2017.
 */

public class OgrenciPanelFragment extends Fragment {

    private Button mNotPaylasButton;
    private Button mHocalarimiGosterButton;

    public static OgrenciPanelFragment newInstance() {

        Bundle args = new Bundle();

        OgrenciPanelFragment fragment = new OgrenciPanelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ogrenci_panel, container, false);
        init(view);
        mNotPaylasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = NotEkleActivity.newIntent(getActivity());
                startActivity(i);

            }
        });
        mHocalarimiGosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OgrencininHocalariActivity.newIntent(getActivity());
                startActivity(i);
            }
        });

        return view;
    }

    private void init(View view) {
        mNotPaylasButton = (Button) view.findViewById(R.id.fragment_ogrenci_panel_not_paylas_button);
        mHocalarimiGosterButton = (Button) view.findViewById(R.id.fragment_ogrenci_panel_hocalarimi_goster_button);
    }
}
