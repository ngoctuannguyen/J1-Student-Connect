package com.example.j1studentconnect.tabsinmain;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.j1studentconnect.R;

import java.util.ArrayList;

import io.grpc.internal.SharedResourceHolder;

public class TBInHomeAdapter extends ArrayAdapter<TimeTableInMain> {

    private Context mcontext;
    private int mResoure;

    public TBInHomeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TimeTableInMain> objects) {
        super(context, resource, objects);
        this.mcontext = mcontext;
        this.mResoure = mResoure;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        convertView = layoutInflater.inflate(mResoure, parent, false);

        TextView timeLength = convertView.findViewById(R.id.timelength);
        TextView nameOfSubject = convertView.findViewById(R.id.name_of_subject);
        TextView subjectID = convertView.findViewById(R.id.subject_id);
        TextView placeForLearning = convertView.findViewById(R.id.place_for_learning);

        timeLength.setText(getItem(position).getLessonTime());
        nameOfSubject.setText(getItem(position).getName_of_subject());
        subjectID.setText(getItem(position).getId_of_subject());
        placeForLearning.setText(getItem(position).getClassroom());

        return convertView;
    }
}
