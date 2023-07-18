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

    TextView name, email, student_id, password, gender, birthday, student_class, phone;
    String user_id, user_name, user_email, user_gender, user_class, user_birthday, user_phone, student_id_child;

    TextView title_name;
    Button edit_profile, logout;
    DatabaseReference reference;
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
        phone = findViewById(R.id.phone);
        title_name = findViewById(R.id.title_name);
        edit_profile = findViewById(R.id.btn_edit_profile);
        logout = findViewById(R.id.btn_logout);

        Intent intentBefore = getIntent();
        student_id_child = intentBefore.getStringExtra("student_id").toString();
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);

        showAllUserData();

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                intent.putExtra("student_id", user_id);
                intent.putExtra("name", user_name);
                intent.putExtra("phone", user_phone);
                intent.putExtra("email", user_email);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, LoginLauncher.class));
            }
        });
    }

    public void showAllUserData() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    user_id = snapshot.child("student_id").getValue().toString();
                    user_name = snapshot.child("name").getValue().toString();
                    user_email = snapshot.child("email").getValue().toString();
                    user_gender = snapshot.child("gender").getValue().toString();
                    user_class = snapshot.child("student_class").getValue().toString();
                    user_birthday = snapshot.child("birthday").getValue().toString();
                    user_phone = snapshot.child("phone").getValue().toString();
                    title_name.setText(user_name);
                    name.setText(user_name);
                    email.setText(user_email);
                    student_id.setText(user_id);
                    birthday.setText(user_birthday);
                    gender.setText(user_gender);
                    student_class.setText(user_class);
                    phone.setText(user_phone);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
