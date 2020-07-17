package com.prm391.sample.khoibm_he141771_practicetest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name, phone;
    public Button update;
    public ItemClickListener updateClickListener;

    public ViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvName);
        phone = itemView.findViewById(R.id.tvPhone);
        update = itemView.findViewById(R.id.btnUpdate);
        update.setOnClickListener(this);

    }

    public void setUpdateClickListener(ItemClickListener updateClickListener) {
        this.updateClickListener = updateClickListener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnUpdate:
                updateClickListener.onClick(v,getAdapterPosition(),false);
                break;
        }
    }
}
