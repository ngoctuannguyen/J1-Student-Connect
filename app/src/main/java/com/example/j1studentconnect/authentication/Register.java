package com.example.j1studentconnect.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.profiletab.HelperClass;
import com.example.j1studentconnect.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextInputEditText registerId, registerEmail, registerPassword, retypePassword;
    TextView loginRedirect;
    Button btn_register;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        parameterConstruct();
        buttonsConstruct();
    }

    private void parameterConstruct() {
        registerId = findViewById(R.id.register_id);
        registerEmail = findViewById(R.id.register_email);
        registerPassword = findViewById(R.id.register_password);
        retypePassword = findViewById(R.id.retype_password);
        loginRedirect = findViewById(R.id.login_redirect);
        btn_register = findViewById(R.id.btn_register);
    }

    private void buttonsConstruct() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/");
                reference = database.getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users");

                String user_email = registerEmail.getText().toString();
                String student_id = registerId.getText().toString();
                String password = registerPassword.getText().toString();
                String retype_password = retypePassword.getText().toString();

                if (!password.equals(retype_password)) {
                    Toast.makeText(Register.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                HelperClass helperClass = new HelperClass(student_id, "Trống", "Trống", "Trống", user_email, "Trống", "Trống");
                reference.child(student_id).setValue(helperClass);
                auth.createUserWithEmailAndPassword(user_email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Register.this, "Đăng ký thất bại" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}