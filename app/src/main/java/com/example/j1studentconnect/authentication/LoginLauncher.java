package com.example.j1studentconnect.authentication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.tabsinmain.MainActivity;
import com.example.j1studentconnect.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginLauncher extends AppCompatActivity {

    Button popup_menu;
    FirebaseAuth auth;

    TextView appName, quotes;

    ImageView logoUET, imageInLoginLauncher;

    Button btnchoose;
    int i, time = 400;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(LoginLauncher.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_activity_left_to_right_in, R.anim.anim_activity_left_to_right_out);
            finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_launcher);

        popup_menu = findViewById(R.id.btn_choose);
        auth = FirebaseAuth.getInstance();

        logoUET = findViewById(R.id.logo_uet);
        TextView login = findViewById(R.id.login);
        quotes = findViewById(R.id.quotes);
        quotes.setText("");
        btnchoose = findViewById(R.id.btn_choose);
        appName = findViewById(R.id.app_name);
        imageInLoginLauncher = findViewById(R.id.image_in_login_launcher);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn.setDuration(3000);
        logoUET.startAnimation(fadeIn);
        login.startAnimation(fadeIn);
        btnchoose.startAnimation(fadeIn);
//         quotes.startAnimation(fadeIn);
        imageInLoginLauncher.startAnimation(fadeIn);
        String str = "Trên bước đường thành công không có dấu chân của kẻ lười biếng";

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.setText("J");
                quotes.append("Trên");
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("1");
                quotes.append(" bước");
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append(" S");
                quotes.append(" đường");
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("t");
                quotes.append(" thành");
            }
        }, 700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("u");
                quotes.append(" công");
            }
        }, 800);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("d");
                quotes.append(" không");
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("e");
                quotes.append(" có");
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("n");
                quotes.append(" dấu");
            }
        }, 1100);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("t");
                quotes.append(" chân");
            }
        }, 1200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append(" C");
                quotes.append(" của");
            }
        }, 1300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("o");
                quotes.append(" kẻ");
            }
        }, 1400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("n");
                quotes.append(" lư");
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("n");
                quotes.append("ời");
            }
        }, 1600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("e");
                quotes.append(" b");
            }
        }, 1700);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("c");
                quotes.append("i");
            }
        }, 1800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("t");
                quotes.append("ếng");
            }
        }, 1900);

        popup_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(LoginLauncher.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.login_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.opt_vnu) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_activity_left_to_right_in, R.anim.anim_activity_left_to_right_out);
                            finish();
                            return true;
                        } else if (menuItem.getItemId() == R.id.opt_other) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ngoctuannguyen/J1-Student-Connect")));
                            overridePendingTransition(R.anim.anim_activity_slide_up, R.anim.anim_acitivity_slide_down);
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }
}