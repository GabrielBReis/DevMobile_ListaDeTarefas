package com.example.listadetarefas.dao;

import com.example.listadetarefas.model.atividade;

import java.util.List;

public interface IAtividadeDAO {
    public boolean inserir(atividade atv);
    public boolean atualizar(int id,String atv);
    public boolean deletar(int id);
    List<atividade> listarAtividades();
    public void apagarTodasAtividades();
}
