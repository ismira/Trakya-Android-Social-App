package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrenciKayitActivty;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.OgrenciProfilActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Activities.TabbedActivity;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Login;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.R;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.RetrofitInterface;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service.WS;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Tools;

public class LoginFragment extends Fragment {

    private RetrofitInterface service = WS.getService();

    private EditText mEditTextOgrenciNo;
    private EditText mEditTextSifre;
    private Button mButtonGonder;
    private TextView mOgrenciKayitOlTextView;

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

                    String sifreMD5 = Tools.MD5(mEditTextSifre.getText().toString());
                    final Long ogrenciNo = Long.parseLong(mEditTextOgrenciNo.getText().toString());
                    Log.i("hatalı sifre", sifreMD5);
                    service.isGiris(ogrenciNo, sifreMD5).enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            if (response.body().isLogin_result()) {
                                Tools.setID(response.body().getUser_id());
                                Tools.setHoca_mi(false);
                                Toast.makeText(getActivity(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                                //Intent i = OgrenciProfilActivity.newIntent(getActivity());
                                Intent i = TabbedActivity.newIntent(getActivity());
                                startActivity(i);

                            } else {
                                Toast.makeText(getActivity(), "Hatalı Giris Denemesi", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {
                            Toast.makeText(getActivity(), "onFailure", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        mOgrenciKayitOlTextView = (TextView) view.findViewById(R.id.fragment_giris_yap_ogrenci_kayit_ol_text_view);
        mOgrenciKayitOlTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OgrenciKayitActivty.newIntent(getActivity());
                startActivity(i);
            }
        });


        return view;
    }
}