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

import com.example.tp02lddm.tp2_lddm.R;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class FirstFragment extends Fragment {

    View Myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        /*String[] menuitem = {"Vídeos", "Links"};

        ListView listview = (ListView) Myview.findViewById(R.id.lviw);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                menuitem
        );

        listview.setAdapter(listViewAdapter);*/
        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "PDF");
        Myview = inflater.inflate(R.layout.third_layout, container, false);

        return Myview;
    }

}