package com.example.j1studentconnect;
//import State.*;

//import static com.example.j1studentconnect.CalendarUtils.daysInWeekArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnSearch, btnProfile, btnCalendar,
                        btnCalendarHotkey, btnAvatar, btnX, btnRequest, btnGrades;
    private Button btnRecover;
    //private LinearLayout
    private CardView ConvenientCard;
    private TextView txtToday;
    private java.util.Calendar today = java.util.Calendar.getInstance();
    public static boolean recover = false;

    private RecyclerView calendarRecyclerView;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstructLayout();
        CreateAndShowInfoStudent();
        ConstructButton();
//        initWidgets();
//        setWeekView();
        ClickButton();

    }

    private void CreateAndShowInfoStudent() {
        TextView Name = findViewById(R.id.name);
        TextView student_id = findViewById(R.id.student_id_in_main);
        TextView class_id = findViewById(R.id.class_id);

        Intent intentBefore = getIntent();
        String student_id_child = intentBefore.getStringExtra("student_id").toString();
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    Name.setText(snapshot.child("name").getValue().toString());
                    student_id.setText(" | "+ snapshot.child("student_id").getValue().toString());
                    class_id.setText(snapshot.child("student_class").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void ConstructTextView(){

        //txtRecover = (TextView) findViewById(R.id.recover);
        txtToday = findViewById(R.id.today);

    }

    private void RenderToday(){
        String date;
        date = today.get(java.util.Calendar.DATE) + " Tháng " + (today.get(java.util.Calendar.MONTH) + 1) + ", " + today.get(java.util.Calendar.YEAR);
        txtToday.setText(date);
    }

    private void ConstructButton(){

        btnSearch = findViewById(R.id.homeSearch);
        btnProfile = findViewById(R.id.homeProfile);
        btnCalendar = findViewById(R.id.TimeTable);
        btnCalendarHotkey =  findViewById(R.id.calendarHotKey);
        btnAvatar = findViewById(R.id.dogAvt);
        btnX = findViewById(R.id.x);
        btnRequest = findViewById(R.id.request);
        btnGrades = findViewById(R.id.grades);

    }

    private void ConstructLayout(){

        ConvenientCard = findViewById(R.id.convenientNoti);
        btnRecover = findViewById(R.id.recover);
        if (recover == false) {

            ConstructTextView();
            RenderToday();
        }
        else {
            ConvenientCard.setVisibility(View.GONE);
            btnRecover.setVisibility(View.VISIBLE);
        }

    }

    private void ClickButton(){

        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Calendar.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBefore = getIntent();
                String user_id = intentBefore.getStringExtra("student_id");
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("student_id", user_id);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RequestAdd.class));
            }
        });

        btnCalendarHotkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Calendar.class));
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (recover == false){
                    recover = true;
                    ConvenientCard.setVisibility(View.GONE);
                    btnRecover.setVisibility(View.VISIBLE);
                }
            }
        });

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvenientCard.setVisibility(View.VISIBLE);
                btnRecover.setVisibility(View.GONE);
                recover = false;
            }
        });

        btnGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Grades.class));
            }
        });

    }

//    //Weekly Calendar
//    private void initWidgets(){
//        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
//        //monthYearText = findViewById(R.id.monthYearTV);
//    }
//
//
//    private void setWeekView()
//    {
//        //monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
//        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);
//        CalendarAdapter calendarAdapter = new CalendarAdapter(days, (CalendarAdapter.OnItemListener) this);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
//        calendarRecyclerView.setLayoutManager(layoutManager);
//        calendarRecyclerView.setAdapter(calendarAdapter);
//        //setEventAdpater();
//    }
//
//    public void previousWeekAction(View view)
//    {
//        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
//        setWeekView();
//    }
//
//    public void nextWeekAction(View view)
//    {
//        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
//        setWeekView();
//    }


}