package com.example.j1studentconnect.tabsinmain;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.j1studentconnect.R;
import com.google.android.material.transition.Hold;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.grpc.internal.SharedResourceHolder;

public class TBInHomeAdapter extends RecyclerView.Adapter<TBInHomeAdapter.MyViewHolder> {


    ArrayList<TimeTableInMain> dataList;
    public TBInHomeAdapter(ArrayList<TimeTableInMain> dataList) {
        //this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TBInHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_cell_in_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TBInHomeAdapter.MyViewHolder holder, int position) {
        TimeTableInMain timeTableInMain = dataList.get(position);
        holder.timeLength.setText(timeTableInMain.lessonTime);
        holder.nameOfSubject.setText(timeTableInMain.name_of_subject);
        holder.subjectID.setText(timeTableInMain.id_of_subject);
        holder.placeForLearning.setText(timeTableInMain.classroom);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView timeLength, nameOfSubject, subjectID, placeForLearning;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            timeLength = itemView.findViewById(R.id.timelength);
            nameOfSubject = itemView.findViewById(R.id.name_of_subject);
            subjectID = itemView.findViewById(R.id.subject_id);
            placeForLearning = itemView.findViewById(R.id.place_for_learning);

        }
    }
}
