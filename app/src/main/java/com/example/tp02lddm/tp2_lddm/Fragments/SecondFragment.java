package com.example.tp02lddm.tp2_lddm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tp02lddm.tp2_lddm.R;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*try {

            String[] menuitem = {"Vídeos", "Links"};

            ListView listview = (ListView) Myview.findViewById(R.id.lviw);

            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_1,
                    menuitem
            );

            listview.setAdapter(listViewAdapter);


        }catch(Exception io){
            //TextView textView = (TextView) getView().findViewById(R.id.);
            //io.getMessage();
        }*/

        String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "Link");
        View Myview = inflater.inflate(R.layout.second_layout, container, false);

        return Myview;
    }
}
