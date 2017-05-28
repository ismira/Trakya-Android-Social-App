package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.LoginActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciKayit;
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

    private RetrofitInterface service = WS.getService();


    private Button mKayitOl;
    private EditText mOgrenciNo;
    private EditText mOgrenciSifre;
    private CheckBox mOgrenciKiz;
    private CheckBox mOgrenciErkek;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ogrenci_kayit, container, false);

        mKayitOl = (Button) view.findViewById(R.id.buttonKayitOlKayit);
        mOgrenciNo = (EditText) view.findViewById(R.id.textOgrenciNumarasiKayit);
        mOgrenciSifre = (EditText) view.findViewById(R.id.textOgrenciSifresiKayit);
        mOgrenciKiz = (CheckBox) view.findViewById(R.id.checkKizKayit);
        mOgrenciErkek = (CheckBox) view.findViewById(R.id.checkErkekKayit);


        mOgrenciErkek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) mOgrenciKiz.setChecked(false);
                else mOgrenciKiz.setChecked(true);


            }
        });

        mOgrenciKiz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) mOgrenciErkek.setChecked(false);
                else mOgrenciErkek.setChecked(true);
            }
        });


        mKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mOgrenciErkek.isChecked() && !mOgrenciKiz.isChecked()) {
                    Toast.makeText(getActivity(), "Cinsiyet Seçiniz", Toast.LENGTH_SHORT).show();
                } else {
                    boolean erkek_mi = mOgrenciErkek.isChecked();
                    int cinsiyet_deger = (erkek_mi) ? 1 : 0;
                    String ogr_no = mOgrenciNo.getText().toString();
                    String ogr_sifre = mOgrenciSifre.getText().toString();
                    service.getOgrenciKayit(ogr_no, ogr_sifre, cinsiyet_deger).enqueue(new Callback<OgrenciKayit>() {
                        @Override
                        public void onResponse(Call<OgrenciKayit> call, Response<OgrenciKayit> response) {
                            if (response.body().isSuccess()) {
                                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                                dialog.setTitle("Kayıt Başarılı");
                                dialog.setMessage("Giriş Yapabilirsiniz.");
                                dialog.setCancelable(false);
                                dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = LoginActivity.newIntent(getActivity());
                                        startActivity(i);
                                    }
                                });
                                dialog.create();
                            } else {
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<OgrenciKayit> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

        return view;
    }
}
