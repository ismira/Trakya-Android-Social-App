package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrenciProfilActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

public class LoginFragment extends Fragment {

    private RetrofitInterface service = WS.getService();

    private EditText mEditTextOgrenciNo;
    private EditText mEditTextSifre;
    private Button mButtonGonder;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_giris_yap, container, false);
        mEditTextOgrenciNo = (EditText) view.findViewById(R.id.fragment_giris_yap_ogrenci_no_edit_text);
        mEditTextSifre = (EditText) view.findViewById(R.id.fragment_giris_yap_ogrenci_sifre_edit_text);
        mButtonGonder = (Button) view.findViewById(R.id.fragment_giris_yap_giris_button);

        mButtonGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextOgrenciNo.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Hata öğrenci numarası giriniz", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    final Long ogrenciNo = Long.parseLong(mEditTextOgrenciNo.getText().toString());

                    Call<Boolean> serviceCall;
                    serviceCall = service.isGiris(ogrenciNo, mEditTextSifre.getText().toString());

                    serviceCall.enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                           // Toast.makeText(getActivity(), "onResponse", Toast.LENGTH_SHORT).show();
                            Tools.setID(5l);
                            Tools.setHoca_mi(false);
                            Intent i = OgrenciProfilActivity.newIntent(getActivity(),3l );
                            //açılacak olan öğrencinin öğrenci numarasını vericez
                            //bak şimdi olaya ya girişte öğrencino ile giriş yapıyoruz
                            // bide öğrenciID diye birşey var hey ya anasını
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(getActivity(), "onFailed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
}