package com.example.j1studentconnect.request;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.searchtab.Search;
import com.example.j1studentconnect.tabsinmain.MainActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestProcessing extends AppCompatActivity {

    RequestProcessingAdapter requestProcessingAdapter;
    ExpandableListView expandableListView1;
    List<String> RequestTypeList;
    HashMap<String, List<String>> StateRequestList;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    DatabaseReference requestRef = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("requests").child(currentUser.getUid());
    private ImageButton btnRPHome, btnRPSearch, btnRPProfile;
    private LinearLayout btnRequestAdd;
    private java.util.Calendar today = java.util.Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_request);

        FirebaseApp.initializeApp(this);
        RequestTypeList = new ArrayList<>();

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
        List<String> subjectMon = new ArrayList<>();

        RequestTypeList.add("Cấp bảng điểm");
        RequestTypeList.add("Đề nghị hoãn thi");
        RequestTypeList.add("Xem lại bài thi");
        RequestTypeList.add("Cấp lại thẻ sinh viên");
        RequestTypeList.add("Đề nghị làm vé xe bus");
        RequestTypeList.add("Xin thôi học");
        RequestTypeList.add("Cấp CN tốt nghiệp tạm thời");
        RequestTypeList.add("Đề nghị hưởng trợ cấp xã hội");


        DatabaseReference capBangDiemRef = requestRef.child("Cấp bảng điểm");
        capBangDiemRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectMon.add(date + reason + state);
                    else subjectMon.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        List<String> subjectTue = new ArrayList<>();
        DatabaseReference hoanThiRef = requestRef.child("Đề nghị hoãn thi");
        hoanThiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectTue.add(date + reason + state);
                    else subjectTue.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<String> subjectWed = new ArrayList<>();
        DatabaseReference xemLaiBaiRef = requestRef.child("Xem lại bài thi");
        xemLaiBaiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectWed.add(date + reason + state);
                    else subjectWed.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<String> subjectThu = new ArrayList<>();
        DatabaseReference capTSVRef = requestRef.child("Cấp lại thẻ sinh viên");
        capTSVRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectThu.add(date + reason + state);
                    else subjectThu.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<String> subjectFri = new ArrayList<>();
        DatabaseReference lamVeBusRef = requestRef.child("Đề nghị làm vé xe bus");
        lamVeBusRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectFri.add(date + reason + state);
                    else subjectFri.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<String> subjectSat = new ArrayList<>();
        DatabaseReference xinThoiHocRef = requestRef.child("Xin thôi học");
        xinThoiHocRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectSat.add(date + reason + state);
                    else subjectSat.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<String> subjectSun = new ArrayList<>();
        DatabaseReference chungChiTNRef = requestRef.child("Cấp CN tốt nghiệp tạm thời");
        chungChiTNRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectSun.add(date + reason + state);
                    else subjectSun.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        List<String> subjectSpe = new ArrayList<>();
        DatabaseReference troCapXHRef = requestRef.child("Đề nghị hưởng trợ cấp xã hội");
        troCapXHRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String date = "Ngày tạo: " + dataSnapshot.child("currentDate").getValue(String.class);
                    String reason = "\nLý do: " + dataSnapshot.child("edtReason").getValue(String.class);
                    String state = "\nTrạng thái: " + dataSnapshot.child("state").getValue(String.class);
                    String reply = "\nLời nhắn từ QTV: " + dataSnapshot.child("reply").getValue(String.class);
                    String tmp = dataSnapshot.child("reply").getValue(String.class);
                    if(tmp=="") subjectSpe.add(date + reason + state);
                    else subjectSpe.add(date + reason + state + reply);
                    requestProcessingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        StateRequestList.put(RequestTypeList.get(0), subjectMon);
        StateRequestList.put(RequestTypeList.get(1), subjectTue);
        StateRequestList.put(RequestTypeList.get(2), subjectWed);
        StateRequestList.put(RequestTypeList.get(3), subjectThu);
        StateRequestList.put(RequestTypeList.get(4), subjectFri);
        StateRequestList.put(RequestTypeList.get(5), subjectSat);
        StateRequestList.put(RequestTypeList.get(6), subjectSun);
        StateRequestList.put(RequestTypeList.get(7), subjectSpe);
    }

    private void ClickButtonInRequestProcessing() {

        btnRequestAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBefore = getIntent();
                String studentId = intentBefore.getStringExtra("student_id").toString();
                Intent intent = new Intent(RequestProcessing.this, RequestAdd.class);
                intent.putExtra("student_id", studentId);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_activity_left_to_right_in_return, R.anim.anim_activity_left_to_right_out_return);
            }
        });
        btnRPHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_activity_slide_up_return, R.anim.anim_activity_slide_down_return);
            }
        });

        btnRPSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, Search.class));
                overridePendingTransition(R.anim.anim_activity_left_to_right_in, R.anim.anim_activity_left_to_right_out);
            }
        });

//        btnRPProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(RequestProcessing.this, Profile.class));
//            }
//        });
    }
    private void CreateAndShowInfoStudent() {
        TextView InfoProcessingRequest = findViewById(R.id.InfoProcessingRequest);
        Intent intentBefore = getIntent();
        String student_id_child = intentBefore.getStringExtra("student_id").toString();
        DatabaseReference reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
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