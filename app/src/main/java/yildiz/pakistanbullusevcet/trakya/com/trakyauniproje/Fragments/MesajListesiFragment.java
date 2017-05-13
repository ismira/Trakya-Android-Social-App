package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.MesajlarActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.SonKonusmaMesaji;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

/**
 * Created by NFL on 12.05.2017.
 */

public class MesajListesiFragment extends Fragment {


    private final static String EXTRA_ID = "extra_id";
    private final static String EXTRA_HOCA_MI = "extra_hoca_mi";
    private RetrofitInterface service = WS.getService();

    private Long mID;
    private boolean mHoca_mi;

    private List<SonKonusmaMesaji> mSonKonusmaMesajlarListesi;
    private SonKonusmaMesajlariAdapter mAdapter;


    private RecyclerView mMesajlarListesiRecyclerView;


    //hesaba giriş yapan kişinin bilgileri
    public static MesajListesiFragment newInstance() {

        Bundle args = new Bundle();

        MesajListesiFragment fragment = new MesajListesiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mesajlar_listesi, container, false);
        init(view);
        mID = Tools.getID();
        mHoca_mi = Tools.isHoca_mi();
        refreshSonKonusmaMesaji(mID, mHoca_mi);
        return view;
    }

    private void refreshSonKonusmaMesaji(Long id, boolean hoca_mi) {
        Call<List<SonKonusmaMesaji>> konusmaListesi = service.getMesajlarListesi(id, hoca_mi);
        konusmaListesi.enqueue(new Callback<List<SonKonusmaMesaji>>() {
            @Override
            public void onResponse(Call<List<SonKonusmaMesaji>> call, Response<List<SonKonusmaMesaji>> response) {
                mSonKonusmaMesajlarListesi = response.body();
                mAdapter = new SonKonusmaMesajlariAdapter(mSonKonusmaMesajlarListesi);
                mMesajlarListesiRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<SonKonusmaMesaji>> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(View view) {
        mMesajlarListesiRecyclerView = (RecyclerView) view.findViewById(R.id.activity_mesajlar_listesi_recycler_view);
        mMesajlarListesiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private class SonKonusmaMesajlariAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<SonKonusmaMesaji> mesajList;

        public SonKonusmaMesajlariAdapter(List<SonKonusmaMesaji> pSonKonusmaMesajListesi) {
            mesajList = pSonKonusmaMesajListesi;
        }


        class MesajViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView mKisiAdiTextView;
            private TextView mSonMesajTextView;
            private TextView mMesajTarihTextView;

            private SonKonusmaMesaji mSonMesaj;

            private MesajViewHolder(View itemView) { // FIXME: 18.04.2017 maybe error ? for private contructure
                super(itemView);
                itemView.setOnClickListener(this);
                mKisiAdiTextView = (TextView) itemView.findViewById(R.id.activity_mesajlar_listesi_mesaj_kisi_adi_text_view);
                mSonMesajTextView = (TextView) itemView.findViewById(R.id.activity_mesajlar_listesi_mesaj_son_mesaj_text_view);
                mMesajTarihTextView = (TextView) itemView.findViewById(R.id.activity_mesajlar_listesi_mesaj_tarihi_text_view);
            }

            @Override
            public void onClick(View v) {
                Intent intent = MesajlarActivity.newIntent(getActivity(), mSonMesaj.getDiger_kisi_id(), mSonMesaj.isDiger_kisi_hoca_mi());
                startActivity(intent);
            }

            public void bind(SonKonusmaMesaji sonKonusmaMesaji) {
                mSonMesaj = sonKonusmaMesaji;

                mKisiAdiTextView.setText(sonKonusmaMesaji.getAlici_adi());
                if (mID.equals(sonKonusmaMesaji.getGonderen_id())) {
                    mSonMesajTextView.setText("Siz: " + sonKonusmaMesaji.getMesaj_icerik());
                } else {
                    mSonMesajTextView.setText("O: " + sonKonusmaMesaji.getMesaj_icerik());
                }

                mMesajTarihTextView.setText(sonKonusmaMesaji.getMesaj_tarih().toString()); // FIXME: 18.04.2017 override tostring
            }
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.activity_mesajlar_listesi_mesaj, parent, false);

            return new MesajViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MesajViewHolder mesajViewHolder = (MesajViewHolder) holder;
            mesajViewHolder.bind(mesajList.get(position));
        }

        @Override
        public int getItemCount() {
            return mesajList.size();
        }
    }

}
