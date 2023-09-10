package com.example.listadetarefas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.dao.AtividadeDAO;
import com.example.listadetarefas.model.atividade;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);

                try {
                    // Você não precisa criar novamente a tabela aqui
                    // A criação da tabela deve ser feita apenas uma vez no SQLiteDataBaseHelper

                    // Insira as atividades (corrigindo o nome da tabela)
                    String sqlInserirAtividades = "INSERT INTO atividades (nome, descricao) VALUES " +
                            "('Atividade 1', 'Descrição da Atividade 1')," +
                            "('Atividade 2', 'Descrição da Atividade 2')," +
                            "('Atividade 3', 'Descrição da Atividade 3')";
                    AtividadeDAO dbUser = new AtividadeDAO(getApplicationContext());
                    dbUser.apagarTodasAtividades();
                    dbUser.inserir(new atividade(0, "Atividade 1", "Descrição da Atividade 1"));
                    dbUser.inserir(new atividade(0, "Atividade 2", "Descrição da Atividade 2"));
                    dbUser.inserir(new atividade(0, "Atividade 3", "Descrição da Atividade 3"));
                    Toast.makeText(MainActivity.this,
                            "Lista Atualizada com Sucesso",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,
                            "Erro na Atualização da Lista",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}