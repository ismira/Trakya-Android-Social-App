package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Gruplar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.Mesaj;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciHocalari;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciNotlar;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.OgrenciProfil;
import yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models.SonKonusmaMesaji;

/**
 * Created by NFL on 3.4.2017.
 */

public interface RetrofitInterface {

    //Bunu(Call<Boolean>) boolean döndürmemeizin sebebi dönen değer sadece true ya da false geliyor olması
    @POST("login.php")
    Call<Boolean> isGiris(@Query("ogrenci_no") Long ogrenci_no,
                          @Query("sifre") String sifre);



    @POST("ogrenciRegister.php")
    Call<Boolean> postOgrenciKayit(@Query("ogrenci_no") String ogrenci_no,
                          @Query("ogrenci_sifre") String sifre,
                                   @Query("ogrenci_ad") String ogrenci_ad,
                                   @Query("ogrenci_cinsiyet") boolean ogrenci_cinsiyet,
                                   @Query("ogrenci_bolum ") String ogrenci_bolum);



    @FormUrlEncoded
    @POST("hocaRegister.php")
    Call<Boolean> postHocaKayit(@Field("hoca_ad") String hoca_ad,
                                   @Field("hoca_email") String hoca_email,
                                   @Field("hoca_sifre") String hoca_sifre,
                                   @Field("hoca_unvan") boolean hoca_unvan,
                                   @Field("hoca_bolum") String hoca_bolum);


    @GET("ogrenciProfil.php")
    Call<OgrenciProfil> getOgrenciProfil(@Query("ogrenci_id") Long ogrenci_no);

    @GET("getGruplar.php")
    Call<Gruplar[]> getOgrenciGruplari(@Query("ogrenci_id") Long ogrenci_no);


    @GET("getHocalar.php")
    Call<OgrenciHocalari[]> getOgrenciHocalari(@Query("ogrenci_id") Long ogrenci_no);

    @GET("getNotlar.php")
    Call<List<OgrenciNotlar>> getOgrenciNotlar(@Query("ogrenci_id") Long ogrenci_no);

    @FormUrlEncoded
    @POST("grubaAboneOl.php")
    Call<Boolean> postGrubaKayitOl(@Field("ogrenci_id") Long ogrenciId,
                                  @Field("grup_id") int grupId );

    @FormUrlEncoded
    @POST("getMesajlar.php")
    Call<List<Mesaj>> getMesajlar(@Field("alici_id") Long alici_id,
                                  @Field("alici_hoca_mi") boolean alici_hoca_mi,
                                  @Field("gonderen_id") Long gonderen_id,
                                  @Field("gonderen_hoca_mi") boolean gonderen_hoca_mi);

    @FormUrlEncoded
    @POST("mesajGonder.php")
    Call<Boolean> mesajGonder(@Field("alici_id") Long alici_id,
                              @Field("alici_hoca_mi") boolean alici_hoca_mi,
                              @Field("gonderen_id") Long gonderen_id,
                              @Field("gonderen_hoca_mi") boolean gonderen_hoca_mi,
                              @Field("mesaj_icerik") String mesaj_icerik);

    //kişinin mesajlaştığı kişilerin listesi son mesajı tarihi ve içeriği
    @FormUrlEncoded
    @POST("getMesajlarListesi.php")
    Call<List<SonKonusmaMesaji>> getMesajlarListesi(@Field("id") Long id,
                                                    @Field("hoca_mi") boolean hoca_mi);


}