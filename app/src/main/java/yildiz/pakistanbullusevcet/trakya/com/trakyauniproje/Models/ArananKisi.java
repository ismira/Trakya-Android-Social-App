package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

/**
 * Created by NFL on 21.05.2017.
 */

public class ArananKisi {

    /**
     * id : 1
     * hoca_mi : false
     * ad : Erkan Karataş
     */

    private Long id;
    private boolean hoca_mi;
    private String ad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHoca_mi() {
        return hoca_mi;
    }

    public void setHoca_mi(boolean hoca_mi) {
        this.hoca_mi = hoca_mi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
}
