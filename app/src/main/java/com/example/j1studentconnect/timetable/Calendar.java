package com.example.j1studentconnect.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.searchtab.Search;
import com.example.j1studentconnect.tabsinmain.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Calendar extends AppCompatActivity {

    private ImageButton btnTBHome, btnTBSearch, btnTBProfile;
    private Spinner SpinnerSemester;
    TimeTableAdapter TimeTable;
    ExpandableListView expandableListView;
    List<String> dayList;
    HashMap<String, List<String>> subjectList;

    DatabaseReference reference;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_set);

        btnTBHome = (ImageButton) findViewById(R.id.TimeTableHome);
        btnTBSearch = (ImageButton) findViewById(R.id.TimeTableSearch);
        btnTBProfile = (ImageButton) findViewById(R.id.TimeTableProfile);
        //Choose semester
//        SpinnerSemester = (Spinner) findViewById(R.id.spinner_semester);
        // ExpandableListView

        expandableListView = findViewById(R.id.TimeTableList);
        //ConstructSpinner();
        showList();

        TimeTable = new TimeTableAdapter(this, dayList, subjectList);
        expandableListView.setAdapter(TimeTable);

        CreateAndShowInfoStudent();


        ClickButtonInCalen();

    }

    private void ConstructSpinner() {

//        String[] options = {"2022-2023"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
//        //Spinner spinner = findViewById(R.id.spinner_semester);
//        //spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                // Xử lý khi người dùng chọn một tùy chọn trong Spinner
//                String selectedOption = (String) adapterView.getItemAtPosition(position);
//
//                // Thực hiện các hành động tùy theo tùy chọn đã chọn
//                switch (selectedOption) {
//                    case "2022-2023":
//                        // Xử lý khi chọn Option 1
//                        expandableListView.removeAllViews(); // Xóa bảng hiện tại (nếu có)
//                        showList();
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });

        showList();

    }

    private void showList() {

        firebaseFirestore = FirebaseFirestore.getInstance();
        String student_id_child = "22026521";
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document(student_id_child).collection("semesterI");

        dayList = new ArrayList<String>();
        subjectList = new HashMap<String, List<String>>();

        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");

        List<String> subjectMon = new ArrayList<>();
        collectionReference.document("Monday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                            boolean check = false;
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();
                                check = true;
                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
                                        int cnt = 0;
                                        String Monday = "";
                                        for (String element : subject1) {
                                            //subjectTue.add()
                                            if (cnt == 1)
                                                Monday += element + '-';
                                            else Monday += element + '\n';
                                            ++cnt;

                                        }
                                        //subjectMon.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                                        subjectMon.add(Monday);
                                    }
                                }
                            }
                            if (!check)
                                subjectMon.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                        }
                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });


        List<String> subjectTue = new ArrayList<>();
        collectionReference.document("Tuesday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        boolean check = false;
                        if (data != null) {
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();
                                check = true;
                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
                                        int cnt = 0;
                                        String Tuesday = "";
                                        for (String element : subject1) {
                                            //subjectTue.add()
                                            if (cnt == 1)
                                                Tuesday += element + '-';
                                            else Tuesday += element + '\n';
                                            ++cnt;

                                        }
                                        subjectTue.add(Tuesday);
                                    }
                                }
                            }
                            if (!check)
                                subjectTue.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                        }

                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });


        List<String> subjectWed = new ArrayList<>();
        collectionReference.document("Wednesday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                            boolean check = false;
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();
                                check = true;
                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
                                        int cnt = 0;
                                        String Wednesday = "";
                                        for (String element : subject1) {
                                            //subjectTue.add()
                                            if (cnt == 1)
                                                Wednesday += element + '-';
                                            else Wednesday += element + '\n';
                                            ++cnt;

                                        }
                                        subjectWed.add(Wednesday);
                                    }
                                }
                            }
                            if (!check)
                                subjectWed.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                        }

                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });

        List<String> subjectThu = new ArrayList<>();
        collectionReference.document("Thursday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                            boolean check = false;
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();

                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
                                        int cnt = 0;
                                        String Thursday = "";
                                        check = true;
                                        for (String element : subject1) {
                                            //subjectTue.add()
                                            if (cnt == 1)
                                                Thursday += element + '-';
                                            else Thursday += element + '\n';
                                            ++cnt;

                                        }
                                        subjectThu.add(Thursday);
                                    }
                                }
                            }
                            if (!check)
                                subjectThu.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                        }

                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });


        List<String> subjectFri = new ArrayList<>();
        collectionReference.document("Friday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                            boolean check = false;
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();

                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
                                        int cnt = 0;
                                        String Friday = "";
                                        check = true;
                                        for (String element : subject1) {
                                            //subjectTue.add()
                                            if (cnt == 1)
                                                Friday += element + '-';
                                            else Friday += element + '\n';
                                            ++cnt;

                                        }
                                        subjectFri.add(Friday);
                                        //continue;
                                    }
                                }
                            }
                            if (!check)
                                subjectFri.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                        }

                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });


        List<String> subjectSat = new ArrayList<>();
        collectionReference.document("Saturday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                            boolean check = false;
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();

                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
                                        int cnt = 0;
                                        String Saturday = "";
                                        check = true;
                                        for (String element : subject1) {
                                            //subjectTue.add()
                                            if (cnt == 1)
                                                Saturday += element + '-';
                                            else Saturday += element + '\n';
                                            ++cnt;

                                        }
                                        subjectSat.add(Saturday);
                                    }
                                }
                            }
                            if (!check)
                                subjectSat.add("    Được nghỉ rồi nhé, nhớ ở nhà tự học đấy    ");
                        }

                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });


        subjectList.put(dayList.get(0), subjectMon);
        subjectList.put(dayList.get(1), subjectTue);
        subjectList.put(dayList.get(2), subjectWed);
        subjectList.put(dayList.get(3), subjectThu);
        subjectList.put(dayList.get(4), subjectFri);
        subjectList.put(dayList.get(5), subjectSat);
    }

    private void ClickButtonInCalen(){

        btnTBHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calendar.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_activity_slide_up_return,R.anim.anim_activity_slide_down_return);
            }
        });

        btnTBSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calendar.this, Search.class));
                overridePendingTransition(R.anim.anim_activity_left_to_right_in,R.anim.anim_activity_left_to_right_out);
            }
        });

//        btnTBProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Calendar.this, Profile.class));
//            }
//        });
    }

    private void CreateAndShowInfoStudent() {
        TextView InfoTBStudent = findViewById(R.id.InfoinTB);
        //Intent intentBefore = getActivity().getIntent();
        //String student_id_child = intentBefore.getStringExtra("student_id").toString();
        String student_id_child = "22026521";
        DatabaseReference reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    String strshow = "Họ tên SV: " + snapshot.child("name").getValue().toString() + "\nMSSV: " + snapshot.child("student_id").getValue().toString();
                    InfoTBStudent.setText(strshow);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}
