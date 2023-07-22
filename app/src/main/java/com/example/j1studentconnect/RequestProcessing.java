package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequestProcessing extends AppCompatActivity {

    private ImageButton btnRPHome, btnRPSearch, btnRPProfile;
    private LinearLayout btnRequestAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_request);

        btnRPHome = (ImageButton) findViewById(R.id.RequestProcessHome);
        btnRPSearch = (ImageButton) findViewById(R.id.RequestProcessSearch);
        btnRPProfile = (ImageButton) findViewById(R.id.RequestProcessProfile);
        btnRequestAdd = (LinearLayout) findViewById(R.id.add_request_bar);
        CreateAndShowInfoStudent();
        ClickButtonInRequestProcessing();
    }

    private void ClickButtonInRequestProcessing() {

        btnRequestAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, RequestAdd.class));
            }
        });
        btnRPHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, MainActivity.class));
            }
        });

        btnRPSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, Search.class));
            }
        });

        btnRPProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, Profile.class));
            }
        });
    }

    private void CreateAndShowInfoStudent() {
        TextView InfoProcessingRequest = findViewById(R.id.InfoProcessingRequest);
        //Intent intentBefore = getActivity().getIntent();
        //String student_id_child = intentBefore.getStringExtra("student_id").toString();
        String student_id_child = "12345678";
        DatabaseReference reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    String strshow = "Họ tên SV: " + snapshot.child("name").getValue().toString() + "\nMSSV: " + snapshot.child("student_id").getValue().toString();
                    InfoProcessingRequest.setText(strshow);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
