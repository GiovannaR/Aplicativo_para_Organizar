package com.example.tp02lddm.tp2_lddm.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tp02lddm.tp2_lddm.Listas.LinkLista;
import com.example.tp02lddm.tp2_lddm.R;

import java.util.ArrayList;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class SecondFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View Myview = inflater.inflate(R.layout.first_layout, container, false);



        final String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "Link");


            Intent intent = new Intent(getActivity(), LinkLista.class);

            Bundle params = new Bundle();

            params.putString("subject", subjectName);

            intent.putExtras(params);
            startActivity(intent);



        return Myview;
    }


}

