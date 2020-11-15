package id.radikz.ttes_magnakarsa1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("login_time")
    @Expose
    String login_time;

    @SerializedName("login_state")
    @Expose
    String login_state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLogin_state() {
        return login_state;
    }

    public void setLogin_state(String login_state) {
        this.login_state = login_state;
    }
}

