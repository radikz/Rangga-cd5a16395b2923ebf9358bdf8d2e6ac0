package id.radikz.ttes_magnakarsa1;

import androidx.appcompat.app.AppCompatActivity;
import id.radikz.ttes_magnakarsa1.model.User;
import id.radikz.ttes_magnakarsa1.network.UserCallback;
import id.radikz.ttes_magnakarsa1.repository.UserRepository;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private EditText username, password;
    private Button register;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setTitle("Register");

        username = findViewById(R.id.create_username);
        password = findViewById(R.id.create_password);
        register = findViewById(R.id.create_button);

        userRepository = new UserRepository();

        register.setOnClickListener(v -> {
            String strUsername = username.getText().toString();
            String strPassword = password.getText().toString();
            userRepository.create(strUsername, strPassword, new UserCallback() {
                @Override
                public void onSuccess(List<User> user) {
                    finish();
                }

                @Override
                public void onMessage(String pesan) {
                    Toast.makeText(CreateActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}