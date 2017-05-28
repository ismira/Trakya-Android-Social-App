package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

/**
 * Created by Deno on 15.04.2017.
 */

public class Gruplar {

    /**
     * id : 1
     * grup_adi : T.Ü Doğa Sporları Kulübü
     * grup_aciklama : İlgilendiğiniz Sporları burada bulup birlikte eğlenmemizi sağlayabilirsiniz.
     * grupta_mi : false
     */

    private int id;
    private String grup_adi;
    private String grup_aciklama;
    private boolean grupta_mi;

    public int getId() {
        return id;
    }

    public void setİd(int id) {
        this.id = id;
    }

    public String getGrup_adi() {
        return grup_adi;
    }

    public void setGrup_adi(String grup_adi) {
        this.grup_adi = grup_adi;
    }

    public String getGrup_aciklama() {
        return grup_aciklama;
    }

    public void setGrup_aciklama(String grup_aciklama) {
        this.grup_aciklama = grup_aciklama;
    }

    public boolean isGrupta_mi() {
        return grupta_mi;
    }

    public void setGrupta_mi(boolean grupta_mi) {
        this.grupta_mi = grupta_mi;
    }
}
