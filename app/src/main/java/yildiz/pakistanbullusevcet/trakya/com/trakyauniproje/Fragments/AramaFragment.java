package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.MesajlarActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.ArananKisi;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;

/**
 * Created by NFL on 21.05.2017.
 */

public class AramaFragment extends Fragment {

    private ProgressBar mProgressBar;
    private RetrofitInterface service = WS.getService();
    private RecyclerView mRecyclerView;

    public static AramaFragment newInstance() {
        Bundle args = new Bundle();
        AramaFragment fragment = new AramaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arama, container, false);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_view_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_view_menu_search_view);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length()>1){
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setEnabled(true);
                    serviceCall(newText);
                }
                return false;
            }
        });


    }

    private void serviceCall(String newText) {
        service.getArananKisiler(newText).enqueue(new Callback<List<ArananKisi>>() {
            @Override
            public void onResponse(Call<List<ArananKisi>> call, Response<List<ArananKisi>> response) {
                KisilerAdapter adapter = new KisilerAdapter(response.body());
                mRecyclerView.setAdapter(adapter);
                mProgressBar.setVisibility(View.GONE);
                mProgressBar.setEnabled(false);
            }

            @Override
            public void onFailure(Call<List<ArananKisi>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mProgressBar.setEnabled(false);
            }
        });
    }

    private void init(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_arama_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_arama_progress_bar);


    }

    private class KisilerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<ArananKisi> mKisiList;

        public KisilerAdapter(List<ArananKisi> _kisiList) {
            mKisiList = _kisiList;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_arama_item, parent, false);
            return new KisiHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            KisiHolder kisiHolder = (KisiHolder) holder;
            ArananKisi a = mKisiList.get(position);
            kisiHolder.bindHolder(a);
        }

        @Override
        public int getItemCount() {
            return mKisiList.size();
        }

        class KisiHolder extends RecyclerView.ViewHolder {
            private TextView mKisiTextView;
            private ImageButton mMesajGonderImageButton;
            private ImageButton mProfilImageButton;
            private ArananKisi mArananKisi;

            public void bindHolder(ArananKisi arananKisi) {
                mArananKisi = arananKisi;
                mKisiTextView.setText(mArananKisi.getAd());
                mMesajGonderImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = MesajlarActivity.newIntent(getActivity(), mArananKisi.getId(), mArananKisi.isHoca_mi());
                        startActivity(i);
                    }
                });

            }

            public KisiHolder(View itemView) {
                super(itemView);
                mMesajGonderImageButton = (ImageButton) itemView.findViewById(R.id.fragment_arama_item_message_image_button);
                mProfilImageButton = (ImageButton) itemView.findViewById(R.id.fragment_arama_item_profil_image_button);
                mKisiTextView = (TextView) itemView.findViewById(R.id.fragment_arama_item_kisi_text_view);

            }
        }

    }

}
