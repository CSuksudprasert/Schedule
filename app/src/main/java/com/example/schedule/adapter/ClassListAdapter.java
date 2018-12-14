package com.example.schedule.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.schedule.Item_class;

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
}
