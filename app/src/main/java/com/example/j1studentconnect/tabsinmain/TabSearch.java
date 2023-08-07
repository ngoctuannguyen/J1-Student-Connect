package com.example.j1studentconnect.tabsinmain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.searchtab.SearchAdapter;
import com.example.j1studentconnect.searchtab.StudentForSearch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TabSearch extends Fragment {
    private View rootView;
    private ImageButton btnSearchHome, btnSearchProfile;
    private List<StudentForSearch> std_search;
    private RecyclerView recyclerViewSearch;
    private SearchAdapter searchAdapter;
    private EditText searchView, searchStudentName, searchClassID, searchStudentID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tab_search, container, false);
        onViewCreated(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSearch.setLayoutManager(linearLayoutManager);

        //SearchAdapter
        searchAdapter = new SearchAdapter(getListSearch());
        recyclerViewSearch.setAdapter(searchAdapter);

        //SearchViewEditText
        searchView = rootView.findViewById(R.id.viewToSearch);
        searchStudentName = rootView.findViewById(R.id.student_name_search_bar);
        searchStudentID = rootView.findViewById(R.id.student_id_search_bar);
        searchClassID = rootView.findViewById(R.id.class_id_search_bar);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString(), "classname");
            }
        });

        searchStudentName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString(), "info");
            }
        });

        searchClassID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString(), "classid");
            }
        });

        searchStudentID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString(), "info");
            }
        });


    }

    private void filter(String text, String index) {
        List<StudentForSearch> filterList = new ArrayList<>();

        for (StudentForSearch item : std_search){
            if (item.getStudentInfoSearch().toLowerCase().contains(text.toLowerCase()) && index.equals("info"))
                filterList.add(item);

            if (item.getClassIdSearch().toLowerCase().contains(text.toLowerCase())  && index.equals("classid"))
                filterList.add(item);

            if (item.getClassNameSearch().toLowerCase().contains(text.toLowerCase()) && index.equals("classname"))
                filterList.add(item);


        }
        searchAdapter.filter_List(filterList);

    }

    private List<StudentForSearch> getListSearch() {
        std_search = new ArrayList<>();
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance("https://j1-student-connect-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("1srn9ku9VkZvIf9dugTTPEcr2tRk3tkWl0MWxjzT1lp0").child("search");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot temp: snapshot.getChildren()) {
                        String tmp_name = temp.child("name").getValue().toString();
                        String tmp_id = temp.child("student_id").getValue().toString();
                        String tmp_class_id = temp.child("class_id").getValue().toString();
                        String tmp_class = temp.child("class_name").getValue().toString();
                        String tmp_tc = temp.child("portal_number").getValue().toString();
                        std_search.add(new StudentForSearch(tmp_name + "\n" + tmp_id, tmp_class_id, tmp_class, tmp_tc));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return std_search;
    }
}