package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciNotlar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

/**
 * Created by Deno on 24.04.2017.
 */

public class NotlarFragment extends Fragment {
    public static NotlarFragment newInstance() {
        Bundle args = new Bundle();

        NotlarFragment fragment = new NotlarFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private RecyclerView mRecyclerView;
    List<OgrenciNotlar> mNotlar = new ArrayList<OgrenciNotlar>();
    private RetrofitInterface mService = WS.getService();
    Call<OgrenciNotlar[]> mServiceCall;
    Long ogrId = 1l;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_not_paylasimlari, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.Notlar_Recyler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mService.getOgrenciNotlar(Tools.getID()).enqueue(new Callback<List<OgrenciNotlar>>() {
            @Override
            public void onResponse(Call<List<OgrenciNotlar>> call, Response<List<OgrenciNotlar>> response) {
                OgrenciNotlarAdapter adapter = new OgrenciNotlarAdapter(response.body());
                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<OgrenciNotlar>> call, Throwable t) {
                Toast.makeText(getActivity(), "onFaileru" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return (v);
    }




    private class OgrenciNotlarViewHolder extends RecyclerView.ViewHolder {
        private TextView mDersAdi;
        private TextView mNotlaricerik;
        private TextView mEkleyen;
        private TextView mTarih;
        private OgrenciNotlar mNotlar;


        public OgrenciNotlarViewHolder(View itemView) {
            super(itemView);
            mNotlaricerik = (TextView) itemView.findViewById(R.id.Notlar_icerik_Adi);
            mDersAdi = (TextView) itemView.findViewById(R.id.Notlar_Ders_Adi);
            mEkleyen = (TextView) itemView.findViewById(R.id.Notlar_Dersi_Ekleyen_Adi);
            mTarih = (TextView) itemView.findViewById(R.id.Notlar_Tarih);

        }


        private void bind(OgrenciNotlar Notlar) {
            mNotlaricerik.setText(Notlar.getNot_icerik());
            mDersAdi.setText(Notlar.getNot_baslik());
            mEkleyen.setText(Notlar.getNot_ekleyen());
            mTarih.setText(String.valueOf(Notlar.getNot_tarih()));

            mNotlar = Notlar;
        }

      /*  private String getDateStringFormat(Long secondsSince1970) {
            Date d = new Date(secondsSince1970 * 1000);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            // gün ve ay değerlerinin indexleri 0 olarak başlıyor yani ocak ayı için dönderilen değer 0 o yüzden +1 yapıyoruz
            return ((((cal.get(Calendar.DAY_OF_MONTH) + 1) > 9) ?
                    (cal.get(Calendar.DAY_OF_MONTH) + 1) : "0" + (cal.get(Calendar.DAY_OF_MONTH) + 1))
                    + "/" +
                    (((cal.get(Calendar.MONTH) + 1) > 9) ? (cal.get(Calendar.MONTH) + 1) : "0" + (cal.get(Calendar.MONTH) + 1))
                    + "/" +
                    cal.get(Calendar.YEAR)
                    + " - " +
                    ((cal.get(Calendar.HOUR_OF_DAY) > 9) ? cal.get(Calendar.HOUR_OF_DAY) : "0" + cal.get(Calendar.HOUR_OF_DAY))
                    + ":" +
                    ((cal.get(Calendar.MINUTE) > 9) ? cal.get(Calendar.MINUTE) : "0" + cal.get(Calendar.MINUTE)));
        }*/

    }

    private class OgrenciNotlarAdapter extends RecyclerView.Adapter<NotlarFragment.OgrenciNotlarViewHolder> {

        private List<OgrenciNotlar> mNotlarList;

        public OgrenciNotlarAdapter(List<OgrenciNotlar> mNotlars) {
            mNotlarList = mNotlars;

        }

        @Override
        public NotlarFragment.OgrenciNotlarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_notlar_recycler_doldurma, parent, false);

            return new NotlarFragment.OgrenciNotlarViewHolder(v);
        }

        @Override
        public void onBindViewHolder(NotlarFragment.OgrenciNotlarViewHolder holder, int position) {
            holder.bind(mNotlarList.get(position));
        }

        @Override
        public int getItemCount() {
            return mNotlarList.size();
        }
    }


}
