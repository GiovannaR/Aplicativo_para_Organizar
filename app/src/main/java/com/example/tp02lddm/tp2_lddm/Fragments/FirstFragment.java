package com.example.tp02lddm.tp2_lddm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tp02lddm.tp2_lddm.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class FirstFragment extends Fragment {


    ArrayAdapter<String> adapter;
    ArrayList<String> listItems=new ArrayList<String>();
    int clickCounter=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View Myview = inflater.inflate(R.layout.first_layout, container, false);



        try {

            String[] menuitem = {"PDF 1", "PDF 2"};

            ListView listview = (ListView) Myview.findViewById(R.id.lviw);

            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1,
                    menuitem
            );

            listview.setAdapter(listViewAdapter);

            Vinicius();


        }catch(Exception io){
            TextView textView = (TextView) getView().findViewById(R.id.texto);
            textView.setText(io.getMessage());
        }

        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "PDF");




        return Myview;
    }

    public void Vinicius(){

        try {
            listview.add("Clicked : " + clickCounter++);
            adapter.notifyDataSetChanged();
        }catch (Exception io){
            TextView textView = (TextView) getView().findViewById(R.id.texto);
            textView.setText(io.getMessage());
        }
    }

}