package com.example.emd029.sqlite_task.SlidingTablayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
public class Tab2 extends Fragment {
    //creating a fragment for a viewpager tabview
    ListView listView2;
    ArrayList<StudentNames> studentlist;
    AdapterBind adapterBind;

    public Tab2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.tab2, group, false);
        listView2 = (ListView) view.findViewById(R.id.listView2);
        DbHandler dbHandler = new DbHandler(getActivity());
        if (Global.search_STATUS) {
            studentlist = dbHandler.searchByInputTxt(Global.ssignment_STATUS);
        } else {
            //get a data into a arraylist by calling a method in a DbHandler
            studentlist = dbHandler.getalldatas();
        }
        //pass a arraylist into a base adapter class Hai
        adapterBind = new AdapterBind(getActivity(), studentlist);
        //set a adapter into a listview using a baseadapter class object hai
        listView2.setAdapter(adapterBind);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ListEditPage.class);
                intent.putExtra("person name",studentlist.get(position).getName());
                intent.putExtra("date",studentlist.get(position).getDate());
                intent.putExtra("time",studentlist.get(position).getTime());
                intent.putExtra("description",studentlist.get(position).getDescription());
                startActivity(intent);
            }
        });
        selectMultiple();
        return view;
    }

    public void selectMultiple(){
        listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView2.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int checkedCount=listView2
                        .getCheckedItemCount();
                mode.setTitle(checkedCount+ " Selected");
                adapterBind.toggleSelection(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_sqlite__main,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.delete:
                        SparseBooleanArray selected=adapterBind.getSelectedItems();
                        for (int i= (selected.size()-1);i>=0;i--){
                            if (selected.valueAt(i)){
                                StudentNames  selectedItem=(StudentNames)adapterBind.getItem(selected.keyAt(i));
                                adapterBind.remove(selectedItem);
                                //Toast.makeText(getActivity(), selected.toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                adapterBind.removeSelection();
            }
        });
    }
}
