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

import java.util.ArrayList;
import java.util.List;

public class Grades extends AppCompatActivity {

    private TextView gradesStuInf;
    Integer user_id;
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
        String[] options = {"2021-2022", "2022-2023"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        Spinner spinner = findViewById(R.id.selectGrades);
        TableLayout tableLayout = findViewById(R.id.gradesTable);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Xử lý khi người dùng chọn một tùy chọn trong Spinner
                String selectedOption = (String) adapterView.getItemAtPosition(position);

                // Thực hiện các hành động tùy theo tùy chọn đã chọn
                switch (selectedOption) {
                    case "2021-2022":
                        // Xử lý khi chọn Option 1
                        tableLayout.removeAllViews(); // Xóa bảng hiện tại (nếu có)
                        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("results");
                        // Đọc dữ liệu từ Firebase
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                // Xóa hết các hàng dữ liệu cũ trước khi hiển thị dữ liệu mới
                                tableLayout.removeAllViews();

                                int stt = 1;
                                List<TableRow> rowsToShow = new ArrayList<>();

                                // Duyệt qua các dữ liệu trong dataSnapshot
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    // Lấy giá trị từ mỗi dữ liệu trong Firebase
                                    Integer student_id = snapshot.child("student_id").getValue(Integer.class);
                                    String name = snapshot.child("class_id").getValue(String.class);
                                    String score = snapshot.child("class_id2").getValue(String.class);
                                    if (student_id != null && student_id.equals(user_id)) {
                                        // Tạo một TableRow mới
                                        TableRow row = new TableRow(getApplicationContext());

                                        // Tạo các TextView chứa dữ liệu và thêm vào TableRow
                                        TextView textViewColumn1 = new TextView(getApplicationContext());
                                        textViewColumn1.setText(String.valueOf(stt));
                                        textViewColumn1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f));
                                        textViewColumn1.setGravity(Gravity.CENTER_HORIZONTAL);
                                        row.addView(textViewColumn1);

                                        TextView textViewColumn2 = new TextView(getApplicationContext());
                                        textViewColumn2.setText(name);
                                        textViewColumn2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.35f));
                                        textViewColumn2.setGravity(Gravity.CENTER_HORIZONTAL);
                                        row.addView(textViewColumn2);

                                        TextView textViewColumn3 = new TextView(getApplicationContext());
                                        textViewColumn3.setText("3");
                                        textViewColumn3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                                        textViewColumn3.setGravity(Gravity.CENTER_HORIZONTAL);
                                        row.addView(textViewColumn3);

                                        TextView textViewColumn4 = new TextView(getApplicationContext());
                                        textViewColumn4.setText(score);
                                        textViewColumn4.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                                        textViewColumn4.setGravity(Gravity.CENTER_HORIZONTAL);
                                        row.addView(textViewColumn4);

                                        TextView textViewColumn5 = new TextView(getApplicationContext());
                                        textViewColumn5.setText("aaa");
                                        textViewColumn5.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.15f));
                                        textViewColumn5.setGravity(Gravity.CENTER_HORIZONTAL);
                                        row.addView(textViewColumn5);

                                        rowsToShow.add(row);
                                        stt++;
                                    }
                                }
                                for (TableRow row : rowsToShow) {
                                    tableLayout.addView(row);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                // Xử lý khi có lỗi xảy ra
                            }
                        });

                        // Tạo các hàng và cột cho bảng tương ứng với Option 1
                        break;
                    case "2022-2023":
                        // Xử lý khi chọn Option 2
                        tableLayout.removeAllViews(); // Xóa bảng hiện tại (nếu có)
                        // Tạo các hàng và cột cho bảng tương ứng với Option 2
                        for (int i = 0; i < 10; i++) {
                            TableRow row = new TableRow(getApplicationContext());

                            // Thêm các TextView vào TableRow để hiển thị dữ liệu trong các cột
                            TextView textViewColumn1 = new TextView(getApplicationContext());
                            textViewColumn1.setText("1");
                            textViewColumn1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.1f));
                            textViewColumn1.setGravity(Gravity.CENTER_HORIZONTAL);
                            row.addView(textViewColumn1);

                            TextView textViewColumn2 = new TextView(getApplicationContext());
                            textViewColumn2.setText("Phát triển ứng dụng di động");
                            textViewColumn2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.35f));
                            textViewColumn2.setGravity(Gravity.CENTER_HORIZONTAL);
                            row.addView(textViewColumn2);

                            TextView textViewColumn3 = new TextView(getApplicationContext());
                            textViewColumn3.setText("zzz");
                            textViewColumn3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                            textViewColumn3.setGravity(Gravity.CENTER_HORIZONTAL);
                            row.addView(textViewColumn3);

                            TextView textViewColumn4 = new TextView(getApplicationContext());
                            textViewColumn4.setText("aaa");
                            textViewColumn4.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
                            textViewColumn4.setGravity(Gravity.CENTER_HORIZONTAL);
                            row.addView(textViewColumn4);

                            TextView textViewColumn5 = new TextView(getApplicationContext());
                            textViewColumn5.setText("aaa");
                            textViewColumn5.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.15f));
                            textViewColumn5.setGravity(Gravity.CENTER_HORIZONTAL);
                            row.addView(textViewColumn5);

                            // Thêm TableRow vào TableLayout
                            tableLayout.addView(row);
                        }
                        break;
                }
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
