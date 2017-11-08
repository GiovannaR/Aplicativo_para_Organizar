package com.example.tp02lddm.tp2_lddm.Listas;

/**
 * Created by giovannariqueti on 07/11/17.
 */

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.tp02lddm.tp2_lddm.AbrirPDF;
import com.example.tp02lddm.tp2_lddm.R;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;




public class PDFLista extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    String subject;
    private static final int PDF_CODE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        itemList=new ArrayList<>();
        adapter=new ArrayAdapter<>(this, R.layout.list_item,R.id.lblListItem,itemList);
        final ListView listV=(ListView)findViewById(R.id.lviw);
        listV.setAdapter(adapter);


        String[] menuitem = {"https://www.ime.usp.br/~pf/teoriadosgrafos/texto/TeoriaDosGrafos.pdf"};

        /*itemList = new ArrayList<String>(Arrays.asList(menuitem));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.lblListItem, itemList);
        final ListView listview = (ListView) findViewById(R.id.lviw);
        //ListView listV=(ListView)findViewById(R.id.list);
        listview.setAdapter(adapter);
*/
        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            subject = params.getString("subject");
        }

        atualizar();

        Button addBtn = (Button) findViewById(R.id.addPDF);

        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, PDF_CODE);

            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novaI = new Intent(getApplicationContext(), AbrirPDF.class);
                novaI.putExtra("pdfNovo",itemList.get(i));
                startActivity(novaI);
            }
        });

    }
    public void atualizar(){
        File file = getApplicationContext().getFileStreamPath(subject + "pdff.txt");
        String lineFromFile;
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(subject + "pdff.txt")));
                lineFromFile = reader.readLine();
                while (lineFromFile != null) {
                    itemList.add(lineFromFile);
                    adapter.notifyDataSetChanged();
                    lineFromFile = reader.readLine();
                }
            }
            catch(Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "File does not exist!", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode == RESULT_OK) && (requestCode == PDF_CODE)){
            String uri = data.getData().toString();

            try {
                itemList.add(uri);
                adicionarArquivo(uri);
                adapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(getApplicationContext(), "Pdf adicionado com sucesso!", Toast.LENGTH_SHORT);
                toast.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public void adicionarArquivo (String uri){
        try{
            FileOutputStream file = openFileOutput(subject + "pdff.txt",MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);
            outputStreamWriter.write(uri + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }

    }

}
