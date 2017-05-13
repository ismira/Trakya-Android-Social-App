package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

/**
 * Created by NFL on 10.4.2017.
 */

public class Mesaj {

    /**
     * gonderen_id : 3
     * gonderen_hoca_mi : false
     * alici_id : 1
     * alici_hoca_mi : false
     * icerik : Kanki sana bişey dicem
     * tarih : 1
     */

    private int gonderen_id;
    private boolean gonderen_hoca_mi;
    private int alici_id;
    private boolean alici_hoca_mi;
    private String icerik;
    private Long tarih;

    public int getGonderen_id() {
        return gonderen_id;
    }

    public void setGonderen_id(int gonderen_id) {
        this.gonderen_id = gonderen_id;
    }

    public boolean isGonderen_hoca_mi() {
        return gonderen_hoca_mi;
    }

    public void setGonderen_hoca_mi(boolean gonderen_hoca_mi) {
        this.gonderen_hoca_mi = gonderen_hoca_mi;
    }

    public int getAlici_id() {
        return alici_id;
    }

    public void setAlici_id(int alici_id) {
        this.alici_id = alici_id;
    }

    public boolean isAlici_hoca_mi() {
        return alici_hoca_mi;
    }

    public void setAlici_hoca_mi(boolean alici_hoca_mi) {
        this.alici_hoca_mi = alici_hoca_mi;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public Long getTarih() {
        return tarih;
    }

    public void setTarih(Long tarih) {
        this.tarih = tarih;
    }
}
