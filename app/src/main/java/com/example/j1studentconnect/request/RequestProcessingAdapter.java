package com.example.j1studentconnect.request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.j1studentconnect.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestProcessingAdapter extends BaseExpandableListAdapter {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    Query query = databaseReference.child("requests").orderByChild("uid").equalTo("RrJhoNgYjKZynWWTmtBFnGMRTJj1");
    private Context context;
    private List<String> RequestTypeList;
    private HashMap<String, List<String>> StateRequestList;
    private List<Boolean> itemVisibilityList = new ArrayList<>();

    public RequestProcessingAdapter(Context context, List<String> requestTypeList, HashMap<String, List<String>> stateRequestList) {
        this.context = context;
        this.RequestTypeList = requestTypeList;
        this.StateRequestList = stateRequestList;
        for (int i = 0; i < requestTypeList.size(); i++) {
            itemVisibilityList.add(false);
        }
    }

    @Override
    public int getGroupCount() {
        return this.RequestTypeList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.StateRequestList.get(this.RequestTypeList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.RequestTypeList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.StateRequestList.get(this.RequestTypeList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String typeOfRequest = (String) getGroup(i);

        if (view == null){

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.type_of_request_processing, null);
        }

        TextView txtTypeOfRequestProcessing = view.findViewById(R.id.txtTypeOfRequestProcessing);
        txtTypeOfRequestProcessing.setText(typeOfRequest);

        if(i==0) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub_fir);
        if(i==1) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub_sec);
        if(i==2) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub3);
        if(i==3) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub4);
        if(i==4) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub5);
        if(i==5) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub6);
        if(i==6) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub7);
        if(i==7) txtTypeOfRequestProcessing.setBackgroundResource(R.drawable.bg_sub8);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String request_cell_processing = (String) getChild(i, i1);

        if (view == null){

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.request_processing_cell, null);
        }

        TextView dateCreate = view.findViewById(R.id.cell_request_date);
        //TextView requestFile = view.findViewById(R.id.cell_request_file);
        //TextView reasonForRequest = view.findViewById(R.id.reason_for_request);

        dateCreate.setText(request_cell_processing);
        //requestFile.setText(request_cell_processing);
        //reasonForRequest.setText(request_cell_processing);
        if (request_cell_processing.contains("Đang chờ")) dateCreate.setBackgroundResource(R.drawable.bg_waiting_request_cell);
        else if (request_cell_processing.contains("Đã duyệt")) dateCreate.setBackgroundResource(R.drawable.bg_checked_request_cell);
        else dateCreate.setBackgroundResource(R.drawable.bg_request_cell);
        // Kiểm tra xem item đã hiển thị hay chưa
        if (!itemVisibilityList.get(i)) {
            // Áp dụng hiệu ứng fade-in vào view
            Animation fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_pomodoro_in);
            view.startAnimation(fadeInAnimation);
            itemVisibilityList.set(i, true); // Đánh dấu item đã hiển thị
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}