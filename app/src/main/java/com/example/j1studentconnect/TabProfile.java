package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.j1studentconnect.authentication.Login;
import com.example.j1studentconnect.authentication.LoginLauncher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class TabProfile extends Fragment {

    TextView name, email, student_id, password, gender, birthday, student_class, phone, title_name;

    String user_id, user_name, user_email, user_gender, user_class, user_birthday, user_phone, student_id_child, profile_imageURL;
    CircleImageView profile_image;

    Button logout, edit_profile;
    View view;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_profile, container, false);

        Intent intentBefore = getActivity().getIntent();
        student_id_child = intentBefore.getStringExtra("student_id").toString();
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("users").child(student_id_child);

        parametersConstruct();
        buttonsConstruct();
        showAllUserData();

        return view;
    }

    private void parametersConstruct() {
        name = view.findViewById(R.id.student_name);
        student_id = view.findViewById(R.id.student_id);
        gender = view.findViewById(R.id.gender);
        birthday = view.findViewById(R.id.birthday);
        student_class = view.findViewById(R.id.student_class);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        title_name = view.findViewById(R.id.title_name);
        edit_profile = view.findViewById(R.id.btn_edit_profile);
        logout = view.findViewById(R.id.btn_logout);
    }

    private void buttonsConstruct() {
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                intent.putExtra("student_id", user_id);
                intent.putExtra("name", user_name);
                intent.putExtra("phone", user_phone);
                intent.putExtra("email", user_email);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Login.class));
            }
        });
    }

    public void showAllUserData() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    user_id = snapshot.child("student_id").getValue().toString();
                    user_name = snapshot.child("name").getValue().toString();
                    user_email = snapshot.child("email").getValue().toString();
                    user_gender = snapshot.child("gender").getValue().toString();
                    user_class = snapshot.child("student_class").getValue().toString();
                    user_birthday = snapshot.child("birthday").getValue().toString();
                    user_phone = snapshot.child("phone").getValue().toString();
                    if (snapshot.hasChild("imageURL")) {
                        profile_imageURL = snapshot.child("imageURL").getValue().toString();
                        // Picasso.get().load(profile_imageURL).into(profile_image);
                    }
                    title_name.setText(user_name);
                    name.setText(user_name);
                    email.setText(user_email);
                    student_id.setText(user_id);
                    birthday.setText(user_birthday);
                    gender.setText(user_gender);
                    student_class.setText(user_class);
                    phone.setText(user_phone);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showAllUserData();
    }
}