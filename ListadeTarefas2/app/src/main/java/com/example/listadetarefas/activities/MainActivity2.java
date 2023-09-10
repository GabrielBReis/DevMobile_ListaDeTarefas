package com.example.listadetarefas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.listadetarefas.R;
import com.example.listadetarefas.dao.AtividadeDAO;
import com.example.listadetarefas.model.atividade;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView lstAtividades;
    private Button btnAtualizar, btnAdicionar;
    private ArrayAdapter<atividade> adaptador; // Adicionado o adaptador para atualizar a lista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lstAtividades = findViewById(R.id.lstAtividades);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Atualize a lista de atividades chamando o método ListaUsuarios
                ListaUsuarios();
                Toast.makeText(MainActivity2.this,
                        "Lista Atualizada com Sucesso",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent);
            }
        });

        lstAtividades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<atividade> adapter = (ArrayAdapter<atividade>) lstAtividades.getAdapter();
                atividade selected = adapter.getItem(position);

                AtividadeDAO dbUser = new AtividadeDAO(getApplicationContext());

                boolean sucesso = dbUser.deletar(selected.getId());

                if (sucesso) {
                    Toast.makeText(MainActivity2.this,
                            "Atividade Excluída com Sucesso",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this,
                            "Erro ao Excluir Atividade",
                            Toast.LENGTH_SHORT).show();
                }

                ListaUsuarios(); // Atualize a lista após a exclusão
                return true;
            }
        });


        // Chame o método ListaUsuarios no início da criação da atividade
        ListaUsuarios();
    }

    public void ListaUsuarios() {
        AtividadeDAO dbUser = new AtividadeDAO(getApplicationContext());
        List<atividade> lista = dbUser.listarAtividades();

        // Atualize o adaptador da lista com os novos dados
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                lista
        );
        lstAtividades.setAdapter(adaptador);
    }
}

