package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Gruplar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciHocalari;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

/**
 * Created by Deno on 8.05.2017.
 */

public class OgrencininHocalariFragment extends Fragment {
    public static OgrencininHocalariFragment newInstance() {
        Bundle args = new Bundle();

        OgrencininHocalariFragment fragment = new OgrencininHocalariFragment();
        fragment.setArguments(args);

        return fragment;
    }


    private RetrofitInterface mService = WS.getService();
    Call<Gruplar[]> mServiceCall;
    private RecyclerView mRecyclerView;
    List<OgrenciHocalari> mOgrenciHocalari = new ArrayList<OgrenciHocalari>();
    Long ogrId = 1l;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ogrenci_hocalari_goster, container, false);
        //  Toast.makeText(getContext(),"sdfdsfdsf",Toast.LENGTH_LONG).show();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ogrenci_hocalari_recyler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        OgrencininHocalariniGetir();

        return view;
    }

    private void OgrencininHocalariniGetir() {
        try {
            mService.getOgrenciHocalari(Tools.getID()).enqueue(new Callback<OgrenciHocalari[]>() {
                @Override
                public void onResponse(Call<OgrenciHocalari[]> call, Response<OgrenciHocalari[]> response) {
                    for(OgrenciHocalari OH:response.body()){
                        mOgrenciHocalari.add(OH);
                    }
                   mRecyclerView.setAdapter(new OgrencininHocalariFragment.OgrenciHocalariAdapter(mOgrenciHocalari));
                }

                @Override
                public void onFailure(Call<OgrenciHocalari[]> call, Throwable t) {

                }
            });


        } catch (Exception e) {

        }
    }


    private class OgrenciHocalariViewHolder extends RecyclerView.ViewHolder  {
        private TextView mHocaAdi;
        private TextView mHocaUnvan;
        private TextView mHocaMail;
        private TextView mHocaVerdigiDersler;
        private OgrenciHocalari mOgrenciHocalari;


        public OgrenciHocalariViewHolder(View itemView) {
            super(itemView);

            mHocaMail = (TextView) itemView.findViewById(R.id.textHocaMail);
            mHocaVerdigiDersler = (TextView) itemView.findViewById(R.id.textHocaVerdigiDersler);
            mHocaAdi = (TextView) itemView.findViewById(R.id.textHocaAdi);
            mHocaUnvan = (TextView) itemView.findViewById(R.id.textHocaUnvan);


        }


        private void bind(OgrenciHocalari ogrenciHocalari) {
            mHocaAdi.setText(ogrenciHocalari.getHoca_ad());
            mHocaMail.setText(ogrenciHocalari.getHoca_email());
            mHocaUnvan.setText(ogrenciHocalari.getHoca_unvan());
            String Dersadlari="";
            for(OgrenciHocalari.DerslerBean derslerBean : ogrenciHocalari.getDersler()){
              Dersadlari +=","+derslerBean.getDers_adi();
            }
            mHocaVerdigiDersler.setText(Dersadlari);
            Dersadlari="";
            mOgrenciHocalari = ogrenciHocalari;
        }


    }


    private class OgrenciHocalariAdapter extends RecyclerView.Adapter<OgrenciHocalariViewHolder> {

        private List<OgrenciHocalari> mOgrenciHocalari;

        public OgrenciHocalariAdapter(List<OgrenciHocalari> OgrenciHocalari) {
            mOgrenciHocalari = OgrenciHocalari;
        }

        @Override
        public OgrencininHocalariFragment.OgrenciHocalariViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_ogrenci_hocalari_baglama, parent, false);

            return new OgrencininHocalariFragment.OgrenciHocalariViewHolder(v);
        }

        @Override
        public void onBindViewHolder(OgrencininHocalariFragment.OgrenciHocalariViewHolder holder, int position) {
            holder.bind(mOgrenciHocalari.get(position));
        }

        @Override
        public int getItemCount() {
            return mOgrenciHocalari.size();
        }
    }




}
