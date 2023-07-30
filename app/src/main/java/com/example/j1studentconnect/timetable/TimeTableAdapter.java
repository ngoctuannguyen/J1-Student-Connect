package com.example.j1studentconnect.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.j1studentconnect.R;

import java.util.HashMap;
import java.util.List;

public class TimeTableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> dayList;
    private HashMap<String, List<String>> subjectList;

    public TimeTableAdapter(Context context, List<String> dayList, HashMap<String, List<String>> subjectList) {
        this.context = context;
        this.dayList = dayList;
        this.subjectList = subjectList;
    }

    @Override
    public int getGroupCount() {
        return this.dayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.subjectList.get(this.dayList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.dayList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.subjectList.get(this.dayList.get(i)).get(i1);
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
        String dayTitle = (String) getGroup(i);

        if (view == null){

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dayl_list_in_calendar_set, null);
        }

        TextView daytxt = view.findViewById(R.id.daytxt);
        daytxt.setText(dayTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        String subjectTitle = (String) getChild(i, i1);

        if (view == null){

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subject_list_calendar_set, null);
        }

        TextView subjectTxt = view.findViewById(R.id.subjecttxt);
        subjectTxt.setText(subjectTitle);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
