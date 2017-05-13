package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.GruplarActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.HocaKayitActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.LoginActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.MesajListesiActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.NotEklleActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.NotlarActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrenciKayitActivty;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrencininHocalariActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

/**
 * Created by NFL on 12.05.2017.
 */

public class AnaEkranFragment extends Fragment {

    private Button mLoginButton;
    private Button mGruplarButton;
    private Button mHocaKayitButton;
    private Button mMesajlarButton;
    private Button mOgrenciKayit;
    private Button mNotlarButton;
    private Button mOgrencininHocalari;
    private Button mNotEkleButton;

    public static AnaEkranFragment newInstance() {
        Bundle args = new Bundle();
        AnaEkranFragment fragment = new AnaEkranFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ana_sayfa, container, false);
        init(v);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = LoginActivity.newIntent(getActivity());
                startActivity(i);
            }
        });
        mGruplarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = GruplarActivity.newIntent(getActivity(), Tools.getID());
                startActivity(i);
            }
        });
        mHocaKayitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = HocaKayitActivity.newIntent(getActivity());
                startActivity(i);
            }
        });
        mMesajlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MesajListesiActivity.newIntent(getActivity());
                startActivity(i);
            }
        });
        mOgrenciKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OgrenciKayitActivty.newIntent(getActivity());
                startActivity(i);
            }
        });
        mNotlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = NotlarActivity.newIntent(getActivity());

                startActivity(i);
            }
        });
        mOgrencininHocalari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OgrencininHocalariActivity.newIntent(getActivity(), Tools.getID());
                startActivity(i);
            }
        });
        mNotEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = NotEklleActivity.newIntent(getActivity());
                startActivity(i);
            }
        });


        return v;
    }

    private void init(View view) {
        mLoginButton = (Button) view.findViewById(R.id.fragment_ana_sayfa_login_button);
        mGruplarButton = (Button) view.findViewById(R.id.fragment_ana_sayfa_gruplar_fragment_button);
        mHocaKayitButton = (Button) view.findViewById(R.id.fragment_ana_hoca_kayit_button);
        mMesajlarButton = (Button) view.findViewById(R.id.fragment_ana_sayfa_Mesajlar_button);
        mOgrenciKayit = (Button) view.findViewById(R.id.fragment_ana_sayfa_ogrenci_kayit_button);
        mNotlarButton = (Button) view.findViewById(R.id.fragment_ana_sayfa_notlar_button);
        mOgrencininHocalari = (Button) view.findViewById(R.id.fragment_ana_sayfa_nogrencin_hocalari_button);
        mNotEkleButton = (Button) view.findViewById(R.id.fragment_ana_sayfa_not_ekle_button);
    }
}
