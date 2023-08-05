package com.example.j1studentconnect.authentication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    TextView appName;

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

        appName = findViewById(R.id.app_name);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.setText("J");
            }
        }, 300);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("1");
            }
        }, 350);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append(" S");
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("t");
            }
        }, 450);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("u");
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("d");
            }
        }, 550);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("e");
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("n");
            }
        }, 650);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("t");
            }
        }, 700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append(" C");
            }
        }, 750);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("o");
            }
        }, 800);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("n");
            }
        }, 850);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("n");
            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("e");
            }
        }, 950);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("c");
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appName.append("t");
            }
        }, 1800);

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
