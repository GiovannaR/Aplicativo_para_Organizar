package com.example.tp02lddm.tp2_lddm;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp02lddm.tp2_lddm.Fragments.FirstFragment;
import com.example.tp02lddm.tp2_lddm.Fragments.SecondFragment;
import com.example.tp02lddm.tp2_lddm.Fragments.ThirdFragment;
import com.example.tp02lddm.tp2_lddm.data.TestUtil;
import com.example.tp02lddm.tp2_lddm.data.Tp2Contract;
import com.example.tp02lddm.tp2_lddm.data.Tp2DbHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SQLiteDatabase mDb;

    ArrayList subjects = new ArrayList<>(Arrays.asList("LDDM", "Grafos", "AED 2"));
    LinkedList<String> anotherSubjects = new LinkedList<>(Arrays.asList("Banco de Dados", "Calculo I", "Cálculo II", "Cálculo III", "Algebra Linear", "Matemática Discreta", "Estatística", "AED 1", "AED 3", "LP", "PAA", "IA", "Compiladores", "PID", "Comp. Paralela", "Comp. Gráfica", "Otimização"));
    Menu menu = null;
    SubMenu pdfMenu = null;
    SubMenu linkMenu = null;
    SubMenu videoMenu = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inicializacao();



        /*Tp2DbHelper dbHelper = new Tp2DbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        TestUtil.insertFakeData(mDb);
*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        botao();

    }

    public void botao() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.customedialog, null);
                final EditText materia = (EditText) mview.findViewById(R.id.mnova);
                Button addBtn = (Button) mview.findViewById(R.id.close);
                mBuilder.setView(mview);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                addBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (!(materia.getText().toString().isEmpty())) {

                            //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                            //menu = navigationView.getMenu();

                            String sub = materia.getText().toString();
                            if(naoExistente(sub)){
                                addArquivo(sub);
                                linkMenu.add(sub).setOnMenuItemClickListener(onMenuItemClick("LINK"));
                                pdfMenu.add(sub).setOnMenuItemClickListener(onMenuItemClick("PDF"));
                                videoMenu.add(sub).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));

                                Toast toast = Toast.makeText(getApplicationContext(), "Matéria adicionada com sucesso", Toast.LENGTH_SHORT);
                                toast.show();
                                dialog.dismiss();
                            }



                        } else {
                            materia.setError("Insira uma matéria");
                            Toast toast = Toast.makeText(getApplicationContext(), "Matéria adicionada com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void inicializacao() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        pdfMenu = menu.addSubMenu("PDF");
        linkMenu = menu.addSubMenu("LINK");
        videoMenu = menu.addSubMenu("VIDEO");
        update();

        /*for (int i = 0; i < subjects.size(); i++) {
            pdfMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("PDF"));
            linkMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("LINK"));
            videoMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
        }*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public MenuItem.OnMenuItemClickListener onMenuItemClick(final String fileType) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();

                switch (fileType) {
                    case "PDF":
                        ThirdFragment thirdfragment = new ThirdFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        thirdfragment.setArguments(args);
                        ft.replace(R.id.content_frame, thirdfragment);
                        break;
                    case "LINK":
                        SecondFragment secondFragment = new SecondFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        secondFragment.setArguments(args);
                        ft.replace(R.id.content_frame, secondFragment);
                        break;
                    case "VIDEO":
                        FirstFragment firstFragment = new FirstFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        firstFragment.setArguments(args);
                        ft.replace(R.id.content_frame, firstFragment);
                        break;
                }

                ft.commit();
                return false;
            }
        };
    }


    public void update(){
        File file = getApplicationContext().getFileStreamPath("materia.txt");
        String linha;

        if ( file.exists( ) ){
            try{
                BufferedReader leitor = new BufferedReader(new InputStreamReader(openFileInput("materia.txt")));
                linha = leitor.readLine();

                while (linha != null) {
                    pdfMenu.add(linha).setOnMenuItemClickListener(onMenuItemClick("PDF"));
                    linkMenu.add(linha).setOnMenuItemClickListener(onMenuItemClick("LINK"));
                    videoMenu.add(linha).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
                    linha = leitor.readLine();
                }

            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "Erro salving file!", Toast.LENGTH_LONG).show();
            }

        }else{
            try{
                FileOutputStream fos = openFileOutput("materias.txt", MODE_APPEND);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
                outputStreamWriter.write("LDDM\n");
                outputStreamWriter.write("Grafos\n");
                outputStreamWriter.write("AED 2\n");
                outputStreamWriter.flush();
                outputStreamWriter.close();
                pdfMenu.add("LDDM").setOnMenuItemClickListener(onMenuItemClick("PDF"));
                pdfMenu.add("Grafos").setOnMenuItemClickListener(onMenuItemClick("PDF"));
                pdfMenu.add("AED 2").setOnMenuItemClickListener(onMenuItemClick("PDF"));

                linkMenu.add("LDDM").setOnMenuItemClickListener(onMenuItemClick("LINK"));
                linkMenu.add("Grafos").setOnMenuItemClickListener(onMenuItemClick("LINK"));
                linkMenu.add("AED 2").setOnMenuItemClickListener(onMenuItemClick("LINK"));

                videoMenu.add("LDDM").setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
                videoMenu.add("Grafos").setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
                videoMenu.add("AED 2").setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public void addArquivo (String adicionada){
        try{
            FileOutputStream file = openFileOutput("materias.txt",MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);
            outputStreamWriter.write(adicionada + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean naoExistente (String adicionada){
        boolean resp = true;
        if (pdfMenu.size() == 0 || linkMenu.size() == 0 || videoMenu.size() == 0 ) {
            resp = true;
        }
        else {
            for (int i = 0; i < pdfMenu.size(); i ++) {
                if ( adicionada.equals(pdfMenu.getItem(i).getTitle().toString())) {
                    resp = false;
                }
            }
            for (int i = 0; i < linkMenu.size(); i ++) {
                if ( adicionada.equals(linkMenu.getItem(i).getTitle().toString())) {
                    resp = false;
                }
            }
            for (int i = 0; i < videoMenu.size(); i ++) {
                if ( adicionada.equals(videoMenu.getItem(i).getTitle().toString())) {
                    resp = false;
                }
            }
        }
        return resp;
    }
}


