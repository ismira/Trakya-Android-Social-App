package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Mesaj;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;


/**
 * Created by NFL on 10.4.2017.
 */

public class MesajlarFragment extends Fragment { private RetrofitInterface service = WS.getService();
    private RecyclerView mMesajlarRecylerView;
    private MesajlarAdapter mMesajlarAdapter;
    private List<Mesaj> mMesajList;

    private Long diger_id;
    private boolean diger_hoca_mi;

    private EditText mMesajIcerikEditText;
    private Button mMesajGonderButton;

    private static final String EXTRA_DIGER_ID = "extra_diger_id";
    private static final String EXTRA_DIGER_HOCA_MI = "extra_diger_hoca_mi";
    /*
    private static final String EXTRA_GONDEREN_ID = "extra_gonderen_id";
    private static final String EXTRA_GONDEREN_HOCA_MI = "extra_gonderen_hoca_mi";
    private static final String EXTRA_ALICI_ID = "extra_alici_id";
    private static final String EXTRA_ALICI_HOCA_MI = "extra_alici_hoca_mi";
*/

    public static MesajlarFragment newInstance(Long diger_id, boolean diger_hoca_mi) {

        Bundle args = new Bundle();/*
        args.putLong(EXTRA_ALICI_ID, alici_id);
        args.putLong(EXTRA_GONDEREN_ID, gonderen_id);

        args.putBoolean(EXTRA_ALICI_HOCA_MI, alici_hoca_mi);
        args.putBoolean(EXTRA_GONDEREN_HOCA_MI, gonderen_hoca_mi);
        */
        args.putLong(EXTRA_DIGER_ID, diger_id);
        args.putBoolean(EXTRA_DIGER_HOCA_MI, diger_hoca_mi);

        MesajlarFragment fragment = new MesajlarFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void MesajlasmaKisileriniAyarla() {
        /*alici_hoca_mi = getArguments().getBoolean(EXTRA_ALICI_HOCA_MI);
        gonderen_hoca_mi = getArguments().getBoolean(EXTRA_GONDEREN_HOCA_MI);

        alici_id = getArguments().getLong(EXTRA_ALICI_ID);
        gonderen_id = getArguments().getLong(EXTRA_GONDEREN_ID);*/
        diger_id = getArguments().getLong(EXTRA_DIGER_ID);
        diger_hoca_mi = getArguments().getBoolean(EXTRA_DIGER_HOCA_MI);

        /*
        boolean extra_alici_hoca_mi = getArguments().getBoolean(EXTRA_ALICI_HOCA_MI);
        boolean extra_gonderen_hoca_mi = getArguments().getBoolean(EXTRA_GONDEREN_HOCA_MI);
        boolean extra_diger_hoca_mi = getArguments().getBoolean(EXTRA_DIGER_HOCA_MI);
        Long extra_alici_id = getArguments().getLong(EXTRA_ALICI_ID);
        Long extra_gonderen_id = getArguments().getLong(EXTRA_GONDEREN_ID);
        Long extra_diger_id = getArguments().getLong(EXTRA_DIGER_ID);

        if (extra_diger_id.equals(extra_alici_id) && extra_diger_hoca_mi == extra_alici_hoca_mi) {
            alici_id = extra_alici_id;
            alici_hoca_mi = extra_alici_hoca_mi;

            gonderen_id = extra_gonderen_id;
            gonderen_hoca_mi = extra_gonderen_hoca_mi;


        } else if (extra_diger_id.equals(extra_gonderen_id) && extra_diger_hoca_mi == extra_gonderen_hoca_mi) {
            gonderen_id = extra_alici_id;
            gonderen_hoca_mi = extra_alici_hoca_mi;

            alici_id = extra_diger_id;
            alici_hoca_mi = extra_diger_hoca_mi;

        } else {
            throw new IllegalStateException();
        }*/


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mesajlasma, container, false);
        MesajlasmaKisileriniAyarla();
        init(view);

        refreshMesajlar();

        // mesaj gonderme yeri
        mMesajGonderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mesajIcerik = mMesajIcerikEditText.getText().toString();
                Call<Boolean> mesajGonderCall = service.mesajGonder(diger_id, diger_hoca_mi, Tools.getID(), Tools.isHoca_mi(), mesajIcerik);
                mesajGonderCall.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        //Toast.makeText(getActivity(), "mesaj gonderildi", Toast.LENGTH_SHORT).show();
                        refreshMesajlar();
                        mMesajIcerikEditText.setText("");
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }

    private void updateUI() {
        mMesajlarAdapter = new MesajlarAdapter();
        mMesajlarRecylerView.setAdapter(mMesajlarAdapter);
    }

    private void refreshMesajlar() {
        Call<List<Mesaj>> mesajlarCall = service.getMesajlar(diger_id, diger_hoca_mi, Tools.getID(), Tools.isHoca_mi());
        mesajlarCall.enqueue(new Callback<List<Mesaj>>() {
            @Override
            public void onResponse(Call<List<Mesaj>> call, Response<List<Mesaj>> response) {
                Toast.makeText(getActivity(), "onResponse", Toast.LENGTH_SHORT).show();
                mMesajList = response.body();
                updateUI();
            }

            @Override
            public void onFailure(Call<List<Mesaj>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                Log.d("Hata:", t.toString());
                Log.d("ss gonderen id, hoca", String.valueOf(Tools.getID() + " " + Tools.isHoca_mi()));
                Log.d("ss alici id, hoca", String.valueOf(diger_id + " " + diger_hoca_mi));

            }
        });
    }


    private class MesajlarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int EXTRA_SAG_LAYOUT = 0;
        private final int EXTRA_SOL_LAYOUT = 1;

        class MesajSagLayout extends RecyclerView.ViewHolder {
            TextView mSagMesajIcerikTextView;
            TextView mSagMesajTarihTextView;

            public MesajSagLayout(View itemView) {
                super(itemView);
                mSagMesajIcerikTextView = (TextView) itemView.findViewById(R.id.activity_mesajlasma_sag_mesaj_metin_icerik);
                mSagMesajTarihTextView = (TextView) itemView.findViewById(R.id.activity_mesajlasma_sag_mesaj_mesaj_tarih);
            }

            private void bind(Mesaj mesaj) {
                mSagMesajIcerikTextView.setText(mesaj.getIcerik());

                mSagMesajTarihTextView.setText(getDateStringFormat(mesaj.getTarih()));
            }
        }

        class MesajSolLayout extends RecyclerView.ViewHolder {
            TextView mSolMesajIcerikTextView;
            TextView mSolMesajTarihTextView;

            public MesajSolLayout(View itemView) {
                super(itemView);
                mSolMesajIcerikTextView = (TextView) itemView.findViewById(R.id.activity_mesajlasma_sol_mesaj_mesaj_icerik);
                mSolMesajTarihTextView = (TextView) itemView.findViewById(R.id.activity_mesajlasma_sol_mesaj_mesaj_tarih);
            }

            private void bind(Mesaj mesaj) {
                mSolMesajIcerikTextView.setText(mesaj.getIcerik());
                mSolMesajTarihTextView.setText(getDateStringFormat(mesaj.getTarih()));
            }
        }

        @Override
        public int getItemViewType(int position) {
            Mesaj m = mMesajList.get(position);
            //alici ben isem


            if (m.getGonderen_id() == Tools.getID() && m.isGonderen_hoca_mi() == Tools.isHoca_mi()) {
                return EXTRA_SAG_LAYOUT;
            } else {
                return EXTRA_SOL_LAYOUT;
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case EXTRA_SAG_LAYOUT:
                    return new MesajSagLayout(LayoutInflater.from(getActivity())
                            .inflate(R.layout.activity_mesajlasma_sag_mesaj, parent, false));

                default:
                    return new MesajSolLayout(LayoutInflater.from(getActivity())
                            .inflate(R.layout.activity_mesajlasma_sol_mesaj, parent, false));


            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (holder.getItemViewType()) {
                case EXTRA_SAG_LAYOUT:
                    Mesaj m = mMesajList.get(position);
                    MesajSagLayout mesajSagLayout = (MesajSagLayout) holder;
                    mesajSagLayout.bind(m);
                    break;
                case EXTRA_SOL_LAYOUT:
                    Mesaj m1 = mMesajList.get(position);
                    MesajSolLayout mesajSolLayout = (MesajSolLayout) holder;
                    mesajSolLayout.bind(m1);
                    break;

            }
        }

        @Override
        public int getItemCount() {
            return mMesajList.size();
        }
    }

    // tek basamaklı dakika saat ay gün gibi değerlerin başına 0 koyarak bir string değeri döndürüyor
    private String getDateStringFormat(Long secondsSince1970) {
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
    }


    private void init(View view) {
        mMesajlarRecylerView = (RecyclerView) view.findViewById(R.id.activity_mesajlasma_recycler_view);
        mMesajlarRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mMesajIcerikEditText = (EditText) view.findViewById(R.id.activity_mesajlasma_mesaj_icerik_edit_text);
        mMesajGonderButton = (Button) view.findViewById(R.id.activity_mesajlasma_mesaj_gonder_button);
    }
}
