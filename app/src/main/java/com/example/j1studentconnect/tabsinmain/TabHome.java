package com.example.j1studentconnect.tabsinmain;

import static android.view.View.GONE;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sahana.horizontalcalendar.HorizontalCalendar;
import com.sahana.horizontalcalendar.OnDateSelectListener;
import com.sahana.horizontalcalendar.model.DateModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class TabHome extends Fragment {
    View rootView;
    private ImageButton btnCalendar, btnCalendarHotkey, btnX, btnRequest, btnGrades, btnGuide;
    private Button btnRecover;
    private CircleImageView homeAvt;
    private CardView ConvenientCard;
    private TextView txtToday;
    private java.util.Calendar today = java.util.Calendar.getInstance();
    public static boolean recover = false;
    public boolean onCreateArrrayList = false;
    AnimatorSet scaleUp, scaleDown;
    String student_id_childd, homeAvtURL;
    String SelectedDate;
    private RecyclerView lessonInDayListView;
    private FirebaseFirestore firebaseFirestore;
    List<TimeTableInMain> arrayListMon, arrayListTue, arrayListWed, arrayListThu, arrayListFri, arrayListSat;
    private boolean setinMon = false, setinTue = false, setinWed = false, setinThu = false, setinFri = false, setinSat = false;
    DatabaseReference reference, referencee;
    String student_id_child;
    Animator pressedButton;
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
        scaleDown = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.anim.button_scale_down);
        scaleUp = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.anim.button_scale_up);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        Intent intentBefore = getActivity().getIntent();
        if(intentBefore.getStringExtra("student_id") != null) {
            student_id_child = intentBefore.getStringExtra("student_id").toString();
        }
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
        arrayListMon = new ArrayList<>();
        arrayListTue = new ArrayList<>();
        arrayListWed = new ArrayList<>();
        arrayListThu = new ArrayList<>();
        arrayListFri = new ArrayList<>();
        arrayListSat = new ArrayList<>();

        mHorizontalCalendar.setOnDateSelectListener(new OnDateSelectListener() {
            @Override
            public void onSelect(DateModel dateModel) {
                SelectedDate = dateModel.dayOfWeek;
                if (SelectedDate.equals("Mon")) {
                    setLessonInMon();
                }
                else if (SelectedDate.equals("Tue")) {
                    setLessonInTue();
                }
                else if (SelectedDate.equals("Wed")) {
                    setLessonInWed();
                }
                else if (SelectedDate.equals("Thu")){
                    setLessonInThu();
                }
                else if (SelectedDate.equals("Fri")) {
                    setLessonInFri();
                }
                else if (SelectedDate.equals("Sat")) {
                    setLessonInSat();
                }
                else if (SelectedDate.equals("Sun"))
                    setLessonInSun();
                Log.d("fff", SelectedDate);
            }
        });
        ClickButton(view);
    }

    private void setLessonInSun() {
        arrayListSat.clear();
        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListSat);
        tbInHomeAdapter.notifyDataSetChanged();
        lessonInDayListView.setAdapter(tbInHomeAdapter);
    }

    private void setLessonInSat() {
        //arrayListSat = new ArrayList<>();
        arrayListSat.clear();
        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListSat);

        lessonInDayListView.setAdapter(tbInHomeAdapter);
        tbInHomeAdapter.notifyDataSetChanged();
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");
        collectionReference.document("Saturday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
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
                                        String timeLesson = subject1.get(1) + " - " + subject1.get(2);
                                        String nameOfLesson = subject1.get(0);
                                        String IdOfLesson = subject1.get(5);
                                        String placeForLesson = subject1.get(3);
                                        arrayListSat.add(new TimeTableInMain(timeLesson,
                                                nameOfLesson,
                                                IdOfLesson,
                                                placeForLesson));
                                        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListSat);
                                        lessonInDayListView.setAdapter(tbInHomeAdapter);
                                        tbInHomeAdapter.notifyDataSetChanged();
                                        //}

                                        //arrayList.add(new TimeTableInMain("7:00 - 11:00", "Phát triên ứng dụng di động", "INT3120 50", "101-G2"));

                                        //Toast.makeText(getContext(), timeLesson + nameOfLesson + IdOfLesson + placeForLesson, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });

    }

    private void setLessonInFri() {
        arrayListFri.clear();
        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListFri);
        tbInHomeAdapter.notifyDataSetChanged();
        lessonInDayListView.setAdapter(tbInHomeAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");

        collectionReference.document("Friday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
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
                                        String timeLesson = subject1.get(1) + " - " + subject1.get(2);
                                        String nameOfLesson = subject1.get(0);
                                        String IdOfLesson = subject1.get(5);
                                        String placeForLesson = subject1.get(3);
                                        arrayListFri.add(new TimeTableInMain(timeLesson,
                                                nameOfLesson,
                                                IdOfLesson,
                                                placeForLesson));
                                        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListFri);
                                        lessonInDayListView.setAdapter(tbInHomeAdapter);
                                        tbInHomeAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });
    }

    private void setLessonInThu() {
        arrayListThu.clear();
        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListThu);
        tbInHomeAdapter.notifyDataSetChanged();
        lessonInDayListView.setAdapter(tbInHomeAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");

        collectionReference.document("Thursday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
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
                                        String timeLesson = subject1.get(1) + " - " + subject1.get(2);
                                        String nameOfLesson = subject1.get(0);
                                        String IdOfLesson = subject1.get(5);
                                        String placeForLesson = subject1.get(3);
                                        arrayListThu.add(new TimeTableInMain(timeLesson,
                                                nameOfLesson,
                                                IdOfLesson,
                                                placeForLesson));
                                        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListThu);
                                        lessonInDayListView.setAdapter(tbInHomeAdapter);
                                        tbInHomeAdapter.notifyDataSetChanged();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });
    }

    private void setLessonInWed() {

        //arrayListWed = new ArrayList<>();
        arrayListWed.clear();
        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListWed);
        tbInHomeAdapter.notifyDataSetChanged();
        lessonInDayListView.setAdapter(tbInHomeAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");

        collectionReference.document("Wednesday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
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
                                        String timeLesson = subject1.get(1) + " - " + subject1.get(2);
                                        String nameOfLesson = subject1.get(0);
                                        String IdOfLesson = subject1.get(5);
                                        String placeForLesson = subject1.get(3);
                                        arrayListWed.add(new TimeTableInMain(timeLesson,
                                                nameOfLesson,
                                                IdOfLesson,
                                                placeForLesson));
                                        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListWed);
                                        lessonInDayListView.setAdapter(tbInHomeAdapter);
                                        tbInHomeAdapter.notifyDataSetChanged();
                                        //arrayList.add(new TimeTableInMain("7:00 - 11:00", "Phát triên ứng dụng di động", "INT3120 50", "101-G2"));

                                        //Toast.makeText(getContext(), timeLesson + nameOfLesson + IdOfLesson + placeForLesson, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });
        //}
    }

    private void setLessonInTue() {
        //arrayListTue = new ArrayList<>();
        arrayListTue.clear();
        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListTue);
        tbInHomeAdapter.notifyDataSetChanged();
        lessonInDayListView.setAdapter(tbInHomeAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");
        collectionReference.document("Tuesday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
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
                                        String timeLesson = subject1.get(1) + " - " + subject1.get(2);
                                        String nameOfLesson = subject1.get(0);
                                        String IdOfLesson = subject1.get(5);
                                        String placeForLesson = subject1.get(3);
                                        arrayListTue.add(new TimeTableInMain(timeLesson,
                                                nameOfLesson,
                                                IdOfLesson,
                                                placeForLesson));
                                        TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListTue);
                                        lessonInDayListView.setAdapter(tbInHomeAdapter);
                                        tbInHomeAdapter.notifyDataSetChanged();
                                        //arrayList.add(new TimeTableInMain("7:00 - 11:00", "Phát triên ứng dụng di động", "INT3120 50", "101-G2"));

                                        //Toast.makeText(getContext(), timeLesson + nameOfLesson + IdOfLesson + placeForLesson, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Log.w("Error getting document.", task.getException());
                }
            }
        });
        //}

    }

    private void setLessonInMon() {
        //String timeLesson, nameOfLesson, IdOfLesson, placeForLesson;
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("timetable").document("22026521").collection("semesterI");

            //arrayListMon = new ArrayList<>();
            arrayListMon.clear();
            TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListMon);
            tbInHomeAdapter.notifyDataSetChanged();
            lessonInDayListView.setAdapter(tbInHomeAdapter);
            collectionReference.document("Monday").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
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
                                                String timeLesson = subject1.get(1) + " - " + subject1.get(2);
                                                String nameOfLesson = subject1.get(0);
                                                String IdOfLesson = subject1.get(5);
                                                String placeForLesson = subject1.get(3);
                                                arrayListMon.add(new TimeTableInMain(timeLesson,
                                                        nameOfLesson,
                                                        IdOfLesson,
                                                        placeForLesson));
                                                TBInHomeAdapter tbInHomeAdapter = new TBInHomeAdapter(getContext(), arrayListMon);
                                                tbInHomeAdapter.notifyDataSetChanged();
                                                lessonInDayListView.setAdapter(tbInHomeAdapter);
                                        }
                                    }

                                }
                            }
                        }
                    } else {
                        Log.w("Error getting document.", task.getException());
                    }
                }
            });


    }

    private void CreateAndShowInfoStudent(View view) {
        TextView Name = view.findViewById(R.id.name1);
        TextView student_id = view.findViewById(R.id.student_id_in_main1);

        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    Name.setText(snapshot.child("name").getValue().toString());
                    student_id.setText(snapshot.child("student_id").getValue().toString());
                    if (snapshot.hasChild("imageURL")) {
                        homeAvtURL = snapshot.child("imageURL").getValue(String.class);
                        Picasso.get().load(homeAvtURL).into(homeAvt);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void ConstructTextView(View view){
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
        btnX = view.findViewById(R.id.x);
        btnRequest = view.findViewById(R.id.request);
        btnGrades = view.findViewById(R.id.grades);
        btnGuide = view.findViewById(R.id.study_guide);
        homeAvt = view.findViewById(R.id.home_avt);
    }

    private void ConstructLayout(View view){

        ConvenientCard = view.findViewById(R.id.convenientNoti);
        btnRecover = view.findViewById(R.id.recover);
        if (recover == false) {

            ConstructTextView(view);
            RenderToday();
        }
        else {
            ConvenientCard.setVisibility(GONE);
            btnRecover.setVisibility(View.VISIBLE);
        }

    }

    private void ClickButton(View view){
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Calendar.class);
                intent.putExtra("student_id", student_id_child);
                startActivity(intent);
                btnCalendar.setBackgroundResource(R.drawable.clicked_bg_button);
                scaleDown.setTarget(btnCalendar);
                scaleDown.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnCalendar.setBackgroundResource(R.drawable.bg_button);
                    }
                }, 250);
                scaleUp.setTarget(btnCalendar);
                scaleUp.setStartDelay(200);
                scaleUp.start();
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RequestAdd.class);
                intent.putExtra("student_id", student_id_child);
                startActivity(intent);
                btnRequest.setBackgroundResource(R.drawable.clicked_bg_button);
                scaleDown.setTarget(btnRequest);
                scaleDown.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnRequest.setBackgroundResource(R.drawable.bg_button);
                    }
                }, 250);
                scaleUp.setTarget(btnRequest);
                scaleUp.setStartDelay(200);
                scaleUp.start();
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudyGuide.class);
                intent.putExtra("student_id", student_id_child);
                btnGuide.setBackgroundResource(R.drawable.clicked_bg_button);
                scaleDown.setTarget(btnGuide);
                scaleDown.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnGuide.setBackgroundResource(R.drawable.bg_button);
                    }
                }, 250);
                scaleUp.setTarget(btnGuide);
                scaleUp.setStartDelay(200);
                scaleUp.start();
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Grades.class);
                intent.putExtra("student_id", student_id_child);
                btnGrades.setBackgroundResource(R.drawable.clicked_bg_button);
                scaleDown.setTarget(btnGrades);
                scaleDown.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnGrades.setBackgroundResource(R.drawable.bg_button);
                    }
                }, 250);
                scaleUp.setTarget(btnGrades);
                scaleUp.setStartDelay(200);
                scaleUp.start();
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_acitivity_slide_down, R.anim.anim_activity_slide_up);
            }
        });

        btnCalendarHotkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PomodoroActivity.class);
                intent.putExtra("student_id", student_id_child);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_pomodoro_in, R.anim.anim_pomodoro_out);

            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recover == false){
                    recover = true;
                    ConvenientCard.setVisibility(GONE);
                    btnRecover.setVisibility(View.VISIBLE);
                }
            }
        });

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvenientCard.setVisibility(View.VISIBLE);
                btnRecover.setVisibility(GONE);
                recover = false;
            }
        });
    }
}