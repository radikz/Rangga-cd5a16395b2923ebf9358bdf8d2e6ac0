package id.radikz.ttes_magnakarsa1.network;

import java.util.List;

import id.radikz.ttes_magnakarsa1.model.User;

public interface UserCallback {
    void onSuccess(List<User> user);

    void onMessage(String pesan);
}

