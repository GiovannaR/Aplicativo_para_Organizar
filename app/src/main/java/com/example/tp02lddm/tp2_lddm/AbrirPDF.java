package com.example.tp02lddm.tp2_lddm;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by giovannariqueti on 07/11/17.
 */

public class AbrirPDF extends AppCompatActivity {

    String endereco;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pdf);

        Bundle getInfo = getIntent().getExtras();
        endereco = getInfo.getString("pdfNovo");

        PDFView pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromUri(Uri.parse(endereco)).load();
    }
}
