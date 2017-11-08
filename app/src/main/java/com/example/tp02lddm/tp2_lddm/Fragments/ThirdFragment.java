package com.example.tp02lddm.tp2_lddm.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tp02lddm.tp2_lddm.Listas.LinkLista;
import com.example.tp02lddm.tp2_lddm.Listas.PDFLista;
import com.example.tp02lddm.tp2_lddm.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by giovannariqueti on 13/10/17.
 */

public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View Myview = inflater.inflate(R.layout.third_layout, container, false);

        final String subjectName = this.getArguments().getString("subjectName");
        getActivity().setTitle(subjectName + " : " + "PDF");


        Intent intent = new Intent(getActivity(), PDFLista.class);

        Bundle params = new Bundle();

        params.putString("subject", subjectName);

        intent.putExtras(params);
        startActivity(intent);

        return Myview;
    }
}
