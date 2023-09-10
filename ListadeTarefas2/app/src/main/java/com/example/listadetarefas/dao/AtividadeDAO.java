package com.example.listadetarefas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.listadetarefas.helper.SQLiteDataBaseHelper;
import com.example.listadetarefas.model.atividade;
import java.util.ArrayList;
import java.util.List;

public class AtividadeDAO implements IAtividadeDAO {
    private SQLiteDatabase objwrite;
    private SQLiteDatabase objRead;
    public AtividadeDAO(Context ctx){
        SQLiteDataBaseHelper db = new SQLiteDataBaseHelper(ctx);
        objwrite= db.getWritableDatabase();
        objRead= db.getReadableDatabase();
    }
    @Override
    public boolean inserir(atividade atv) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", atv.getNome());
            contentValues.put("descricao", atv.getDescricao());
            long resultado = objwrite.insert("atividades", null, contentValues);
            Log.i("DB INFO", "dados inseridos com sucesso ");
            return true;
        } catch (Exception ex) {
            Log.i("INFO DB", "falha na insercao dos dados " + ex.getMessage());
            return false;
        }
    }
    @Override
    public boolean atualizar(int id,String task) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", task);

            String[] whereArgs = {String.valueOf(id)};
            int resultado = objwrite.update("atividades", contentValues, "ID=?", whereArgs);

            if (resultado > 0) {
                Log.i("DB INFO", "Tarefa atualizada com sucesso");
                return true;
            } else {
                Log.i("DB INFO", "Nenhuma tarefa foi atualizada");
                return false;
            }
        } catch (Exception ex) {
            Log.i("INFO DB", "Falha na atualização dos dados " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            String[] whereArgs = {String.valueOf(id)};
            int resultado = objwrite.delete("atividades", "ID=?", whereArgs);
            if (resultado > 0) {
                // Exclusão bem-sucedida
                Log.i("DB INFO", "Atividade excluída com sucesso");
                return true;
            } else {
                // Nenhuma linha foi excluída, indica que a tarefa não existe
                Log.i("DB INFO", "Nenhuma Atividade foi excluída");
                return false;
            }
        } catch (Exception ex) {
            Log.i("INFO DB", "Falha na exclusão da Atividade " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<atividade> listarAtividades() {
        List<atividade> listaAtividades = new ArrayList<>();
        try {
            Cursor cursor = objRead.query("atividades", null, null, null, null, null, null);
            int iid = cursor.getColumnIndexOrThrow("id");
            int inome = cursor.getColumnIndexOrThrow("nome");
            int idescricao = cursor.getColumnIndexOrThrow("descricao");
            cursor.moveToFirst();
            do {
                if (cursor.getCount() == 0) {
                    break;
                }
                atividade atv = new atividade();
                atv.setId(Integer.valueOf(cursor.getString(iid)));
                atv.setNome(cursor.getString(inome));
                atv.setDescricao(cursor.getString(idescricao));
                listaAtividades.add(atv);

            } while (cursor.moveToNext());
            Log.i("INFO DB", "sucesso na listagem ");
        } catch (Exception ex) {
            Log.i("INFO DB", "falha ao listar " + ex.getMessage());
            return null;
        }
        return listaAtividades;
    }

    public void apagarTodasAtividades() {
        try {
            // Execute o comando SQL para apagar todos os dados da tabela
            String sql = "DELETE FROM atividades";
            objwrite.execSQL(sql);
            Log.i("INFO DB", "Todas as atividades foram apagadas com sucesso");
        } catch (Exception ex) {
            Log.i("INFO DB", "Falha na exclusão de todas as atividades " + ex.getMessage());
        }
    }
}