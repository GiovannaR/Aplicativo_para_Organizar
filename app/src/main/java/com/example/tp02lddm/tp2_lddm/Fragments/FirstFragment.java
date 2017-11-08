package com.example.tp02lddm.tp2_lddm.Fragments;

import android.app.Fragment;
import android.content.Intent;
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

import com.example.tp02lddm.tp2_lddm.Listas.LinkLista;
import com.example.tp02lddm.tp2_lddm.Listas.VideoLista;
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


        final String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "Video");

        String[] menuitem = {"https://www.youtube.com/watch?v=IwzUs1IMdyQ"};

        Intent intent = new Intent(getActivity(), VideoLista.class);

        Bundle params = new Bundle();

        params.putString("subject", subjectName);

        intent.putExtras(params);
        startActivity(intent);



        return Myview;
    }


}