package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Attr;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.GruplarActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Gruplar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciProfil;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;


/**
 * Created by NFL on 3.4.2017.
 */

public class OgrenciProfilFragment extends Fragment implements View.OnClickListener {

    public static final String mTag = OgrenciProfilFragment.class.getName();

    private static final String ARG_OGRENCI_ID = "ogrenci_id";

    public static Long mOgrenciID;

    private RetrofitInterface service = WS.getService();

    private Button mBtnTumGruplariGor;
    private Button mButtonClick;
    private TextView mTextViewOgrenciAdi;
    private TextView mTextViewFakulte;
    private TextView mTextViewBolum;
    private LinearLayout mGruplarLinearLayout;
    Call<OgrenciProfil> serviceCall;


    public static OgrenciProfilFragment newInstance(Long ogrenciID) {

        Bundle args = new Bundle();
        args.putLong(ARG_OGRENCI_ID, ogrenciID);
        OgrenciProfilFragment fragment = new OgrenciProfilFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ogrenci_profil2, container, false); // fark ederseniz 2.profil sayfasını inflate ediyoruz deneme amaçlı
        init(view); // wire up kısımı daha kolay yapabilmek için böyle yapıyoruz

        OgrenciBilgileriniGetir();
        OgrencininGruplarınıGetir();

        return view;
    }


    private void init(View view) {
        mBtnTumGruplariGor = (Button) view.findViewById(R.id.BtnTumGruplariGor);
        mBtnTumGruplariGor.setOnClickListener(this);
        mButtonClick = (Button) view.findViewById(R.id.btnClick);
        mButtonClick.setOnClickListener(this);

        mTextViewOgrenciAdi = (TextView) view.findViewById(R.id.textOgrenciAdi);
        mTextViewFakulte = (TextView) view.findViewById(R.id.textFakultesi);
        mTextViewBolum = (TextView) view.findViewById(R.id.textBolumAdi);
        mGruplarLinearLayout = (LinearLayout) view.findViewById(R.id.Gruplar);

    }

    private void OgrencininGruplarınıGetir() {
        try {
            mOgrenciID = (Long) getArguments().getLong(ARG_OGRENCI_ID); // ogrenciId'si, ogrenciNo'su değil (farklışeyler)
           // Toast.makeText(getContext(), mOgrenciID.toString(), Toast.LENGTH_LONG).show();

            Long ogrId = 1l;

         //   Toast.makeText(getContext(), ogrId.toString(), Toast.LENGTH_LONG).show();
            service.getOgrenciGruplari(ogrId).enqueue(new Callback<Gruplar[]>() {
                @Override
                public void onResponse(Call<Gruplar[]> call, Response<Gruplar[]> response) {
                    for (Gruplar gruplar : response.body()) {
                        if (gruplar.isGrupta_mi() == true) {
                            Button mButton = new Button(getContext());
                            mButton.setText(gruplar.getGrup_adi());
                            mGruplarLinearLayout.addView(mButton);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Gruplar[]> call, Throwable t) {

                }
            });


        } catch (Exception e) {

        }

    }

    private void OgrenciBilgileriniGetir() {
        try {
            mOgrenciID = (Long) getArguments().getLong(ARG_OGRENCI_ID); // ogrenciId'si, ogrenciNo'su değil (farklışeyler)
          //  Toast.makeText(getContext(), mOgrenciID.toString(), Toast.LENGTH_LONG).show();

            Long ogrId = 1l;
          //  Toast.makeText(getContext(), ogrId.toString(), Toast.LENGTH_LONG).show();
            serviceCall = service.getOgrenciProfil(ogrId);

            serviceCall.enqueue(new Callback<OgrenciProfil>() {
                @Override
                public void onResponse(Call<OgrenciProfil> call, Response<OgrenciProfil> response) {
                    mTextViewOgrenciAdi.setText(response.body().getOgrenci_ad());
                    mTextViewBolum.setText(response.body().getBolum().getBolum_adi());
                    mTextViewFakulte.setText(response.body().getBolum().getFakulte_adi());
                }

                @Override
                public void onFailure(Call<OgrenciProfil> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.btnClick) {

            Toast.makeText(getActivity(), "Merhaba Tombili", Toast.LENGTH_LONG).show();
        }
        if (view.getId() == R.id.BtnTumGruplariGor) {

           Intent i = GruplarActivity.newIntent(getContext(),mOgrenciID);
            startActivity(i);

        }


    }
}