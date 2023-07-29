package com.example.j1studentconnect;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sahana.horizontalcalendar.HorizontalCalendar;
import com.sahana.horizontalcalendar.OnDateSelectListener;
import com.sahana.horizontalcalendar.model.DateModel;
//import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
//import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;

import java.util.Date;

public class TabHome extends Fragment {
    View rootView;
    private ImageButton btnCalendar, btnCalendarHotkey, btnAvatar, btnX, btnRequest, btnGrades;
    private Button btnRecover;
    //private LinearLayout
    private CardView ConvenientCard;
    private TextView txtToday;
    private java.util.Calendar today = java.util.Calendar.getInstance();
    public static boolean recover = false;

    String SelectedDate;

    private RecyclerView calendarRecyclerView;

    DatabaseReference reference;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TabHome() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TabHome newInstance(String param1, String param2) {
        TabHome fragment = new TabHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        CreateAndShowInfoStudent();
        ConstructLayout();
        ConstructButton();

//        initWidgets();
      //SetCalendar();
//        setWeekView();
        ClickButton();
        return rootView;
    }

    private void SetCalendar() {

        HorizontalCalendar mHorizontalCalendar;
        mHorizontalCalendar = rootView.findViewById(R.id.horizontalCalendar);
        //dayScrollDatePicker.setStartDate(29, 7, 2023);
        mHorizontalCalendar.setOnDateSelectListener(new OnDateSelectListener() {
            @Override
            public void onSelect(DateModel dateModel) {
                //mDateTextView.setText(dateModel != null ? dateModel.day + " " + dateModel.dayOfWeek + " " + dateModel.month + "," + dateModel.year : "");

            }
        });

    }

    private void CreateAndShowInfoStudent() {
        TextView Name = rootView.findViewById(R.id.name1);
        TextView student_id = rootView.findViewById(R.id.student_id_in_main1);
        TextView class_id = rootView.findViewById(R.id.class_id1);

        //Intent intentBefore = getActivity().getIntent();
        //String student_id_child = intentBefore.getStringExtra("student_id").toString();
        String student_id_child = "22026521";
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
        txtToday = rootView.findViewById(R.id.today);

    }

    private void RenderToday(){
        String date;
        date = today.get(java.util.Calendar.DATE) + " Tháng " + (today.get(java.util.Calendar.MONTH) + 1) + ", " + today.get(java.util.Calendar.YEAR);
        txtToday.setText(date);
    }

    private void ConstructButton(){
        btnCalendar = rootView.findViewById(R.id.TimeTable);
        btnCalendarHotkey =  rootView.findViewById(R.id.calendarHotKey);
        btnAvatar = rootView.findViewById(R.id.dogAvt);
        btnX = rootView.findViewById(R.id.x);
        btnRequest = rootView.findViewById(R.id.request);
        btnGrades = rootView.findViewById(R.id.grades);
    }

    private void ConstructLayout(){

        ConvenientCard = rootView.findViewById(R.id.convenientNoti);
        btnRecover = rootView.findViewById(R.id.recover);
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

//        btnAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), Profile.class));
//            }
//        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Calendar.class));
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), RequestAdd.class));
            }
        });

        btnCalendarHotkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PomodoroActivity.class));
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
                startActivity(new Intent(getActivity(), Grades.class));
            }
        });

    }
}