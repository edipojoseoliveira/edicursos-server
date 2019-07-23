package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.model.Conta;

/**
 *
 * @author edipo
 */
public interface AlunoDAO {
    public void salvar(Aluno aluno);
    public void atualizar(Aluno aluno);
    public void excluir(Integer id);
    public Aluno carregarPorConta(Conta conta);
}
