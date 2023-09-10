package com.example.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 2;
    public SQLiteDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS atividades (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(255), " +
                    "descricao VARCHAR(255))");

            Log.i("INFO BD", "Tabelas criadas com sucesso");

        } catch (Exception ex) {
            Log.i("INFO BD", "Erro na criação das tabelas   " + ex.getMessage());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}