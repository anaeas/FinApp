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
import android.widget.ListView;
import android.widget.TextView;

import com.example.finapp.R;
import com.example.finapp.helper.OperacoesDAO;
import com.example.finapp.model.Operacoes;

import java.util.List;

public class ListaClassificada extends AppCompatActivity {
    List<Operacoes> lRecebidos;
    List<Operacoes> lGastos;
    double gastos;
    double recebidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);

        ListView lvGastos = findViewById(R.id.lvGastos);
        OperacoesDAO operacoesDao = new OperacoesDAO(this);
        lGastos = operacoesDao.listarGastos();
        ArrayAdapter<Operacoes> adapter = new ArrayAdapter<Operacoes>(this, android.R.layout.simple_list_item_1, lGastos);
        lvGastos.setAdapter(adapter);

        ListView lvRecebidos = findViewById(R.id.lvRecebidos);
        OperacoesDAO operacoesDao2 = new OperacoesDAO(this);
        lRecebidos = operacoesDao.listarRecebidos();
        ArrayAdapter<Operacoes> adapter2 = new ArrayAdapter<Operacoes>(this, android.R.layout.simple_list_item_1, lRecebidos);
        lvRecebidos.setAdapter(adapter2);

        gastos = operacoesDao.debitoTotal();
        TextView gastosView = findViewById(R.id.textViewTotalGastos);
        gastosView.setText(Double.toString(gastos));

        recebidos = operacoesDao.creditoTotal();
        TextView recebidosView = findViewById(R.id.textViewTotalRecebidos);
        recebidosView.setText(Double.toString(recebidos));

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
            AlertDialog.Builder builder = new AlertDialog.Builder(ListaClassificada.this);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(ListaClassificada.this);
            builder.setTitle("Qualquer ação não salva será cancelada");
            builder.setMessage("Tem certeza que deseja sair?");
            builder.setCancelable(true);

            builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent it = new Intent(ListaClassificada.this, MainActivity.class);
                    finishAffinity();
                    startActivity(it);
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
        return true;
    }
}