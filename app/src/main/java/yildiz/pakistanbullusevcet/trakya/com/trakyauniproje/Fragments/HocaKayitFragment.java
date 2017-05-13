package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
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
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;

/**
 * Created by Deno on 8.05.2017.
 */

public class HocaKayitFragment extends Fragment {
    public static HocaKayitFragment newInstance() {
        Bundle args = new Bundle();

        HocaKayitFragment fragment = new HocaKayitFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private RetrofitInterface mService = WS.getService();

    private Button mKayitOl;
    private EditText mHocaEmail;
    private EditText mHocaAd;
    private EditText mHocaSifre;
    private CheckBox mHocaUnvanDoc;
    private CheckBox mHocaUnvanDigerleri;
    private String textHocaAd;
    private String textHocaEmail;
    private String textHocaSifre;
    private Boolean mHocaUnvan = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoca_kayit, container, false);

        mKayitOl = (Button) view.findViewById(R.id.buttonHocaKayitOlKayit);
        mHocaAd = (EditText) view.findViewById(R.id.textHocaAdiKayit);
        mHocaEmail = (EditText) view.findViewById(R.id.textHocaEmailKayit);
        mHocaSifre = (EditText) view.findViewById(R.id.textHocaSifresiKayit);
        mHocaUnvanDoc = (CheckBox) view.findViewById(R.id.checkDocKayit);
        mHocaUnvanDigerleri = (CheckBox) view.findViewById(R.id.checkDigerleriKayit);
        mHocaUnvanDoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mHocaUnvan = true;
            }
        });

        mHocaUnvanDigerleri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mHocaUnvan = false;
            }
        });

        mKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textHocaEmail = mHocaEmail.getText().toString();
                textHocaAd = mHocaAd.getText().toString();
                textHocaSifre = mHocaSifre.getText().toString();

                mService.postHocaKayit(textHocaAd, textHocaEmail, textHocaSifre, mHocaUnvan, "Bilgisayar Mühendisliği").enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Toast.makeText(getContext(), String.valueOf(response.body()), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });


        return view;
    }
}
