//package com.example.j1studentconnect;
//
//import android.app.ActivityManager;
//import android.content.Context;
//import android.graphics.Color;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.NumberPicker;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
//    private Context context;
//    private ArrayList<Date> data;
//    private Calendar currentDate;
//    private Calendar changeMonth;
//    private OnItemClickListener mListener;
//    private int index = -1;
//    private boolean selectCurrentDate = true;
//    private int currentMonth;
//    private int currentYear;
//    private int currentDay;
//    private int selectedDay;
//    private int selectedMonth;
//    private int selectedYear;
//
//    public CalendarAdapter(Context context, ArrayList<Date> data, Calendar currentDate, Calendar changeMonth) {
//        this.context = context;
//        this.data = data;
//        this.currentDate = currentDate;
//        this.changeMonth = changeMonth;
//
//        currentMonth = currentDate.get(Calendar.MONTH);
//        currentYear = currentDate.get(Calendar.YEAR);
//        currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
//
//        if (changeMonth != null) {
//            selectedDay = changeMonth.getActualMinimum(Calendar.DAY_OF_MONTH);
//        } else {
//            selectedDay = currentDay;
//        }
//
//        if (changeMonth != null) {
//            selectedMonth = changeMonth.get(Calendar.MONTH);
//        } else {
//            selectedMonth = currentMonth;
//        }
//
//        if (changeMonth != null) {
//            selectedYear = changeMonth.get(Calendar.YEAR);
//        } else {
//            selectedYear = currentYear;
//        }
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
//        return new ViewHolder(view, mListener);
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.ENGLISH);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(data.get(position));
//
//        int displayMonth = cal.get(Calendar.MONTH);
//        int displayYear = cal.get(Calendar.YEAR);
//        int displayDay = cal.get(Calendar.DAY_OF_MONTH);
//
//        try {
//            Date dayInWeek = sdf.parse(cal.getTime().toString());
//            sdf.applyPattern("EEE");
//            holder.txtDayInWeek.setText(sdf.format(dayInWeek));
//        } catch (ParseException ex) {
//            Log.v("Exception", ex.getLocalizedMessage());
//        }
//        holder.txtDay.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
//
//        if (displayYear >= currentYear && (displayMonth >= currentMonth || displayYear > currentYear)) {
//            if (displayDay >= currentDay || displayMonth > currentMonth || displayYear > currentYear) {
//                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        index = position;
//                        selectCurrentDate = false;
//                        holder.listener.onItemClick(position);
//                        notifyDataSetChanged();
//                    }
//                });
//
//                if (index == position)
//                    makeItemSelected(holder);
//                else {
//                    if (displayDay == selectedDay && displayMonth == selectedMonth && displayYear == selectedYear && selectCurrentDate)
//                        makeItemSelected(holder);
//                    else
//                        makeItemDefault(holder);
//                }
//            } else {
//                makeItemDisabled(holder);
//            }
//        } else {
//            makeItemDisabled(holder);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.size();
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    private void makeItemDisabled(ViewHolder holder) {
//        holder.txtDay.setTextColor(ContextCompat.getColor(context, R.color.yellow));
//        holder.txtDayInWeek.setTextColor(ContextCompat.getColor(context, R.color.blue));
//        holder.linearLayout.setBackgroundColor(Color.WHITE);
//        //holder.linearLayout.setEnabled(false);
//    }
//
//    private void makeItemSelected(ViewHolder holder) {
//        holder.txtDay.setTextColor(Color.parseColor("#FFFFFF"));
//        holder.txtDayInWeek.setTextColor(Color.parseColor("#FFFFFF"));
//        holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
//        //holder.linearLayout.setEnabled(false);
//    }
//
//    private void makeItemDefault(ViewHolder holder) {
//        holder.txtDay.setTextColor(Color.BLACK);
//        holder.txtDayInWeek.setTextColor(Color.BLACK);
//        holder.linearLayout.setBackgroundColor(Color.WHITE);
//        //holder.linearLayout.setEnabled(true);
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public NumberPicker txtDayInWeek;
//        public ActivityManager.TaskDescription.Builder linearLayout;
//        TextView txtDay;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
//}