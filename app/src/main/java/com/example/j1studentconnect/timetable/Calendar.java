package com.example.j1studentconnect.timetable;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        SpinnerSemester = (Spinner) findViewById(R.id.spinner_semester);
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

        String[] options = {"2022-2023"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        Spinner spinner = findViewById(R.id.spinner_semester);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Xử lý khi người dùng chọn một tùy chọn trong Spinner
                String selectedOption = (String) adapterView.getItemAtPosition(position);

                // Thực hiện các hành động tùy theo tùy chọn đã chọn
                switch (selectedOption) {
                    case "2022-2023":
                        // Xử lý khi chọn Option 1
                        expandableListView.removeAllViews(); // Xóa bảng hiện tại (nếu có)
                        showList();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }

    private void showList() {

        firebaseFirestore = FirebaseFirestore.getInstance();
        String student_id_child = "21020074";
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        DocumentReference documentReference = firebaseFirestore.collection("subjectsII").document();

        dayList = new ArrayList<String>();
        subjectList = new HashMap<String, List<String>>();

        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");

        List<String> subjectMon = new ArrayList<>();
        subjectMon.add("DSA");
        subjectMon.add("Mobile");

        List<String> subjectTue = new ArrayList<>();
        subjectTue.add("DSA");
        subjectTue.add("Mobile");

        List<String> subjectWed = new ArrayList<>();
        subjectWed.add("DSA");
        subjectWed.add("Mobile");

        List<String> subjectThu = new ArrayList<>();
        subjectThu.add("DSA");
        subjectThu.add("Mobile");

        List<String> subjectFri = new ArrayList<>();
        subjectFri.add("DSA");
        subjectFri.add("Mobile");

        List<String> subjectSat = new ArrayList<>();
        subjectSat.add("DSA");
        subjectSat.add("Mobile");

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
            }
        });

        btnTBSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calendar.this, Search.class));
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
