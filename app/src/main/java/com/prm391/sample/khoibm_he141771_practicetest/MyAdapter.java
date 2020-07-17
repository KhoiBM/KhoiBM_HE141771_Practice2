package com.prm391.sample.khoibm_he141771_practicetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Employee> employees;
    private Context context;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    public MyAdapter(List<Employee> employees, Context context) {
        this.employees = employees;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_layout, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (employees != null && position < employees.size()) {
            final Employee employee = employees.get(position);
            holder.name.setText(employee.getName());
            holder.phone.setText(employee.getPhone());
            holder.setUpdateClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    Intent intent = new Intent(context, NewActivity.class);
                    intent.putExtra("typeAction", "update");
                    intent.putExtra("employee", employee);
                    ((Activity) context).startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return employees.size();
    }
}
