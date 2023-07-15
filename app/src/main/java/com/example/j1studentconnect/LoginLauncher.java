package com.example.j1studentconnect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginLauncher extends AppCompatActivity {

    Button popup_menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_launcher);

        popup_menu = findViewById(R.id.btn_choose);

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
                            finish();
                            return true;
                        } else if (menuItem.getItemId() == R.id.opt_other) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ngoctuannguyen/J1-Student-Connect")));
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
