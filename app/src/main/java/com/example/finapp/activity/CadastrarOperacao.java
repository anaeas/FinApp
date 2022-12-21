package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.DateInputMask;
import com.example.finapp.MoneyTextWatcher;
import com.example.finapp.R;
import com.example.finapp.helper.OperacoesDAO;
import com.example.finapp.model.Operacoes;

import java.util.Locale;

public class CadastrarOperacao extends AppCompatActivity {

    private String selectedCategoria, selectedOperacao;
    private Spinner operacaoSpinner, categoriaSpinner;
    private ArrayAdapter<CharSequence> operacaoAdapter, categoriaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_operacao);


        //Inicialização do spinner de operacao
        operacaoSpinner = findViewById(R.id.spinner_operacoes);

        //Cria ArrayAdapter usando um string array e um spinnerlayout
        operacaoAdapter = ArrayAdapter.createFromResource(this, R.array.operacoes, R.layout.spinner_layout);

        //Seleciona o layout
        operacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Seta o adapter ao spinner para popular o spinner de operacoes
        operacaoSpinner.setAdapter(operacaoAdapter);

        //Operacao selecionada
        operacaoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Define o spinner de categorias
                categoriaSpinner = findViewById(R.id.spinner_categoria);
                //Obtem a operacao selecionada
                selectedOperacao = operacaoSpinner.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.spinner_operacoes) {
                    switch (selectedOperacao) {
                        case "Selecione uma operação":
                            categoriaAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_default_operacoes, R.layout.spinner_layout);
                            break;
                        case "Débito":
                            categoriaAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_debito, R.layout.spinner_layout);
                            break;
                        case "Crédito":
                            categoriaAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_credito, R.layout.spinner_layout);
                            break;
                        default:
                            break;
                    }
                    //Define o layout
                    categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Cria a lista de categorias de acordo com a operacao escolhida
                    categoriaSpinner.setAdapter(categoriaAdapter);

                    //Obtem a categoria do spinner
                    categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedCategoria = categoriaSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button submitButton;
        submitButton = findViewById(R.id.button_submit);
        EditText valor = findViewById(R.id.editTextValor);
        Locale mLocale = new Locale("pt", "BR");
        valor.addTextChangedListener(new MoneyTextWatcher(valor, mLocale));
        EditText data = findViewById(R.id.editTextDate);
        new DateInputMask(data);
        Spinner tvOperacaoSpinner = findViewById(R.id.spinner_operacoes);
        Spinner tvCategoriaSpinner = findViewById(R.id.spinner_categoria);

        submitButton.setOnClickListener(v -> {
            //valida se o spinner esta preenchido corretamente
            if (selectedOperacao.equals("Selecione uma operação")) {
                Toast.makeText(CadastrarOperacao.this, "Por favor, selecione uma Operação da lista", Toast.LENGTH_LONG).show();
                tvOperacaoSpinner.requestFocus();
            } else if (selectedCategoria.equals("Selecione uma categoria")) {
                Toast.makeText(CadastrarOperacao.this, "Por favor, selecione uma Categoria da lista", Toast.LENGTH_LONG).show();
                tvCategoriaSpinner.requestFocus();

            } else if (valor.length() == 0) {
                Toast.makeText(CadastrarOperacao.this, "Por favor, insira um valor", Toast.LENGTH_LONG).show();
            }
            else if (data.length() == 0) {
                Toast.makeText(CadastrarOperacao.this, "Por favor, insira uma data", Toast.LENGTH_LONG).show();
            }
            else {
                //Caso tudo ok salva no banco PENDENTE
                String operacaoString = tvOperacaoSpinner.getSelectedItem().toString();
                String categoriaString = tvCategoriaSpinner.getSelectedItem().toString();
                String valorString = valor.getText().toString();
                valorString = valorString.replace("R", "");
                valorString = valorString.replace("$", "");
                valorString = valorString.replace(",", "#");
                valorString = valorString.replace(".", "");
                valorString = valorString.replace("#", ".");
                double valorDouble = Double.parseDouble(valorString);
                String dataString = data.getText().toString();
                Operacoes operacoes = new Operacoes(operacaoString, categoriaString, valorDouble, dataString);
                OperacoesDAO operacoesDAO = new OperacoesDAO(this);
                long id = operacoesDAO.inserir(operacoes);
                Toast.makeText(this, "id " +id, Toast.LENGTH_LONG).show();
                //Mensagem de cadastro ok
                AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarOperacao.this);
                builder.setTitle("Operação cadastrada com sucesso");
                builder.setMessage("Deseja cadastrar uma nova operação");
                builder.setCancelable(true);

                builder.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent it = new Intent(CadastrarOperacao.this, MainActivity.class);
                        startActivity(it);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}