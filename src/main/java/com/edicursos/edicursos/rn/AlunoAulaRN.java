package com.edicursos.edicursos.rn;

import com.edicursos.edicursos.dao.AlunoAulaDAO;
import com.edicursos.edicursos.model.AlunoAula;
import com.edicursos.edicursos.util.DAOFactory;

public class AlunoAulaRN {

	private AlunoAulaDAO alunoAulaDAO;
	
	public AlunoAulaRN() {
		this.alunoAulaDAO = DAOFactory.criarAlunoAulaDAO();
	}
	
	public AlunoAula consultar(Integer idAluno, Integer idAula) {
		return this.alunoAulaDAO.consultar(idAluno, idAula);
	}
	
	public void salvar(AlunoAula alunoAula) {
		if (alunoAula.getId() == null || alunoAula.getId() == -1) {
            this.alunoAulaDAO.salvar(alunoAula);
        } else {
            this.alunoAulaDAO.atualizar(alunoAula);
        }
	}
	
}
