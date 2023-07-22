package com.example.j1studentconnect;

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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton btnSearchHome, btnSearchProfile;

    private List<StudentForSearch> std_search;
    private RecyclerView recyclerViewSearch;
    private SearchAdapter searchAdapter;
    private EditText searchView, searchStudentName, searchClassID, searchStudentID;

    public TabSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static TabSearch newInstance(String param1, String param2) {
        TabSearch fragment = new TabSearch();
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
            if (item.getStudentInfoSearch().toLowerCase().contains(text.toLowerCase()) && index == "info")
                filterList.add(item);

            if (item.getClassIdSearch().toLowerCase().contains(text.toLowerCase())  && index == "classid")
                filterList.add(item);

            if (item.getClassNameSearch().toLowerCase().contains(text.toLowerCase()) && index == "classname")
                filterList.add(item);


        }
        searchAdapter.filter_List(filterList);

    }

    private List<StudentForSearch> getListSearch() {
        std_search = new ArrayList<>();
        std_search.add(new StudentForSearch("Nguyễn Tuấn Ngọc \n 22026521", "INT3120 50", "Tiếng Nhật trong Công nghệ thông tin", "3"));
        std_search.add(new StudentForSearch("Lương Thị Thu Hương", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Hoàng Phi Hùng", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Bùi Thị Ngọc", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        std_search.add(new StudentForSearch("Nguyễn Hữu Cứ", "INT3120 50", "Phát triển ứng dụng di động", "3"));
        return std_search;
    }
}