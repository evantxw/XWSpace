package com.evan.xwspace;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by evan on 2015/12/8.
 */
public class PersonAdapter extends RecyclerView.Adapter {

    public interface OnRecycleListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }


    public static final String TAG = "PersonAdapter";

    public OnRecycleListener onRecycleListener;
    private List<Person> list;

    public PersonAdapter(List<Person> list) {
        this.list = list;

    }

    public void setOnRecycleListener(OnRecycleListener onRecycleListener) {
        this.onRecycleListener = onRecycleListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder, i: " + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder, i: " + position);
        PersonViewHolder holder1 = (PersonViewHolder) holder;
        holder1.position = position;
        Person person = list.get(position);
        holder1.name_tv.setText(person.getName());
        holder1.age_tv.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public View rootView;
        public TextView name_tv;
        public TextView age_tv;
        public int position;

        public PersonViewHolder(View view) {
            super(view);
            name_tv = (TextView) view.findViewById(R.id.recycle_item_tv1);
            age_tv = (TextView) view.findViewById(R.id.recycle_item_tv2);
            rootView = view.findViewById(R.id.recycle_item);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (onRecycleListener != null) {
                return onRecycleListener.onItemLongClick(position);
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (onRecycleListener != null) {
                onRecycleListener.onItemClick(position);
            }
        }
    }
}