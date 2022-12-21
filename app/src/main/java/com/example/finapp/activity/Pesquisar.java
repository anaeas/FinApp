package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.finapp.R;

public class Pesquisar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(Pesquisar.this);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(Pesquisar.this);
            builder.setTitle("Qualquer ação não salva será cancelada");
            builder.setMessage("Tem certeza que deseja sair?");
            builder.setCancelable(true);

            builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                    Intent it = new Intent(Pesquisar.this, MainActivity.class);
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
