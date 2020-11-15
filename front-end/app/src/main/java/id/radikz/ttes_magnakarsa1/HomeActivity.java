package id.radikz.ttes_magnakarsa1;

import androidx.appcompat.app.AppCompatActivity;
import id.radikz.ttes_magnakarsa1.util.PrefManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private TextView home;
    private Button button;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
        prefManager = new PrefManager(this);

        home = findViewById(R.id.home_text);
        button = findViewById(R.id.home_button);

        button.setOnClickListener(v -> {
            final Handler handler = new Handler();

            Runnable runnableCode = new Runnable() {
                String username = prefManager.catchString("myusername");
                @Override
                public void run() {
                    String date = getDate();
                    home.setText("Hello, " + username + ". Waktu: "+ date);
//                    home.setText(date);
                    handler.postDelayed(this, 100);
                }
            };
            handler.post(runnableCode);
        });

    }

    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void startProgress() {
        // do something long
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String username = prefManager.catchString("myusername");
                String date = getDate();
                home.setText("Hello, " + username + " Waktu: "+ date);
            }
        };
        new Thread(runnable).start();
    }
}