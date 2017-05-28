package yildiz.pakistanbullusevcet.trakya.com.trakyauniproje.Models;

/**
 * Created by NFL on 27.05.2017.
 */

public class Login {

    /**
     * login_result : false
     * user_id : 0
     */

    private boolean login_result;
    private Long user_id;

    public boolean isLogin_result() {
        return login_result;
    }

    public void setLogin_result(boolean login_result) {
        this.login_result = login_result;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
