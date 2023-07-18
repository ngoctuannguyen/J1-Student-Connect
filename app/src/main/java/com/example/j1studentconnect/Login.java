package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;

public class Login extends AppCompatActivity {

    TextInputEditText editTextId, editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView textView;
    TextView registerRedirect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextId = findViewById(R.id.student_id);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        registerRedirect = findViewById(R.id.register_redirect);
        progressBar = findViewById(R.id.progressBar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (!validateUsername() || !validatePassword()) {
                    progressBar.setVisibility(View.GONE);
                    return;
                } else {
                    checkUser();
                }
            }
        });

        registerRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername() {
        String student_id = editTextId.getText().toString();
        if (student_id.isEmpty()) {
            Toast.makeText(Login.this, "Hãy nhập mã sinh viên", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    public Boolean validatePassword(){
        String password = editTextPassword.getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(Login.this, "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    public void checkUser(){
        String student_id = editTextId.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                if (snapshot.hasChildren()){
                    editTextId.setError(null);
                    String password_from_DB = snapshot.child("password").getValue().toString();
                    if (password_from_DB.equals(password)) {
                        editTextId.setError(null);
                        String id_from_DB = snapshot.child("student_id").getValue().toString();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("student_id", id_from_DB);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
                        editTextPassword.requestFocus();
                    }
                } else {
                    Toast.makeText(Login.this, "Mã sinh viên không hợp lệ", Toast.LENGTH_SHORT).show();
                    editTextId.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
