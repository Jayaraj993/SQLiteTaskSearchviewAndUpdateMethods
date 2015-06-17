package com.example.emd029.sqlite_task.SlidingTablayout;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.emd029.sqlite_task.R;
import com.example.emd029.sqlite_task.StudentNames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EMD029 on 6/11/2015.
 */
public class AdapterBind extends BaseAdapter {
    CompleteListViewHolder viewHolder;

    //creating a class using base adapter
    //just use a base adapter in a common for all
    Context context;
    private SparseBooleanArray mSelectedItems;
    ArrayList<StudentNames> list=new ArrayList<StudentNames >();
    public AdapterBind(Context context, ArrayList<StudentNames> list){
        this.context=context;
        this.list=list;

        mSelectedItems=new SparseBooleanArray();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public StudentNames getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.text, null);
            viewHolder = new CompleteListViewHolder();
            viewHolder.mTVItem = (TextView) v.findViewById(R.id.textView);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        //viewHolder.
        viewHolder.mTVItem.setText(list.get(position).getName());
       /* viewHolder.mTVItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //viewHolder.mTVItem.setBackgroundColor(Color.parseColor("#FF00CC"));*//*setBackgroundColor(R.color.pink);*//*
            }
        });*/
        return v;
    }

    class CompleteListViewHolder {


             TextView mTVItem;

    }

    public void remove(StudentNames names){
        list.remove(names);
       notifyDataSetChanged();
    }

    public List<StudentNames> getStudentNames(){
        return list;
    }

    public void toggleSelection(int position){
        selectView(position,!mSelectedItems.get(position));
    }

    public void removeSelection(){
        mSelectedItems=new SparseBooleanArray();
      notifyDataSetChanged();
    }

    public void selectView(int position,boolean value){
        if (value){
            mSelectedItems.put(position,value);
        }else{
            mSelectedItems.delete(position);
        }
        notifyDataSetChanged();
    }

    public int getSelectedCount(){
        return mSelectedItems.size();
    }
    public SparseBooleanArray getSelectedItems(){
        return mSelectedItems;
    }

}
