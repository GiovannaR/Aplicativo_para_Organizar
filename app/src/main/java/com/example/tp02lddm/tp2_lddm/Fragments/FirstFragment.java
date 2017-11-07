package com.example.tp02lddm.tp2_lddm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tp02lddm.tp2_lddm.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class FirstFragment extends Fragment {


    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    ListView listView;
    int count = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View Myview = inflater.inflate(R.layout.first_layout, container, false);



        try {

            String[] menuitem = {"PDF 1", "PDF 2"};



            itemList=new ArrayList<String>(Arrays.asList(menuitem));
            adapter=new ArrayAdapter<String>(getContext(),R.layout.list_item,R.id.lblListItem,itemList);
            ListView listview = (ListView) Myview.findViewById(R.id.lviw);
            //ListView listV=(ListView)findViewById(R.id.list);
            listview.setAdapter(adapter);

         /*   ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1,
                    menuitem
            );
*/
            //listview.setAdapter(adapter);




        }catch(Exception io){
            TextView textView = (TextView) getView().findViewById(R.id.texto);
            textView.setText(io.getMessage());
        }

        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "PDF");

        Button btAdd=(Button) Myview.findViewById(R.id.addPdf);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                try {
                    itemList.add("PDF " + count);
                    adapter.notifyDataSetChanged();
                }catch (Exception io){
                    TextView textView = (TextView) getView().findViewById(R.id.texto);
                    textView.setText(io.getMessage());
                }
            }
        });


        return Myview;
    }


}