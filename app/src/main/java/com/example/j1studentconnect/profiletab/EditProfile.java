package com.example.j1studentconnect.profiletab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j1studentconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {
    EditText name, email, phone;
    String user_name, user_id, user_email, user_phone, imageURL;
    Button save;
    DatabaseReference reference;
    CircleImageView uploadImage;
    Uri uri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone_number);
        save = findViewById(R.id.btn_save_edit);
        uploadImage = findViewById(R.id.profile_image1);

        Intent intentBefore = getIntent();
        user_name = intentBefore.getStringExtra("name");
        user_email = intentBefore.getStringExtra("email");
        user_id = intentBefore.getStringExtra("student_id");
        user_phone = intentBefore.getStringExtra("phone");
        imageURL = intentBefore.getStringExtra("imageURL");

        name.setText(user_name);
        email.setText(user_email);
        phone.setText(user_phone);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(EditProfile.this, "Chọn ảnh đại diện của bạn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                user_name = name.getText().toString();
                save.setEnabled(true);
                save.setBackgroundColor(Color.BLUE);
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                user_email = email.getText().toString();
                save.setEnabled(true);
                save.setBackgroundColor(Color.BLUE);
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                user_phone = phone.getText().toString();
                save.setEnabled(true);
                save.setBackgroundColor(Color.BLUE);
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfile.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                updateDatabase();
                finish();
            }
        });
    }

    public void updateDatabase() {
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(user_id);
        reference.child("name").setValue(user_name);
        reference.child("email").setValue(user_email);
        reference.child("phone").setValue(user_phone);

        StorageReference storageReference = FirebaseStorage.getInstance("gs://j1-student-connect.appspot.com").getReference().child("images")
                .child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageURL = uri.toString();
                        reference.child("imageURL").setValue(imageURL);
                    }
                });
//                imageURL = urlImage.toString();
                Toast.makeText(EditProfile.this, imageURL, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });



//        reference.child("imageURL").setValue(imageURL);
    }
}
