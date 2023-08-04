package com.example.j1studentconnect.timetable;

import android.content.Context;
import android.graphics.Color;
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
        String dayView = dayList.get(i);
        if (dayView.equals("Monday"))  daytxt.setBackgroundResource(R.drawable.bg_day_list_sec);
        if (dayView.equals("Tuesday"))  daytxt.setBackgroundResource(R.drawable.bg_day_list_fir);
        if (dayView.equals("Wednesday"))  daytxt.setBackgroundResource(R.drawable.bg_day_list_thir);
        if (dayView.equals("Thursday"))  daytxt.setBackgroundResource(R.drawable.bg_day_list_for);
        if (dayView.equals("Friday"))  daytxt.setBackgroundResource(R.drawable.bg_list_day_fif);
        if (dayView.equals("Saturday"))  daytxt.setBackgroundResource(R.drawable.bg_list_day_six);

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

        if (i==0) {
            if(i1==0) subjectTxt.setBackgroundResource(R.drawable.bg_sub_fir);
            if(i1==1) subjectTxt.setBackgroundResource(R.drawable.bg_sub_sec);
            if(i1==2) subjectTxt.setBackgroundResource(R.drawable.bg_sub3);
        } else if (i==1) {
            if(i1==0) subjectTxt.setBackgroundResource(R.drawable.bg_sub4);
            if(i1==1) subjectTxt.setBackgroundResource(R.drawable.bg_sub5);
            if(i1==2) subjectTxt.setBackgroundResource(R.drawable.bg_sub6);
        } else if (i==2) {
            if (i1 == 0) subjectTxt.setBackgroundResource(R.drawable.bg_sub12);
            if (i1 == 1) subjectTxt.setBackgroundResource(R.drawable.bg_sub8);
            if (i1 == 2) subjectTxt.setBackgroundResource(R.drawable.bg_sub9);
        } else if (i==3) {
            if (i1 == 0) subjectTxt.setBackgroundResource(R.drawable.bg_sub10);
            if (i1 == 1) subjectTxt.setBackgroundResource(R.drawable.bg_sub11);
            if (i1 == 2) subjectTxt.setBackgroundResource(R.drawable.bg_sub7);
        } else if (i==4) {
            if (i1 == 0) subjectTxt.setBackgroundResource(R.drawable.bg_sub13);
            if (i1 == 1) subjectTxt.setBackgroundResource(R.drawable.bg_sub14);
            if (i1 == 2) subjectTxt.setBackgroundResource(R.drawable.bg_sub_15);
        } else if (i==5) {
            if (i1 == 0) subjectTxt.setBackgroundResource(R.drawable.bg_sub_16);
            if (i1 == 1) subjectTxt.setBackgroundResource(R.drawable.bg_sub17);
            if (i1 == 2) subjectTxt.setBackgroundResource(R.drawable.bg_sub18);
        }



        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
