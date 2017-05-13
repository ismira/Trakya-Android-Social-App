package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

/**
 * Created by NFL on 12.05.2017.
 */

public class SonKonusmaMesaji {


    /**
     * mesaj_id : 3
     * gonderen_id : 6
     * gonderen_hoca_mi : true
     * alici_id : 5
     * alici_hoca_mi : true
     * diger_kisi_id : 6
     * diger_kisi_hoca_mi : true
     * alici_adi : İlhan UMUT
     * mesaj_tarih : 0
     * mesaj_icerik : BIL 122 nolu derse ek kontenjan açmamız mümkün müdür?
     */

    private Long mesaj_id;
    private Long gonderen_id;
    private boolean gonderen_hoca_mi;
    private Long alici_id;
    private boolean alici_hoca_mi;
    private Long diger_kisi_id;
    private boolean diger_kisi_hoca_mi;
    private String alici_adi;
    private Long mesaj_tarih;
    private String mesaj_icerik;

    public Long getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(Long mesaj_id) {
        this.mesaj_id = mesaj_id;
    }

    public Long getGonderen_id() {
        return gonderen_id;
    }

    public void setGonderen_id(Long gonderen_id) {
        this.gonderen_id = gonderen_id;
    }

    public boolean isGonderen_hoca_mi() {
        return gonderen_hoca_mi;
    }

    public void setGonderen_hoca_mi(boolean gonderen_hoca_mi) {
        this.gonderen_hoca_mi = gonderen_hoca_mi;
    }

    public Long getAlici_id() {
        return alici_id;
    }

    public void setAlici_id(Long alici_id) {
        this.alici_id = alici_id;
    }

    public boolean isAlici_hoca_mi() {
        return alici_hoca_mi;
    }

    public void setAlici_hoca_mi(boolean alici_hoca_mi) {
        this.alici_hoca_mi = alici_hoca_mi;
    }

    public Long getDiger_kisi_id() {
        return diger_kisi_id;
    }

    public void setDiger_kisi_id(Long diger_kisi_id) {
        this.diger_kisi_id = diger_kisi_id;
    }

    public boolean isDiger_kisi_hoca_mi() {
        return diger_kisi_hoca_mi;
    }

    public void setDiger_kisi_hoca_mi(boolean diger_kisi_hoca_mi) {
        this.diger_kisi_hoca_mi = diger_kisi_hoca_mi;
    }

    public String getAlici_adi() {
        return alici_adi;
    }

    public void setAlici_adi(String alici_adi) {
        this.alici_adi = alici_adi;
    }

    public Long getMesaj_tarih() {
        return mesaj_tarih;
    }

    public void setMesaj_tarih(Long mesaj_tarih) {
        this.mesaj_tarih = mesaj_tarih;
    }

    public String getMesaj_icerik() {
        return mesaj_icerik;
    }

    public void setMesaj_icerik(String mesaj_icerik) {
        this.mesaj_icerik = mesaj_icerik;
    }

}
