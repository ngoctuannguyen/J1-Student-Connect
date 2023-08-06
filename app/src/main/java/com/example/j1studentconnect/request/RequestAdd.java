package com.example.j1studentconnect.request;

import android.Manifest;
import java.util.Calendar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.searchtab.Search;
import com.example.j1studentconnect.tabsinmain.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class RequestAdd extends AppCompatActivity {
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 101;
    private static final int MANAGE_EXTERNAL_STORAGE_REQUEST_CODE = 102;


    private static final int PICKFILE_REQUEST_CODE = 1;


    DatabaseReference reference, reference1;
    FirebaseStorage storage;
    Uri tempFile;
    public static String strRequest = "";
    EditText edtReason;
    Button file_archive;
    Button submitDialog;
    String fileId, fileName;
    ActivityResultLauncher<String> getFile = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null) {
                String fileId = generateFileId();
                tempFile = result;
            }
        }
    });
    private Spinner spinner1;
    private Context context;
    private Button attachButton;
    private StorageReference storageRef;
    private String path;
    private TextView InfoAddRequest;
    private LinearLayout btnRequestProcessing;
    private ImageButton btnRHome, btnRSearch, btnRProfile;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    //    private EditText edtReason;
//    private Button file_archive, submitDialog;
    private String generateFileId() {
        return String.valueOf(System.currentTimeMillis());
    }
    private CardView cardResults, cardPostpone, cardReview, cardStudentRequest, cardBusRequest, cardStopLearning, cardDegree, cardSocialAssistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_request);
        ///attachButton = findViewById(R.id.attach_button);

        btnRHome = findViewById(R.id.RequestHome);
        btnRSearch = findViewById(R.id.RequestSearch);
        btnRProfile = findViewById(R.id.RequestProfile);


        FirebaseApp.initializeApp(this);
        storage = FirebaseStorage.getInstance();
        reference1 = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0");

        ConstructButton();
        ClickButtonInRequest();
        ConstructCardButton();
        //initifinal();
        //addListenerOnButton();
        //addListenerOnSpinnerItemSelection();
        //addListenerOnButton();
        addClickOnCardRequest();
        CreateAndShowInfoStudent();

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                requestManageExternalStoragePermission();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICKFILE_REQUEST_CODE);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền truy cập được cấp, bạn có thể tiếp tục việc chọn tệp ở đây
            } else {
                // Quyền truy cập bị từ chối, bạn nên xử lý tùy chọn tại đây
            }
        }
    }

    private void requestManageExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, MANAGE_EXTERNAL_STORAGE_REQUEST_CODE);
                overridePendingTransition(R.anim.anim_pomodoro_in, R.anim.anim_pomodoro_out);
            } catch (Exception e) {
                // Open the application's settings to grant the permission manually
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_pomodoro_in, R.anim.anim_pomodoro_out);
            }
        }
    }

    private void ConstructCardButton() {
        cardResults = findViewById(R.id.card_study_results);
        cardPostpone = findViewById(R.id.card_postpone);
        cardReview = findViewById(R.id.card_review);
        cardStudentRequest = findViewById(R.id.card_student_card);
        cardBusRequest = findViewById(R.id.card_bus_request);
        cardStopLearning = findViewById(R.id.card_stop_learning);
        cardDegree = findViewById(R.id.card_degree_request);
        cardSocialAssistance = findViewById(R.id.social_assistance);
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

        cardSocialAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRequest(Gravity.CENTER, 8);
            }
        });

    }

    private void openRequest(int gravity, int type) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_request);
        dialog.setContentView(R.layout.dialog_request);

        Window window = dialog.getWindow();
        if (window == null) return;

        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

//        if (Gravity.BOTTOM == gravity)
//            dialog.setCancelable(true);
//        else dialog.setCancelable(false);

        TextView txtTitleOfDialog = dialog.findViewById(R.id.dialog_title);
        switch (type) {

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
            case 8:
                txtTitleOfDialog.setText("Đề nghị hưởng trợ cấp xã hội");
                break;

        }

        edtReason = dialog.findViewById(R.id.reason_of_dialog);
        file_archive = dialog.findViewById(R.id.file_archive);
        submitDialog = dialog.findViewById(R.id.submit_dialog);
        RelativeLayout stateOfReason = dialog.findViewById(R.id.stateOfReason);
        RelativeLayout stateOfFile = dialog.findViewById(R.id.stateOfFile);
        TextView textState = dialog.findViewById(R.id.text_stateOfReason);
        TextView fileName = dialog.findViewById(R.id.fileName);
        stateOfReason.setVisibility(View.GONE);
        stateOfFile.setVisibility(View.GONE);

        // Sử dụng AnimationUtils để áp dụng animation vào Dialog
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.anim_pomodoro_in);
        dialog.findViewById(R.id.layout_dialog_request).startAnimation(slideUp);


//
        file_archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile.launch("*/*");
            }
        });


        submitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtReason.getText().toString())) {
                    textState.setText("Bạn chưa điền đủ lý do");
                    stateOfReason.setVisibility(View.VISIBLE);
                } else if (tempFile == null) {
                    textState.setText("Bạn chưa chọn tệp từ thiết bị");
                    stateOfReason.setVisibility(View.VISIBLE);
                } else {
                    stateOfFile.setVisibility(View.GONE);
                    stateOfReason.setVisibility(View.GONE);
                    String uid = currentUser.getUid();
                    Calendar calendar = Calendar.getInstance();

                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);

                    String currentTime = hour + ":" + minute + ":" + second;

                    String currentDate = day + "-" + (month + 1) + "-" + year + "     " + currentTime;
                    reference1.child("requests").child(uid).child(txtTitleOfDialog.getText().toString()).push().setValue(new RequestData(currentUser.getDisplayName().toString(), "Đang chờ", "", currentDate, edtReason.getText().toString())).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(RequestAdd.this, "Submit successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RequestAdd.this, RequestAdd.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("DatabaseError", "Failed to submit request");
                            Toast.makeText(RequestAdd.this, "Submit failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    StorageReference referencee = storage.getReference().child("archive_files/" + uid + "/" + txtTitleOfDialog.getText().toString() + "/" + currentDate + "/" + edtReason.getText().toString());
                    referencee.putFile(tempFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(RequestAdd.this, "Submit successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RequestAdd.this, RequestAdd.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("UpFileError", "Upload file failed");
                            Toast.makeText(RequestAdd.this, "Update failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        dialog.show();
    }

    private void ClickButtonInRequest() {
        btnRequestProcessing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, RequestProcessing.class));
                overridePendingTransition(R.anim.anim_activity_left_to_right_in, R.anim.anim_activity_left_to_right_out);

            }
        });

        btnRHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_activity_slide_up_return,R.anim.anim_activity_slide_down_return);
            }
        });

        btnRSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestAdd.this, Search.class));
                overridePendingTransition(R.anim.anim_activity_left_to_right_in, R.anim.anim_activity_left_to_right_out);
            }
        });


    }

    private void ConstructButton() {
        btnRequestProcessing = findViewById(R.id.request_handing_bar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == MANAGE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // User has granted access to read and manage external storage
                } else {
                    // User has denied access to manage external storage
                }
            }
        } else if (requestCode == PICKFILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            path = uri.getPath();
            Log.d("URIne", uri.toString());
            Log.d("Path tu uri ne", path);

            Log.d("DEBUG", "Path: " + path);

            if (path == null) {
                Toast.makeText(RequestAdd.this, "File not found", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            // Call the method to open the dialog and pass the 'path' to it
            openRequest(Gravity.CENTER, 1);
        } else {
            path = null;
        }
    }

    private void CreateAndShowInfoStudent() {
        InfoAddRequest = findViewById(R.id.InfoAddRequest);
        //Intent intentBefore = getActivity().getIntent();
        //String student_id_child = intentBefore.getStringExtra("student_id").toString();
        String student_id_child = "22026521";
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
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