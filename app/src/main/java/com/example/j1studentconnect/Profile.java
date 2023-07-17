package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView name, email, student_id, password, gender, birthday, student_class;
    TextView title_name;
    Button edit_profile, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.student_name);
        student_id = findViewById(R.id.student_id);
        gender = findViewById(R.id.gender);
        birthday = findViewById(R.id.birthday);
        student_class = findViewById(R.id.student_class);
        email = findViewById(R.id.email);
        title_name = findViewById(R.id.title_name);
        edit_profile = findViewById(R.id.btn_edit_profile);
        logout = findViewById(R.id.btn_logout);

        showAllUserData();

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

    }

    public void showAllUserData(){
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_id = intent.getStringExtra("student_id");
        String user_password = intent.getStringExtra("password");
        String user_gender = intent.getStringExtra("gender");
        String user_class = intent.getStringExtra("student_class");
        String user_birthday = intent.getStringExtra("birthday");

        title_name.setText(user_name);
        name.setText(user_name);
        email.setText(user_email);
        student_id.setText(user_id);
        birthday.setText(user_birthday);
        gender.setText(user_gender);
        student_class.setText(user_class);
    }
    public void passUserData(){
        String user_id = student_id.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");
        Query checkUserDatabase = reference.orderByChild("student_id").equalTo(user_id);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String id_from_DB = snapshot.child(user_id).child("student_id").getValue(String.class);
                    String name_from_DB = snapshot.child(user_id).child("name").getValue(String.class);
                    String email_from_DB = snapshot.child(user_id).child("email").getValue(String.class);
                    String password_from_DB = snapshot.child(user_id).child("password").getValue(String.class);
                    String gender_from_DB = snapshot.child(user_id).child("gender").getValue(String.class);
                    String class_from_DB = snapshot.child(user_id).child("student_class").getValue(String.class);
                    String birthday_from_DB = snapshot.child(user_id).child("birthday").getValue(String.class);
                    Intent intent = new Intent(Profile.this, MainActivity.class);
                    intent.putExtra("name", name_from_DB);
                    intent.putExtra("email", email_from_DB);
                    intent.putExtra("student_id", id_from_DB);
                    intent.putExtra("password", password_from_DB);
                    intent.putExtra("gender", email_from_DB);
                    intent.putExtra("student_class", id_from_DB);
                    intent.putExtra("birthday", password_from_DB);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
