package com.example.emd029.sqlite_task.SlidingTablayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.emd029.sqlite_task.DbHandler;
import com.example.emd029.sqlite_task.Global;
import com.example.emd029.sqlite_task.ListNewPage.ListEditPage;
import com.example.emd029.sqlite_task.R;
import com.example.emd029.sqlite_task.StudentNames;

import java.util.ArrayList;

/**
 * Created by EMD029 on 6/4/2015.
 */
public class Tab1 extends Fragment {
    //creating a fragment for a viewpager tabview
    Context context;
    public ListView listView1;
    ArrayList<StudentNames> studentsList;
    AdapterBind adapterBind;

    public Tab1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup group,Bundle savedInstanceState){
        ViewGroup view= (ViewGroup) inflater.inflate(R.layout.tab1,group,false);
        DbHandler dbHandler=new DbHandler(getActivity());
        listView1= (ListView) view.findViewById(R.id.listView1);
        if(Global.search_STATUS){

            //get a data into a arraylist by calling a method in a DbHandler
            studentsList = dbHandler.searchByInputTxt(Global.ssignment_STATUS);
        }
        //get a data into a arraylist by calling a method in a DbHandler
        else {

            studentsList = dbHandler.getall();
        }
        //pass a arraylist to abase adapter class hai
       // baseAdapterlist =new BaseAdapter_list(getActivity(),na);
        adapterBind =new AdapterBind(getActivity(),studentsList);
        //set a adapter using a base adapter class object hai
        listView1.setAdapter(adapterBind);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ListEditPage.class);
               // String empn=((TextView)view.findViewById(R.id.textView)).getText().toString();

                intent.putExtra("person name",studentsList.get(position).getName());
                intent.putExtra("date",studentsList.get(position).getDate());
                intent.putExtra("time",studentsList.get(position).getTime());
                intent.putExtra("description",studentsList.get(position).getDescription());
                startActivity(intent);
            }
        });

        return view;
    }
}
