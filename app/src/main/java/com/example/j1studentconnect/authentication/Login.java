package com.example.j1studentconnect.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.MainActivity;
import com.example.j1studentconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputEditText editTextId, editTextPassword;
    Button buttonLogin;
    ProgressBar progressBar;
    TextView textView;
    TextView registerRedirect, forgotRedirect;
    FirebaseAuth auth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        parameterConstruct();
        buttonsConstruct();
    }

    private void parameterConstruct() {
        editTextId = findViewById(R.id.student_id);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        forgotRedirect = findViewById(R.id.forgot_redirect);
        registerRedirect = findViewById(R.id.register_redirect);
        progressBar = findViewById(R.id.progressBar);
    }

    private void buttonsConstruct() {
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

        forgotRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
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
                if (snapshot.hasChildren()) {
                    editTextId.setError(null);
                    String email = snapshot.child("email").getValue().toString();
                    if (!password.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        editTextId.setError(null);
                                        String id_from_DB = snapshot.child("student_id").getValue().toString();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        intent.putExtra("student_id", id_from_DB);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Mã sinh viên hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(Login.this, "Hãy nhập mật khẩu.", Toast.LENGTH_SHORT).show();
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
