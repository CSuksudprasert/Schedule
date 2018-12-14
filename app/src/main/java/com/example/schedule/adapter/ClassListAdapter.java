package com.example.schedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schedule.Item_class;
import com.example.schedule.R;

import java.util.List;

public class ClassListAdapter extends ArrayAdapter<Item_class> {
    private Context mContext;
    private int mResource;
    private List<Item_class> mclassItemList;

    public ClassListAdapter(@NonNull Context context,
                            int resource,
                            @NonNull List<Item_class> classItemList) {
        super(context, resource, classItemList);
        this.mContext = context;
        this.mResource = resource;
        this.mclassItemList = classItemList;
    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(mResource, parent, false);

            TextView subjectTextView = view.findViewById(R.id.subject_text_view);
            TextView dayTextView = view.findViewById(R.id.day_text_view);
            TextView timeView = view.findViewById(R.id.time_text_view);
            TextView classroomView = view.findViewById(R.id.classroom_text_view);

            Item_class classItem = mclassItemList.get(position);
            String subject = classItem.subject;
            String day = classItem.day;
            String time = classItem.time;
            String classroom = classItem.classroom;

            subjectTextView.setText(subject);
            dayTextView.setText(day);
            timeView.setText(time);
            classroomView.setText(classroom);

            return view;
    }
}
