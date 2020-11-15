package id.radikz.ttes_magnakarsa1.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("data")
    @Expose
    private List<User> data = null;

    public String getPesan() {
        return pesan;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}

