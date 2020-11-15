package id.radikz.ttes_magnakarsa1.network;

import id.radikz.ttes_magnakarsa1.model.User;
import id.radikz.ttes_magnakarsa1.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequest {
    @FormUrlEncoded
    @POST("create.php")
    Call<User> create(@Field("username") String username,
                      @Field("password") String password
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<User> update(@Field("username") String username,
                      @Field("login_time") String loginTime,
                      @Field("login_state") String LoginState
    );

    @GET("retrieve.php")
    Call<UserResponse> getView();

}
