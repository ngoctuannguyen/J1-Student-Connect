package com.example.j1studentconnect.searchtab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.j1studentconnect.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    private List<StudentForSearch> stdSearch;

    public SearchAdapter(List<StudentForSearch> stdSearch) {
        this.stdSearch = stdSearch;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_in4_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        StudentForSearch studentForSearch = stdSearch.get(position);
        if (stdSearch != null && stdSearch.size() > 0){

            holder.txtInfo.setText(studentForSearch.getStudentInfoSearch());
            holder.txtClassId.setText(studentForSearch.getClassIdSearch());
            holder.txtClassName.setText(studentForSearch.getClassNameSearch());
            holder.txtPortalNumber.setText(studentForSearch.getPortalNumberSearch());
        }
        else return;

    }

    @Override
    public int getItemCount() {
        if (stdSearch != null)
            return stdSearch.size();
        return 0;
    }

    public void filter_List(List<StudentForSearch> filterList) {
        stdSearch = filterList;
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView recyclerView;
        private TextView txtInfo, txtClassId, txtClassName, txtPortalNumber;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txtInfo = itemView.findViewById(R.id.item_search_info);
            txtClassId = itemView.findViewById(R.id.item_search_class_id);
            txtClassName = itemView.findViewById(R.id.item_search_class_name);
            txtPortalNumber = itemView.findViewById(R.id.item_search_portal_number);

        }
    }
}
