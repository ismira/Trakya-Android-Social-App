package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Gruplar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

/**
 * Created by Deno on 16.04.2017.
 */

public class GruplarFragment extends Fragment {

    public static GruplarFragment newInstance() {
        Bundle args = new Bundle();

        GruplarFragment fragment = new GruplarFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private RetrofitInterface mService = WS.getService();
    Call<Gruplar[]> mServiceCall;
    private RecyclerView mRecyclerView;
    List<Gruplar> mGruplar = new ArrayList<Gruplar>();
    Long ogrId = Tools.getID();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grulari_goster, container, false);
        //  Toast.makeText(getContext(),"sdfdsfdsf",Toast.LENGTH_LONG).show();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GruplariGetir();

        return view;
    }

    public void GruplariGetir() {

        try {
            // mOgrenciID = (Long) getArguments().getLong(ARG_OGRENCI_ID); // ogrenciId'si, ogrenciNo'su değil (farklışeyler)
            //Toast.makeText(getContext(), mOgrenciID.toString(), Toast.LENGTH_LONG).show();

            mRecyclerView.removeAllViews();
            mGruplar.clear();
            //  Toast.makeText(getContext(), ogrId.toString(), Toast.LENGTH_LONG).show();
            mService.getOgrenciGruplari(Tools.getID()).enqueue(new Callback<Gruplar[]>() {
                @Override
                public void onResponse(Call<Gruplar[]> call, Response<Gruplar[]> response) {
                    for (Gruplar gruplar : response.body()) {
                        if (gruplar.isGrupta_mi() == false) {
                            mGruplar.add(gruplar);
                        }
                        mRecyclerView.setAdapter(new GruplarAdapter(mGruplar));
                    }
                }

                @Override
                public void onFailure(Call<Gruplar[]> call, Throwable t) {

                }
            });


        } catch (Exception e) {

        }
    }


    private class GruplarViewHolder extends RecyclerView.ViewHolder implements Button.OnClickListener {
        private TextView mGrupAdi;
        private Button mBtnGrubaKatil;
        private Gruplar mGrup;


        public GruplarViewHolder(View itemView) {
            super(itemView);

            mBtnGrubaKatil = (Button) itemView.findViewById(R.id.BtnGrubuKatilx);
            mGrupAdi = (TextView) itemView.findViewById(R.id.TextGrupAdi);
            mBtnGrubaKatil.setOnClickListener(this);

        }


        private void bind(Gruplar gruplar) {
            mGrupAdi.setText(gruplar.getGrup_adi());
            mBtnGrubaKatil.setText("Gruba Katıl");
            mGrup = gruplar;
        }

        @Override
        public void onClick(View view) {


            try {
                mService.postGrubaKayitOl(Tools.getID(), mGrup.getId()).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                      //  Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                        GruplariGetir();

                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });


            } catch (Exception e) {

            }


        }
    }

    private class GruplarAdapter extends RecyclerView.Adapter<GruplarViewHolder> {

        private List<Gruplar> mGruplarList;

        public GruplarAdapter(List<Gruplar> mGruplars) {
            mGruplarList = mGruplars;
        }

        @Override
        public GruplarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_grupbaglama, parent, false);

            return new GruplarViewHolder(v);
        }

        @Override
        public void onBindViewHolder(GruplarViewHolder holder, int position) {
            holder.bind(mGruplarList.get(position));
        }

        @Override
        public int getItemCount() {
            return mGruplarList.size();
        }
    }
}
