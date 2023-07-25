package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestProcessing extends AppCompatActivity {

    private ImageButton btnRPHome, btnRPSearch, btnRPProfile;
    private LinearLayout btnRequestAdd;

    RequestProcessingAdapter requestProcessingAdapter;
    ExpandableListView expandableListView1;
    List<String> RequestTypeList;
    HashMap<String, List<String>> StateRequestList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_request);

        btnRPHome = findViewById(R.id.RequestProcessHome);
        btnRPSearch = findViewById(R.id.RequestProcessSearch);
        btnRPProfile = findViewById(R.id.RequestProcessProfile);
        btnRequestAdd = findViewById(R.id.add_request_bar);
        CreateAndShowInfoStudent();
        ClickButtonInRequestProcessing();
        expandableListView1 = findViewById(R.id.StateRequestList1);

        showList();

        requestProcessingAdapter = new RequestProcessingAdapter(this, RequestTypeList, StateRequestList);
        expandableListView1.setAdapter(requestProcessingAdapter);


    }

    private void showList() {
        RequestTypeList = new ArrayList<String>();
        StateRequestList = new HashMap<String, List<String>>();

        RequestTypeList.add("Cấp bảng điểm");
        RequestTypeList.add("Đề nghị hoãn thi");
        RequestTypeList.add("Xem lại bài thi");
        RequestTypeList.add("Cấp lại thẻ sinh viên");
        RequestTypeList.add("Đề nghị làm vé xe bus");
        RequestTypeList.add("Xin thôi học");
        RequestTypeList.add("Cấp chứng chỉ tốt nghiệp tạm thời");

        List<String> subjectMon = new ArrayList<>();
        String date = "Ngày tạo : 24/7/2023\n";
        String file = "Tệp đính kèm: \n";
        String reason = "Lý do:";
        subjectMon.add(date + file + reason);

        List<String> subjectTue = new ArrayList<>();
        subjectTue.add("DSA");

        List<String> subjectWed = new ArrayList<>();
        subjectWed.add("DSA");

        List<String> subjectThu = new ArrayList<>();
        subjectThu.add("DSA");

        List<String> subjectFri = new ArrayList<>();
        subjectFri.add("DSA");

        List<String> subjectSat = new ArrayList<>();
        subjectSat.add("DSA");

        StateRequestList.put(RequestTypeList.get(0), subjectMon);
        StateRequestList.put(RequestTypeList.get(1), subjectTue);
        StateRequestList.put(RequestTypeList.get(2), subjectWed);
        StateRequestList.put(RequestTypeList.get(3), subjectThu);
        StateRequestList.put(RequestTypeList.get(4), subjectFri);
        StateRequestList.put(RequestTypeList.get(5), subjectSat);
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
        String student_id_child = "22026521";
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
