package com.example.j1studentconnect.studyresults;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.request.RequestAdd;
import com.example.j1studentconnect.searchtab.Search;
import com.example.j1studentconnect.tabsinmain.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Grades extends AppCompatActivity {

    private TextView gradesStuInf, gradeSemester, gradeGeneral;
    Integer user_id;
    String check_option, HKoption1, HKoption2;
    DatabaseReference reference;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_search);

        bottomNavigationView = findViewById(R.id.tab_menu);
        Intent intentBefore = getIntent();
        String student_id_child = intentBefore.getStringExtra("student_id").toString();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.tab_home) {
                    Intent intent = new Intent(Grades.this, MainActivity.class);
                    intent.putExtra("signal", "0");
                    intent.putExtra("student_id", student_id_child);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.tab_search) {
                    Intent intent = new Intent(Grades.this, MainActivity.class);
                    intent.putExtra("signal", "1");
                    intent.putExtra("student_id", student_id_child);
                    startActivity(intent);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.tab_profile) {
                    Intent intent = new Intent(Grades.this, MainActivity.class);
                    intent.putExtra("signal", "2");
                    intent.putExtra("student_id", student_id_child);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });
        ConstructGradesSpinner();
        CreateAndShowInfoStudent();
    }

    private void ConstructGradesSpinner() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonth().getValue();
        int currentYear = currentDate.getYear();
        if(currentMonth>=5 && currentMonth<=12) {
            HKoption1 = "Học kì I " + String.valueOf(currentYear-1) + " - " + String.valueOf(currentYear);
            HKoption2 = "Học kì II " + String.valueOf(currentYear-1) + " - " + String.valueOf(currentYear);
        } else {
            HKoption1 = "Học kì II " + String.valueOf(currentYear-2) + " - " + String.valueOf(currentYear-1);
            HKoption2 = "Học kì I " + String.valueOf(currentYear-1) + " - " + String.valueOf(currentYear);
        }

        String[] options = {HKoption1, HKoption2, "Toàn khóa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        Spinner spinner = findViewById(R.id.selectGrades);
        TableLayout tableLayout = findViewById(R.id.gradesTable);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = (String) adapterView.getItemAtPosition(position);
                if (selectedOption.equals(HKoption1)) {
                    check_option = HKoption1;
                    ShowGrades();
                } else if (selectedOption.equals(HKoption2)) {
                    check_option = HKoption2;
                    ShowGrades();
                } else if (selectedOption.equals("Toàn khóa")) {
                    check_option = "Toàn khóa";
                    ShowGrades();
                }
            }

            private void ShowGrades() {
                tableLayout.removeAllViews(); // Xóa bảng hiện tại (nếu có)
                reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("subjects");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tableLayout.removeAllViews(); // Xóa bảng hiện tại (nếu có)

                        int stt = 1;
                        Double portal_semester = 0.00;
                        Double gpa_semester = 0.00;
                        Double portal_total = 0.00;
                        Double gpa_total = 0.00;
                        List<TableRow> rowsToShow = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Integer student_id = snapshot.child("student_id").getValue(Integer.class);
                            String name = snapshot.child("subject_name").getValue(String.class);
                            Double portal = snapshot.child("portal_number").getValue(Double.class);
                            Double gpa = snapshot.child("gpa").getValue(Double.class);
                            String score;
                            if (gpa.equals(4.0)) {
                                score = "A+";
                            } else if (gpa.equals(3.7)) {
                                score = "A";
                            } else if (gpa.equals(3.5)) {
                                score = "B+";
                            } else if (gpa.equals(3.0)) {
                                score = "B";
                            } else if (gpa.equals(2.5)) {
                                score = "C+";
                            } else if (gpa.equals(2.0)) {
                                score = "C";
                            } else if (gpa.equals(1.5)) {
                                score = "D+";
                            } else if (gpa.equals(1.0)) {
                                score = "D";
                            } else {
                                score = "F";
                            }
                            String semester = snapshot.child("semester_id").getValue(String.class);
                            if (check_option==HKoption1 || check_option==HKoption2) {
                                if (student_id != null && student_id.equals(user_id)) {
                                    if (semester.equals(check_option)) {
                                        TableRow row = new TableRow(getApplicationContext());

                                        // Tạo các TextView chứa dữ liệu và thêm vào TableRow
                                        TextView textViewColumn1 = new TextView(getApplicationContext());
                                        textViewColumn1.setText(String.valueOf(stt));
                                        textViewColumn1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.1f));
                                        textViewColumn1.setGravity(Gravity.CENTER_HORIZONTAL);
                                        textViewColumn1.setBackgroundResource(R.drawable.cell1);
                                        row.addView(textViewColumn1);

                                        TextView textViewColumn2 = new TextView(getApplicationContext());
                                        textViewColumn2.setText(name);
                                        textViewColumn2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.35f));
                                        textViewColumn2.setGravity(Gravity.CENTER_HORIZONTAL);
                                        textViewColumn2.setBackgroundResource(R.drawable.cell2);
                                        row.addView(textViewColumn2);

                                        TextView textViewColumn3 = new TextView(getApplicationContext());
                                        textViewColumn3.setText(String.format("%.0f", portal));
                                        textViewColumn3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.2f));
                                        textViewColumn3.setGravity(Gravity.CENTER_HORIZONTAL);
                                        textViewColumn3.setBackgroundResource(R.drawable.cell3);
                                        row.addView(textViewColumn3);

                                        TextView textViewColumn4 = new TextView(getApplicationContext());
                                        textViewColumn4.setText(score);
                                        textViewColumn4.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.2f));
                                        textViewColumn4.setGravity(Gravity.CENTER_HORIZONTAL);
                                        textViewColumn4.setBackgroundResource(R.drawable.cell4);
                                        row.addView(textViewColumn4);

                                        TextView textViewColumn5 = new TextView(getApplicationContext());
                                        textViewColumn5.setText(String.valueOf(gpa));
                                        textViewColumn5.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.15f));
                                        textViewColumn5.setGravity(Gravity.CENTER_HORIZONTAL);
                                        textViewColumn5.setBackgroundResource(R.drawable.cell5);
                                        row.addView(textViewColumn5);

                                        rowsToShow.add(row);
                                        stt++;
                                        portal_semester += portal;
                                        gpa_semester += gpa * portal;

                                        portal_total += portal;
                                        gpa_total += gpa * portal;
                                    } else {
                                        portal_total += portal;
                                        gpa_total += gpa * portal;
                                    }
                                }
                            } else if (check_option == "Toàn khóa") {
                                if (student_id != null && student_id.equals(user_id)) {
                                    TableRow row = new TableRow(getApplicationContext());

                                    // Tạo các TextView chứa dữ liệu và thêm vào TableRow
                                    TextView textViewColumn1 = new TextView(getApplicationContext());
                                    textViewColumn1.setText(String.valueOf(stt));
                                    textViewColumn1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.1f));
                                    textViewColumn1.setGravity(Gravity.CENTER_HORIZONTAL);
                                    textViewColumn1.setBackgroundResource(R.drawable.cell1);
                                    row.addView(textViewColumn1);

                                    TextView textViewColumn2 = new TextView(getApplicationContext());
                                    textViewColumn2.setText(name);
                                    textViewColumn2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.35f));
                                    textViewColumn2.setGravity(Gravity.CENTER_HORIZONTAL);
                                    textViewColumn2.setBackgroundResource(R.drawable.cell2);
                                    row.addView(textViewColumn2);

                                    TextView textViewColumn3 = new TextView(getApplicationContext());
                                    textViewColumn3.setText(String.format("%.0f", portal));
                                    textViewColumn3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.2f));
                                    textViewColumn3.setGravity(Gravity.CENTER_HORIZONTAL);
                                    textViewColumn3.setBackgroundResource(R.drawable.cell3);
                                    row.addView(textViewColumn3);

                                    TextView textViewColumn4 = new TextView(getApplicationContext());
                                    textViewColumn4.setText(score);
                                    textViewColumn4.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.2f));
                                    textViewColumn4.setGravity(Gravity.CENTER_HORIZONTAL);
                                    textViewColumn4.setBackgroundResource(R.drawable.cell4);
                                    row.addView(textViewColumn4);

                                    TextView textViewColumn5 = new TextView(getApplicationContext());
                                    textViewColumn5.setText(String.valueOf(gpa));
                                    textViewColumn5.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.15f));
                                    textViewColumn5.setGravity(Gravity.CENTER_HORIZONTAL);
                                    textViewColumn5.setBackgroundResource(R.drawable.cell5);
                                    row.addView(textViewColumn5);

                                    rowsToShow.add(row);
                                    stt++;
                                    portal_total += portal;
                                    gpa_total += gpa * portal;
                                }
                            }
                        }

                        if (portal_semester != 0) {
                            gpa_semester /= portal_semester;
                        } else {
                            gpa_semester = 0.00;
                        }

                        if (portal_total != 0) {
                            gpa_total /= portal_total;
                        } else {
                            gpa_total = 0.00;
                        }

                        if (check_option==HKoption1 || check_option==HKoption2) {
                            gradeSemester = findViewById(R.id.grade_semester);
                            gradeSemester.setText("Tổng tín chỉ: " + String.format("%.0f", portal_semester) + "\nĐiểm trung bình hệ 4: " + String.format("%.2f", gpa_semester));

                            gradeGeneral = findViewById(R.id.grade_general);
                            gradeGeneral.setText("Tổng tín chỉ tích lũy: " + String.format("%.0f", portal_total) + "\nĐiểm trung bình tích lũy hệ 4: " + String.format("%.2f", gpa_total));
                        } else if (check_option=="Toàn khóa") {
                            gradeSemester = findViewById(R.id.grade_general);
                            String str = "Xếp loại học lực\n";
                            if (gpa_total>=3.6 && gpa_total<=4.0) {
                                str += "Xuất sắc";
                            } else if (gpa_total>=3.2 && gpa_total<=3.6) {
                                str += "Giỏi";
                            } else if (gpa_total>=2.5 && gpa_total<=3.2) {
                                str += "Khá";
                            } else if (gpa_total>=2.0 && gpa_total<=2.5) {
                                str += "Trung bình";
                            } else if (gpa_total>=1.0 && gpa_total<=2.0) {
                                str += "Yếu";
                            } else if (gpa_total>=0.0 && gpa_total<=1.0) {
                                str += "Kém";
                            }
                            gradeSemester.setText(str);

                            gradeGeneral = findViewById(R.id.grade_semester);
                            gradeGeneral.setText("Tổng tín chỉ tích lũy: " + String.format("%.0f", portal_total) + "\nĐiểm trung bình tích lũy hệ 4: " + String.format("%.2f", gpa_total));
                        }

                        for (TableRow row : rowsToShow) {
                            tableLayout.addView(row);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }

    private void CreateAndShowInfoStudent() {
        gradesStuInf = findViewById(R.id.gradesStuInf);

        Intent intent = getIntent();
        String student_id_child = intent.getStringExtra("student_id").toString();
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    user_id = snapshot.child("student_id").getValue(Integer.class);
                    String strshow = "Họ tên SV: " + snapshot.child("name").getValue().toString() + "\nMSSV: " + user_id.toString();
                    gradesStuInf.setText(strshow);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
