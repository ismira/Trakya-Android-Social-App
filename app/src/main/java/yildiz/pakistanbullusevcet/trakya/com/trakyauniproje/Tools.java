package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje;

/**
 * Created by NFL on 12.05.2017.
 */
public class Tools {
    //kullanıcı giris yaptığı zaman buradaki id set edilir
    //herhangi bir sorguda mesela id'ye göre kullanıcı bilgilerinin getirildiği sorguda burdan çekilir.
    private Tools sTools;



    private static Long mID;
    private static boolean hoca_mi;

    public static Long getID() {
        return mID;
    }

    public static void setID(Long _ID) {
        mID = _ID;
    }

    public static boolean isHoca_mi() {
        return hoca_mi;
    }

    public static void setHoca_mi(boolean _hoca_mi) {
        hoca_mi = _hoca_mi;
    }
}
