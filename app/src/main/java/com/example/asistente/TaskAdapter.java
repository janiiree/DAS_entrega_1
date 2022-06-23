package com.example.asistente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Task> list;

    public TaskAdapter(Context pContext, List<Task> pList) {
        context = pContext;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        list = pList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Task getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.list_object_task ,null);

        TextView tv_name = (TextView) view.findViewById(R.id.tv_task_name);
        RatingBar rb_priority = (RatingBar) view.findViewById(R.id.task_priority);
        TextView tv_desc = (TextView) view.findViewById(R.id.tv_task_desc);

        tv_name.setText(list.get(i).getTaskName());
        rb_priority.setRating(list.get(i).getPriority());
        tv_desc.setText(list.get(i).getTaskDescription());

        return view;
    }
}
