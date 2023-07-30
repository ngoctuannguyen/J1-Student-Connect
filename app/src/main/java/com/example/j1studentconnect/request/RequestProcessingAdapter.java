package com.example.j1studentconnect.request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.j1studentconnect.R;

import java.util.HashMap;
import java.util.List;

public class RequestProcessingAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> RequestTypeList;
    private HashMap<String, List<String>> StateRequestList;

    public RequestProcessingAdapter(Context context, List<String> requestTypeList, HashMap<String, List<String>> stateRequestList) {
        this.context = context;
        this.RequestTypeList = requestTypeList;
        this.StateRequestList = stateRequestList;
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

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
