package com.example.tp02lddm.tp2_lddm.Listas;

/**
 * Created by giovannariqueti on 07/11/17.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tp02lddm.tp2_lddm.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by giovannariqueti on 06/11/17.
 */

public class VideoLista extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    String subject;
    String nomeSalvar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        itemList=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,R.layout.list_item,R.id.lblListItem,itemList);
        final ListView listV=(ListView)findViewById(R.id.lviw);
        listV.setAdapter(adapter);

        String[] menuitem = {"https://www.youtube.com/watch?v=P9A1-fxd-hI"};

        itemList = new ArrayList<String>(Arrays.asList(menuitem));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.lblListItem, itemList);
        final ListView listview = (ListView) findViewById(R.id.lviw);
        listview.setAdapter(adapter);



        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            subject = params.getString("subject");
        }
        update();





        Button addBtn = (Button) findViewById(R.id.addVideo);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                AlertDialog.Builder mmBuilder;
                mmBuilder = new AlertDialog.Builder(VideoLista.this);
                View mView = getLayoutInflater().inflate(R.layout.customedialog, null);
                final EditText conteudo = (EditText) mView.findViewById(R.id.mnova);
                Button addBtn = (Button) mView.findViewById(R.id.close);
                mmBuilder.setView(mView);
                final AlertDialog dialog = mmBuilder.create();
                dialog.show();

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!conteudo.getText().toString().isEmpty()) {
                            if (naoExiste(conteudo.getText().toString())) {
                                itemList.add(conteudo.getText().toString());
                                adapter.notifyDataSetChanged();
                                adicionarFile(conteudo.getText().toString());
                                Toast toast = Toast.makeText(getApplicationContext(), "Vídeo adicionado com sucesso!", Toast.LENGTH_SHORT);
                                toast.show();
                                dialog.dismiss();
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Erro! Vídeo ja adicionado!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } else {
                            conteudo.setError("Insira um Link!");
                            Toast toast = Toast.makeText(getApplicationContext(), "Erro! Campo vazio!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });



                listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String video_path = itemList.get(i).toString();
                        if (!(HttpCorreto (video_path))){
                            video_path = "https://" + video_path;
                        }

                        if (video_path.contains("youtube")){
                            Uri uri = Uri.parse(video_path);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                        else if(video_path.contains("youtu.be")){
                            String aux[] = video_path.split("youtu.be/");
                            String aux2 = aux[1];
                            String video_path2 = "http://www.youtube.com/watch?v="+aux2;
                            Uri uri = Uri.parse(video_path2);
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }

                    }

                });



        //update();
    }


    public void update() {

        File file = getApplicationContext().getFileStreamPath(subject + "video.txt");
        String lineFromFile;
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(subject + "video.txt")));
                lineFromFile = reader.readLine();
                while (lineFromFile != null) {
                    itemList.add(lineFromFile);
                    adapter.notifyDataSetChanged();
                    lineFromFile = reader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File does not exist!", Toast.LENGTH_SHORT).show();
        }
    }


    public void adicionarFile(String adicionada){
        try{
            FileOutputStream file = getApplicationContext().openFileOutput(subject + "video.txt",MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);
            outputStreamWriter.write(adicionada + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean HttpCorreto (String url){
        boolean resp = false;
        if ( url.contains("https://")){
            resp = true;
        }
        return resp;
    }


    public boolean naoExiste (String novaPalavra) {
        boolean resp = true;
        if (itemList.size() == 0) {
            resp = true;
        }
        else {
            for (int i = 0; i < itemList.size(); i ++) {
                if ( novaPalavra.equals(itemList.get(i).toString())) {
                    resp = false;
                }
            }
        }
        return resp;
    }
}
