package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;

/**
 * Created by NFL on 12.05.2017.
 */

public class NotEkleFragment extends Fragment {

    public static NotEkleFragment newInstance() {
        Bundle args = new Bundle();
        NotEkleFragment fragment = new NotEkleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_not_ekle,container,false);

        return view;

    }
}
