package id.radikz.ttes_magnakarsa1.repository;

import android.util.Log;

import id.radikz.ttes_magnakarsa1.model.User;
import id.radikz.ttes_magnakarsa1.model.UserResponse;
import id.radikz.ttes_magnakarsa1.network.APIRequest;
import id.radikz.ttes_magnakarsa1.network.RetrofitRequest;
import id.radikz.ttes_magnakarsa1.network.UserCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private APIRequest apiRequest;

    public UserRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(APIRequest.class);

    }

    public void getUsers(UserCallback callback) {
        apiRequest.getView()
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null){
//                            callback.onSuccess(res);
                            callback.onSuccess(response.body().getData());
                            callback.onMessage(response.body().getPesan());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        callback.onMessage(t.getMessage());
                    }
                });
    }

    public void create(String username, String password, UserCallback callback){
        apiRequest.create(username, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("repo", "berhasil");
                        callback.onMessage("berhasil menambahkan data");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("repo", "gagal");
                        callback.onMessage(t.getMessage());
                    }
                });
    }

    public void update(String username, String loginTime, String loginState, UserCallback callback){
        apiRequest.update(username, loginTime, loginState)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("repo", "berhasil");
                        callback.onMessage("berhasil mengupdate data");
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("repo", t.getMessage());
                        callback.onMessage(t.getMessage());
                    }
                });
    }
}

