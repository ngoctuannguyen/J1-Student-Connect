package com.example.j1studentconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ExpandedMenuView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestAdd extends AppCompatActivity {
    private Spinner spinner1;
    private Button attachButton;
    private static final int PICKFILE_REQUEST_CODE = 1;
    private String path;

    private TextView InfoAddRequest;
    private LinearLayout btnRequestProcessing;
    private ImageButton btnRHome, btnRSearch, btnRProfile;

    private CardView cardResults, cardPostpone, cardReview, cardStudentRequest, cardBusRequest, cardStopLearning, cardDegree;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_request);
        //attachButton = findViewById(R.id.attach_button);

        btnRHome = (ImageButton) findViewById(R.id.RequestHome);
        btnRSearch = (ImageButton) findViewById(R.id.RequestSearch);
        btnRProfile = (ImageButton) findViewById(R.id.RequestProfile);

        ConstructButton();
        ClickButtonInRequest();
        ConstructCardButton();
        //initifinal();
        //addListenerOnButton();
        //addListenerOnSpinnerItemSelection();
        //addListenerOnButton();
        addClickOnCardRequest();
        CreateAndShowInfoStudent();
    }

    private void ConstructCardButton() {
        cardResults = findViewById(R.id.card_study_results);
        cardPostpone = findViewById(R.id.card_postpone);
        cardReview = findViewById(R.id.card_review);
        cardStudentRequest = findViewById(R.id.card_student_card);
        cardBusRequest = findViewById(R.id.card_bus_request);
        cardStopLearning = findViewById(R.id.card_stop_learning);
        cardDegree = findViewById(R.id.card_degree_request);
    }

    private void addClickOnCardRequest() {
        cardResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 1);
            }
        });

        cardPostpone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 2);
            }
        });

        cardReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 3);
            }
        });
        cardStudentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 4);
            }
        });

        cardBusRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 5);
            }
        });

        cardStopLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 6);
            }
        });

        cardDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rqAdd, new FragmentStudyResultsRequest()).commit();
                openRequest(Gravity.CENTER, 7);
            }
        });

    }

    private void openRequest(int gravity, int type) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_request);

        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

//        if (Gravity.BOTTOM == gravity)
//            dialog.setCancelable(true);
//        else dialog.setCancelable(false);

        TextView txtTitleOfDialog = dialog.findViewById(R.id.dialog_title);
        switch (type){

            case 1:
                txtTitleOfDialog.setText("Cấp bảng điểm");
                break;
            case 2:
                txtTitleOfDialog.setText("Đề nghị hoãn thi");
                break;
            case 3:
                txtTitleOfDialog.setText("Xem lại bài thi");
                break;
            case 4:
                txtTitleOfDialog.setText("Cấp lại thẻ sinh viên");
                break;
            case 5:
                txtTitleOfDialog.setText("Đề nghị làm vé xe bus");
                break;
            case 6:
                txtTitleOfDialog.setText("Xin thôi học");
                break;
            case 7:
                txtTitleOfDialog.setText("Cấp CN tốt nghiệp tạm thời");
                break;

        }

        EditText edtReason = dialog.findViewById(R.id.reason_of_dialog);
        Button file_archive = dialog.findViewById(R.id.file_archive);
        Button submitDialog = dialog.findViewById(R.id.submit_dialog);

        dialog.show();
    }

    private void ClickButtonInRequest() {
        btnRequestProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, RequestProcessing.class));
            }
        });

        btnRHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, MainActivity.class));
            }
        });

        btnRSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, Search.class));
            }
        });

        btnRProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, Profile.class));
            }
        });
    }

    private void ConstructButton() {
        btnRequestProcessing = (LinearLayout) findViewById(R.id.request_handing_bar);
    }

//    private void addListenerOnButton() {
//        attachButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");
//                startActivityForResult(intent, PICKFILE_REQUEST_CODE);
//            }
//        });
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICKFILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri uri = data.getData();
//            path = getRealPathFromURI(uri);
//            // Đính kèm tệp đó vào email, tin nhắn hoặc in-app storage của ứng dụng
//        }
//    }

//    private String getRealPathFromURI(Uri contentUri) {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
//        if (cursor == null) return null;
//        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String path = cursor.getString(columnIndex);
//        cursor.close();
//        return path;
//    }

//    private void addListenerOnSpinnerItemSelection() {
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),"OnItemSelectedListener: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

//    private void initifinal() {
//        spinner1 = (Spinner) findViewById(R.id.request_type_select);
//    }
//
//    public void submitRequest(View view) {
//        // Lấy dữ liệu từ Spinner
//        Spinner requestTypeSpinner = findViewById(R.id.request_type_select);
//        String requestType = requestTypeSpinner.getSelectedItem().toString();
//
//        // Lấy dữ liệu từ EditText
//        EditText editTextTitle = findViewById(R.id.request_reason);
//        String title = editTextTitle.getText().toString();
//
//        // Gửi dữ liệu lên server
//        // Code để gửi dữ liệu lên server
//
//        // Sử dụng thư viện Volley để gửi POST request
//        String url = "https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app";
//        StringRequest request = new StringRequest(Request.Method.POST, url,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(RequestAdd.this, "Request submitted", Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(RequestAdd.this, "Error submitting request", Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                // Truyền dữ liệu vào request
//                Map<String, String> params = new HashMap<>();
//                params.put("request_type", requestType);
//                params.put("title", title);
//                params.put("attachment", path); // Thêm đường dẫn của tệp đính kèm vào request
//                // Thêm các tham số khác nếu cần
//                return params;
//            }
//        };
//        Volley.newRequestQueue(this).add(request);
//    }

    private void CreateAndShowInfoStudent() {
        InfoAddRequest = findViewById(R.id.InfoAddRequest);
        //Intent intentBefore = getActivity().getIntent();
        //String student_id_child = intentBefore.getStringExtra("student_id").toString();
        String student_id_child = "12345678";
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    String strshow = "Họ tên SV: " + snapshot.child("name").getValue().toString() + "\nMSSV: " + snapshot.child("student_id").getValue().toString();
                    InfoAddRequest.setText(strshow);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



}