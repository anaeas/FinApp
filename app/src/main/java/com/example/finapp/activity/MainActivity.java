package com.example.finapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrarOperacao(View view) {
        Intent it = new Intent(this, CadastrarOperacao.class);
        startActivity(it);
    }

    public void extrato(View view) {
        Intent it = new Intent(this, Extrato.class);
        startActivity(it);
    }

    public void pesquisar(View view) {
        Intent it = new Intent(this, Pesquisar.class);
        startActivity(it);
    }

    public void listaClassificada(View view) {
        Intent it = new Intent(this, ListaClassificada.class);
        startActivity(it);
    }

    public void sair(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Sair irá encerrar a aplicação");
        builder.setMessage("Tem certeza que deseja sair?");
        builder.setCancelable(true);

        builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(1);
            }
        });

        builder.setPositiveButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}