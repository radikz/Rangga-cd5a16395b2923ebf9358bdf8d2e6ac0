package id.radikz.ttes_magnakarsa1;

import androidx.appcompat.app.AppCompatActivity;
import id.radikz.ttes_magnakarsa1.model.User;
import id.radikz.ttes_magnakarsa1.network.UserCallback;
import id.radikz.ttes_magnakarsa1.repository.UserRepository;
import id.radikz.ttes_magnakarsa1.util.PrefManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, register;
    private UserRepository userRepository;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.login_create_button);

        userRepository = new UserRepository();
        prefManager = new PrefManager(this);

        if (prefManager.catchBoolean("mylogin")){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        login.setOnClickListener(v -> {
            userRepository.getUsers(new UserCallback() {
                @Override
                public void onSuccess(List<User> user) {
                    String strUsername = username.getText().toString().trim();
                    String strPassword = password.getText().toString().trim();
                    short intLogin = 0;
                    for (int i = 0; i < user.size(); i += 1){
                        if (strUsername.equals(user.get(i).getUsername()) &&
                                strPassword.equals(user.get(i).getPassword())){
                            intLogin += 1;
                        }
                    }
                    if (intLogin > 0){
                        Toast.makeText(LoginActivity.this, "selamat", Toast.LENGTH_SHORT).show();
                        update(strUsername);
                        prefManager.saveBoolean("mylogin", true);
                        prefManager.saveString("myusername", strUsername);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        LoginActivity.this.startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "username / password salah", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onMessage(String pesan) {
//                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            });
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CreateActivity.class);
            LoginActivity.this.startActivity(intent);
        });
    }

    private void update(String username){
        String date = getDate();
        String status = "Aktif";
        Toast.makeText(LoginActivity.this, date, Toast.LENGTH_SHORT).show();
        userRepository.update(username, date, status, new UserCallback() {
            @Override
            public void onSuccess(List<User> user) {

            }

            @Override
            public void onMessage(String pesan) {
                Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}