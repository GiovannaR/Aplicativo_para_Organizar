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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp02lddm.tp2_lddm.Fragments.FirstFragment;
import com.example.tp02lddm.tp2_lddm.Fragments.SecondFragment;
import com.example.tp02lddm.tp2_lddm.Fragments.ThirdFragment;
import com.example.tp02lddm.tp2_lddm.data.TestUtil;
import com.example.tp02lddm.tp2_lddm.data.Tp2Contract;
import com.example.tp02lddm.tp2_lddm.data.Tp2DbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SQLiteDatabase mDb;

    ArrayList subjects = new ArrayList<>(Arrays.asList("LDDM", "Grafos", "AED 2"));
    //ArrayList<String> anotherSubjects = new ArrayList<String>();
    LinkedList<String> anotherSubjects = new LinkedList<>(Arrays.asList("Banco de Dados", "Calculo I", "Cálculo II", "Cálculo III", "Algebra Linear", "Matemática Discreta", "Estatística", "AED 1", "AED 3", "LP", "PAA", "IA", "Compiladores", "PID", "Comp. Paralela", "Comp. Gráfica", "Otimização"));
    Menu menu = null;
    SubMenu pdfMenu = null;
    SubMenu linkMenu = null;
    SubMenu videoMenu = null;
    Dialog mydialog;
    Bundle params = new Bundle();
    String materia;





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

    public void botao (){

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

                addBtn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        if(!(materia.getText().toString().isEmpty())){

                            //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                             //menu = navigationView.getMenu();

                            String sub = materia.getText().toString();

                            linkMenu.add(sub).setOnMenuItemClickListener(onMenuItemClick("LINK"));
                            pdfMenu.add(sub).setOnMenuItemClickListener(onMenuItemClick("PDF"));
                            videoMenu.add(sub).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));

                            Toast toast = Toast.makeText(getApplicationContext(), "Matéria adicionada com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();

                        }else{
                            materia.setError("Insira uma matéria");
                            Toast toast = Toast.makeText(getApplicationContext(), "Matéria adicionada com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                //startActivity(new Intent(MainActivity.this, Pop.class));
                //TextView Aleatorio = (TextView) findViewById(R.id.aleatorio);
                //try {
                //MyDialogC();
                //}catch( Exception io){
                //      Aleatorio.setText("Tamanho" + io.getMessage());
                //}
                //  Aleatorio.setText("Tamanho" + materia);

                //materia = "AA";
                // if (materia.length() > 0){

                //String subject = anotherSubjects.poll();
                //String subject = "AAA";


                //Snackbar.make(view, "Matéria adicionada com sucesso!", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
                //}*/


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

    public void inicializacao (){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        pdfMenu = menu.addSubMenu("PDF");
        linkMenu = menu.addSubMenu("LINK");
        videoMenu = menu.addSubMenu("VIDEO");

        for (int i = 0; i < subjects.size(); i++) {
            pdfMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("PDF"));
            linkMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("LINK"));
            videoMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
        }
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
                        FirstFragment firstFragment = new FirstFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        firstFragment.setArguments(args);
                        ft.replace(R.id.content_frame, firstFragment);
                        break;
                    case "LINK":
                        SecondFragment secondFragment = new SecondFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        secondFragment.setArguments(args);
                        ft.replace(R.id.content_frame, secondFragment);
                        break;
                    case "VIDEO":
                        ThirdFragment thirdFragment = new ThirdFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        thirdFragment.setArguments(args);
                        ft.replace(R.id.content_frame, thirdFragment);
                        break;
                }

                ft.commit();
                return false;
            }
        };
    }

   /* public void MyDialogC(){
        mydialog = new Dialog(MainActivity.this);
        mydialog.setContentView(R.layout.customedialog);
        mydialog.setTitle("Qual matéria deseja adicioinar?");

        Button close = (Button)mydialog.findViewById(R.id.close);

        //Button salvar = (Button)mydialog.findViewById(R.id.confirm);

        //final TextView texto= mydialog.findViewById(R.id.textq);
        final EditText mnova = (EditText)mydialog.findViewById(R.id.mnova);

        //salvar.setEnabled(true);
        close.setEnabled(true);

        /*salvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                    materia = mnova.getText().toString();
                    //texto.setText(anotherSubjects.get(0));
            }
        });


        close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                materia = mnova.getText().toString();
                mydialog.cancel();
            }
        });
        mydialog.show();
    }
*/

    private Cursor getAllGuests(){
        return mDb.query(
                Tp2Contract.SubjectEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Tp2Contract.SubjectEntry.COLUMN_TIMESTAMP);

    }
}


