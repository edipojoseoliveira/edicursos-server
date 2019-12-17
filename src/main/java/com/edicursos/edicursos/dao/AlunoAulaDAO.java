package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.AlunoAula;

public interface AlunoAulaDAO {

	public void salvar(AlunoAula alunoAula);
	public void atualizar(AlunoAula alunoAula);
	public AlunoAula consultar(Integer idAluno, Integer idAula);
	
}
