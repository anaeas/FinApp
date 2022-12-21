package com.example.finapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.model.Operacoes;

import java.util.ArrayList;
import java.util.List;

public class OperacoesDAO {

    private DBHelper con;
    private SQLiteDatabase db;

    public OperacoesDAO(Context context) {
        con = new DBHelper(context);
        db = con.getWritableDatabase();
    }

    public long inserir(Operacoes operacoes) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("tipo", operacoes.getTipo());
        contentValues.put("categoria", operacoes.getCategoria());
        contentValues.put("valor", operacoes.getValor());
        contentValues.put("data", operacoes.getData());

        return db.insert("operacao", null, contentValues);
    }

    public List<Operacoes> listar() {
        List<Operacoes> lista = new ArrayList<Operacoes>();
        Cursor cursor = db.rawQuery("SELECT * FROM operacao ORDER BY data DESC LIMIT 15", null);

        while(cursor.moveToNext()) {
            lista.add(new Operacoes(cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4)));
        }
        return lista;
    }

    public double creditoTotal() {
        Cursor cursor = db.rawQuery("SELECT SUM(valor) as credito FROM operacao WHERE tipo='Crédito'", null);
        Double credito = 0d;
        while (cursor.moveToNext()) {
            credito = cursor.getDouble(0);
        }
        return credito;
    }

    public double debitoTotal() {
        Cursor cursor = db.rawQuery("SELECT SUM(valor) as debito FROM operacao WHERE tipo='Débito'", null);
        Double debito = 0d;
        while (cursor.moveToNext()) {
            debito = cursor.getDouble(0);
        }
        return debito;
    }

    public double saldo() {

        Double credito = creditoTotal();
        Double debito = debitoTotal();

        Double saldo = (credito-debito);

        Log.i("debito", debito.toString());
        Log.i("credito", credito.toString());
        Log.i("saldo", saldo.toString());

        return saldo;

    }

    public List<Operacoes> listarGastos() {
        List<Operacoes> lista = new ArrayList<Operacoes>();
        Cursor cursor = db.rawQuery("SELECT * FROM operacao WHERE tipo='Débito'", null);

        while(cursor.moveToNext()) {
            lista.add(new Operacoes(cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4)));
            Log.i("lista", lista.toString());
        }
        return lista;
    }

    public List<Operacoes> listarRecebidos() {
        List<Operacoes> lista = new ArrayList<Operacoes>();
        Cursor cursor = db.rawQuery("SELECT * FROM operacao WHERE tipo='Crédito'", null);

        while(cursor.moveToNext()) {
            lista.add(new Operacoes(cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4)));
            Log.i("lista", lista.toString());
        }
        return lista;
    }




}
