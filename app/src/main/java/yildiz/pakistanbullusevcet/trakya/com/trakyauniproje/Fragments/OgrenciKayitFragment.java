package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrenciProfilActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Gruplar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;

/**
 * Created by Deno on 8.05.2017.
 */

public class OgrenciKayitFragment extends Fragment {
    public static OgrenciKayitFragment newInstance() {
        Bundle args = new Bundle();

        OgrenciKayitFragment fragment = new OgrenciKayitFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private RetrofitInterface mService = WS.getService();


    private Button mKayitOl;
    private EditText mOgrenciNo;
    private EditText mOgrenciAd;
    private EditText mOgrenciSifre;
    private CheckBox mOgrenciKız;
    private CheckBox mOgrenciErkek;
    private String textOgrenciAd;
    private String textOgrenciNo;
    private String textOgrenciSifre;
    private Boolean mOgrenciCinsiyet = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ogrenci_kayit, container, false);

        mKayitOl = (Button) view.findViewById(R.id.buttonKayitOlKayit);
        mOgrenciAd = (EditText) view.findViewById(R.id.textOgrenciAdiKayit);
        mOgrenciNo = (EditText) view.findViewById(R.id.textOgrenciNumarasiKayit);
        mOgrenciSifre = (EditText) view.findViewById(R.id.textOgrenciSifresiKayit);
        mOgrenciKız = (CheckBox) view.findViewById(R.id.checkKizKayit);
        mOgrenciErkek = (CheckBox) view.findViewById(R.id.checkErkekKayit);


        mOgrenciErkek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mOgrenciCinsiyet = true;
            }
        });

        mOgrenciKız.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mOgrenciCinsiyet = false;
            }
        });


        mKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textOgrenciNo = mOgrenciNo.getText().toString();
                textOgrenciAd = mOgrenciAd.getText().toString();
                textOgrenciSifre = mOgrenciSifre.getText().toString();
                try {
                    mService.postOgrenciKayit(textOgrenciNo, textOgrenciSifre, textOgrenciAd, mOgrenciCinsiyet, "Bilgisayar Mühendisligi").enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                         /*   if (response.body()) {
                                Toast.makeText(getContext(), "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                            }*/
                            Toast.makeText(getContext(), String.valueOf( response.body()), Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(getContext(), "Kayıt Başarısız", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
