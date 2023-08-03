package com.example.j1studentconnect.tabsinmain;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.j1studentconnect.guide.StudyGuide;
import com.example.j1studentconnect.timetable.Calendar;
import com.example.j1studentconnect.studyresults.Grades;
import com.example.j1studentconnect.pomodoro.PomodoroActivity;
import com.example.j1studentconnect.R;
import com.example.j1studentconnect.request.RequestAdd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sahana.horizontalcalendar.HorizontalCalendar;
import com.sahana.horizontalcalendar.OnDateSelectListener;
import com.sahana.horizontalcalendar.model.DateModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import com.harrywhewell.scrolldatepicker.DayScrollDatePicker;
//import com.harrywhewell.scrolldatepicker.OnDateSelectedListener;


public class TabHome extends Fragment {
    View rootView;
    private ImageButton btnCalendar, btnCalendarHotkey, btnAvatar, btnX, btnRequest, btnGrades, btnGuide;
    private Button btnRecover;
    //private LinearLayout
    private CardView ConvenientCard;
    private TextView txtToday;
    private java.util.Calendar today = java.util.Calendar.getInstance();
    public static boolean recover = false;

    String SelectedDate;

    ArrayList<TimeTableInMain> arrayList;

    private RecyclerView lessonInDayListView;

    private FirebaseFirestore firebaseFirestore;

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


        //onViewCreated();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lessonInDayListView = view.findViewById(R.id.listViewHome);
        lessonInDayListView.setLayoutManager(new LinearLayoutManager(getContext()));
        CreateAndShowInfoStudent(view);
        ConstructLayout(view);
        ConstructButton(view);

        HorizontalCalendar mHorizontalCalendar;
        mHorizontalCalendar = view.findViewById(R.id.horizontalCalendar);
        //dayScrollDatePicker.setStartDate(29, 7, 2023);
        mHorizontalCalendar.setOnDateSelectListener(new OnDateSelectListener() {
            @Override
            public void onSelect(DateModel dateModel) {
                //mDateTextView.setText(dateModel != null ? dateModel.day + " " + dateModel.dayOfWeek + " " + dateModel.month + "," + dateModel.year : "");
                SelectedDate = dateModel.dayOfWeek;
                if (SelectedDate == "Mon")
                    SelectedDate = "Monday";
                else if (SelectedDate == "Tue")
                    SelectedDate = "Tuesday";
                else if (SelectedDate == "Wed")
                    SelectedDate = "Wednesday";
                else if (SelectedDate == "Thu")
                    SelectedDate = "Thursday";
                else if (SelectedDate == "Fri")
                    SelectedDate = "Friday";
                else if (SelectedDate == "Sat")
                    SelectedDate = "Saturday";
                setLessonInDay(SelectedDate);
                //Toast.makeText(getContext(), dateModel.dayOfWeek, Toast.LENGTH_SHORT).show();
            }
        });

//        arrayList = new ArrayList<>();
//        //arrayList.add(new TimeTableInMain("7:00 - 11:00", "Phát triển ứng dụng di động", "INT3120 50", "101-G2"));
//
//        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(arrayList);
//        lessonInDayListView.setAdapter(tbInHomeAdapter);

        ClickButton(view);
    }

    private void setLessonInDay(String Date) {

        arrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");
        collectionReference.document("Friday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        if (data != null) {
                            for (Map.Entry<String, Object> entry : data.entrySet()) {
                                String field = entry.getKey();
                                Object value = entry.getValue();

                                if (value instanceof ArrayList<?> || value instanceof List<?>) {
                                    List<String> subject1 = (List<String>) document.get(field);
                                    if (subject1 != null) {
//                                        arrayList.add(new TimeTableInMain(subject1.get(1) + '-' + subject1.get(2),
//                                                                          subject1.get(0) + "",
//                                                                        subject1.get(5) + "",
//                                                                        subject1.get(3) + ""));
                                        //Toast.makeText(getContext(), subject1.get(0), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });

        arrayList.add(new TimeTableInMain("7:00 - 11:00", "Phát triên ứng dụng di động", "INT3120 50", "101-G2"));

        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(arrayList);

        lessonInDayListView.setAdapter(tbInHomeAdapter);
        //tbInHomeAdapter.notifyDataSetChanged();

    }

    private void CreateAndShowInfoStudent(View view) {
        TextView Name = view.findViewById(R.id.name1);
        TextView student_id = view.findViewById(R.id.student_id_in_main1);
        TextView class_id = view.findViewById(R.id.class_id1);

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

    private void ConstructTextView(View view){

        //txtRecover = (TextView) findViewById(R.id.recover);
        txtToday = view.findViewById(R.id.today);

    }

    private void RenderToday(){
        String date;
        date = today.get(java.util.Calendar.DATE) + " Tháng " + (today.get(java.util.Calendar.MONTH) + 1) + ", " + today.get(java.util.Calendar.YEAR);
        txtToday.setText(date);
    }

    private void ConstructButton(View view){
        btnCalendar = view.findViewById(R.id.TimeTable);
        btnCalendarHotkey =  view.findViewById(R.id.calendarHotKey);
        btnAvatar = view.findViewById(R.id.dogAvt);
        btnX = view.findViewById(R.id.x);
        btnRequest = view.findViewById(R.id.request);
        btnGrades = view.findViewById(R.id.grades);
        btnGuide = view.findViewById(R.id.study_guide);
    }

    private void ConstructLayout(View view){

        ConvenientCard = view.findViewById(R.id.convenientNoti);
        btnRecover = view.findViewById(R.id.recover);
        if (recover == false) {

            ConstructTextView(view);
            RenderToday();
        }
        else {
            ConvenientCard.setVisibility(View.GONE);
            btnRecover.setVisibility(View.VISIBLE);
        }

    }

    private void ClickButton(View view){

//        btnAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), Profile.class));
//            }
//
//        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Calendar.class));
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), RequestAdd.class));
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), StudyGuide.class));
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnCalendarHotkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PomodoroActivity.class));
                // Thiết lập animation khi mở Activity mới
                getActivity().overridePendingTransition(R.anim.anim_pomodoro_in, R.anim.anim_pomodoro_out);

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
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

    }
}