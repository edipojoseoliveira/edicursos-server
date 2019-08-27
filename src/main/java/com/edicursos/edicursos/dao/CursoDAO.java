package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.Curso;

/**
 *
 * @author edipo
 */
public interface CursoDAO {
    public void salvar(Curso curso);
    public void atualizar(Curso curso);
    public void excluir(Curso curso);
    public Curso carregar(Integer id);
}
