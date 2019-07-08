package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.Aluno;

/**
 *
 * @author edipo
 */
public interface AlunoDAO {
    public void salvar(Aluno aluno);
    public void atualizar(Aluno aluno);
    public void excluir(Integer id);
}
