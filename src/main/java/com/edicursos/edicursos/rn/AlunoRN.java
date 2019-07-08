package com.edicursos.edicursos.rn;

import com.edicursos.edicursos.dao.AlunoDAO;
import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.util.DAOFactory;

/**
 *
 * @author edipo
 */
public class AlunoRN {
    
    private AlunoDAO alunoDAO;

    public AlunoRN() {
        this.alunoDAO = DAOFactory.criarAlunoDAO();
    }
    
    public void salvar(Aluno aluno) {
        if (aluno.getId() == null || aluno.getId() == -1) {
            this.alunoDAO.salvar(aluno);
        } else {
            this.alunoDAO.atualizar(aluno);
        }
    }
    
    public void excluir(Integer id) {
        this.alunoDAO.excluir(id);
    }
    
}
