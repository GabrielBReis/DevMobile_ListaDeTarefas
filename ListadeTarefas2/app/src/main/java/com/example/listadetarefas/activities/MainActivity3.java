package com.example.listadetarefas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.dao.AtividadeDAO;
import com.example.listadetarefas.model.atividade;

public class MainActivity3 extends AppCompatActivity {

    private Button btnCancelar, btnSalvar;
    private EditText edtNome, edtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnSalvar = findViewById(R.id.btnSalvar);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtNome = findViewById(R.id.edtNome);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Voltar para MainActivity2
                onBackPressed();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String descricao = edtDescricao.getText().toString();

                if (!nome.isEmpty() && !descricao.isEmpty()) {
                    AtividadeDAO dbUser = new AtividadeDAO(getApplicationContext());
                    atividade atv = new atividade();
                    atv.setNome(nome);
                    atv.setDescricao(descricao);
                    dbUser.inserir(atv);
                    limpacampos();
                    Toast.makeText(MainActivity3.this,
                            "Atividade Salva com Sucesso",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this,
                            "Preencha todos os campos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void limpacampos() {
        edtNome.setText("");
        edtDescricao.setText("");
    }
}
