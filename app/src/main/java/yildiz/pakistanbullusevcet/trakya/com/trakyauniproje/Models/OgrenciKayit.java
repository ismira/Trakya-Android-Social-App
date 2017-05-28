package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

/**
 * Created by NFL on 28.05.2017.
 */

public class OgrenciKayit {


    /**
     * success : false
     * message :  Kayıtlı Fakülte Var... | Kayıtlı bölüm var... |  --Dersler-- | Ders önceden eklenmişti...(MOBİL UYGULAMA GELİŞTİRME II) | Ders önceden eklenmişti...(AĞ GÜVENLİĞİ) | Ders önceden eklenmişti...(KABLOSUZ BİLGİSAYAR SİSTEMLERİ) | Ders önceden eklenmişti...(YAZILIM MÜHENDİSLİĞİ) | Ders önceden eklenmişti...(PROJE II) | Ders önceden eklenmişti...(İNSAN BİLGİSAYAR ETKİLEŞİMİ II) | Öğrenci önceden kayıt olmuş... |
     */

    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
