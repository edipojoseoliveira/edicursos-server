package com.edicursos.edicursos.rn;

import com.edicursos.edicursos.dao.AlunoCursoDAO;
import com.edicursos.edicursos.model.AlunoCurso;
import com.edicursos.edicursos.util.DAOFactory;

public class AlunoCursoRN {

	private AlunoCursoDAO alunoCursoDAO;
	
	public AlunoCursoRN() {
		this.alunoCursoDAO = DAOFactory.criarAlunoCursoDAO();
	}
	
	public AlunoCurso consultar(Integer idCurso, Integer idAluno) {
		return this.alunoCursoDAO.consultar(idCurso, idAluno);
	}
	
	public void salvar(AlunoCurso alunoCurso) {
		if (alunoCurso.getId() == null || alunoCurso.getId() == -1) {
            this.alunoCursoDAO.salvar(alunoCurso);
        } else {
            this.alunoCursoDAO.atualizar(alunoCurso);
        }
	}
	
}
