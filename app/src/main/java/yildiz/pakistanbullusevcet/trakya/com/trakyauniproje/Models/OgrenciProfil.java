package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

import java.util.List;

/**
 * Created by NFL on 3.4.2017.
 */

public class OgrenciProfil {

    /**
     * ogrenci_no : 1131602902
     * ogrenci_ad : Erkan Karataş
     * ogrenci_cinsiyet : true
     * ogrenci_ders : 1,6
     * dersler : [{"ders_adi":"Programlama Dillerine Giriş "},{"ders_adi":"Nümerik Analiz"}]
     * bolum : {"bolum_adi":"Bilgisayar Mühendisliği Bölümü","fakulte_adi":"Mühendislik Fakültesi"}
     */

    private int ogrenci_no;
    private String ogrenci_ad;
    private boolean ogrenci_cinsiyet;
    private String ogrenci_ders;
    /**
     * bolum_adi : Bilgisayar Mühendisliği Bölümü
     * fakulte_adi : Mühendislik Fakültesi
     */

    private BolumBean bolum;
    /**
     * ders_adi : Programlama Dillerine Giriş
     */

    private List<DerslerBean> dersler;

    public int getOgrenci_no() {
        return ogrenci_no;
    }

    public void setOgrenci_no(int ogrenci_no) {
        this.ogrenci_no = ogrenci_no;
    }

    public String getOgrenci_ad() {
        return ogrenci_ad;
    }

    public void setOgrenci_ad(String ogrenci_ad) {
        this.ogrenci_ad = ogrenci_ad;
    }

    public boolean isOgrenci_cinsiyet() {
        return ogrenci_cinsiyet;
    }

    public void setOgrenci_cinsiyet(boolean ogrenci_cinsiyet) {
        this.ogrenci_cinsiyet = ogrenci_cinsiyet;
    }

    public String getOgrenci_ders() {
        return ogrenci_ders;
    }

    public void setOgrenci_ders(String ogrenci_ders) {
        this.ogrenci_ders = ogrenci_ders;
    }

    public BolumBean getBolum() {
        return bolum;
    }

    public void setBolum(BolumBean bolum) {
        this.bolum = bolum;
    }

    public List<DerslerBean> getDersler() {
        return dersler;
    }

    public void setDersler(List<DerslerBean> dersler) {
        this.dersler = dersler;
    }

    public static class BolumBean {
        private String bolum_adi;
        private String fakulte_adi;

        public String getBolum_adi() {
            return bolum_adi;
        }

        public void setBolum_adi(String bolum_adi) {
            this.bolum_adi = bolum_adi;
        }

        public String getFakulte_adi() {
            return fakulte_adi;
        }

        public void setFakulte_adi(String fakulte_adi) {
            this.fakulte_adi = fakulte_adi;
        }
    }

    public static class DerslerBean {
        private String ders_adi;

        public String getDers_adi() {
            return ders_adi;
        }

        public void setDers_adi(String ders_adi) {
            this.ders_adi = ders_adi;
        }
    }
}
