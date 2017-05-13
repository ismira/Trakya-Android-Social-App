package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

import java.util.List;

/**
 * Created by Deno on 8.05.2017.
 */

public class OgrenciHocalari {

    /**
     * hoca_ad : M. Tolga SAKALLI
     * hoca_email : tolga@trakya.edu.tr
     * hoca_unvan : Doç.Dr.
     * dersler : [{"ders_adi":"Ayrık Matematik"},{"ders_adi":"Bilgisayar Ağları"}]
     */

    private String hoca_ad;
    private String hoca_email;
    private String hoca_unvan;
    /**
     * ders_adi : Ayrık Matematik
     */

    private List<DerslerBean> dersler;

    public String getHoca_ad() {
        return hoca_ad;
    }

    public void setHoca_ad(String hoca_ad) {
        this.hoca_ad = hoca_ad;
    }

    public String getHoca_email() {
        return hoca_email;
    }

    public void setHoca_email(String hoca_email) {
        this.hoca_email = hoca_email;
    }

    public String getHoca_unvan() {
        return hoca_unvan;
    }

    public void setHoca_unvan(String hoca_unvan) {
        this.hoca_unvan = hoca_unvan;
    }

    public List<DerslerBean> getDersler() {
        return dersler;
    }

    public void setDersler(List<DerslerBean> dersler) {
        this.dersler = dersler;
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
