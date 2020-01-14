package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.AlunoCurso;

public interface AlunoCursoDAO {

	public void salvar(AlunoCurso alunoCurso);
	public void atualizar(AlunoCurso alunoCurso);
	public AlunoCurso consultar(Integer idCurso, Integer idAluno);
	public AlunoCurso carregar(Integer id);
	
}
