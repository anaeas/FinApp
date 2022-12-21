package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finapp.R;
import com.example.finapp.helper.OperacoesDAO;
import com.example.finapp.model.Operacoes;

import java.util.List;

public class Extrato extends AppCompatActivity {

    List<Operacoes> lOperacoes;
    double credito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);
        //Preenche a listView
        ListView lvOperacoes = findViewById(R.id.listViewExtrato);
        OperacoesDAO operacoesDao = new OperacoesDAO(this);
        lOperacoes = operacoesDao.listar();

        ArrayAdapter<Operacoes> adapter = new ArrayAdapter<Operacoes>(this, android.R.layout.simple_list_item_1, lOperacoes);
        lvOperacoes.setAdapter(adapter);
        //Mostra o saldo
        credito = operacoesDao.saldo();
        TextView saldo = findViewById(R.id.textViewSaldo);
        saldo.setText(Double.toString(credito));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sair) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Extrato.this);
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
        else if (id == R.id.home) {
            finishAffinity();
            Intent it = new Intent(Extrato.this, MainActivity.class);
            startActivity(it);
        }
        return true;
    }
}