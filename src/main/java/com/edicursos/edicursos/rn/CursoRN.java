package com.edicursos.edicursos.rn;

import com.edicursos.edicursos.dao.CursoDAO;
import com.edicursos.edicursos.model.AtividadeQuestao;
import com.edicursos.edicursos.model.Aula;
import com.edicursos.edicursos.model.Curso;
import com.edicursos.edicursos.util.DAOFactory;

/**
 *
 * @author edipo
 */
public class CursoRN {
    
    private CursoDAO cursoDAO;

    public CursoRN() {
        this.cursoDAO = DAOFactory.criarCursoDAO();
    }
    
    public void salvar(Curso curso) {
        if (curso.getAulas() != null) {
            for (int i = 0; i < curso.getAulas().size(); i++) {
                Aula aula = curso.getAulas().get(i);
                if (aula.getCurso() == null) {
                    aula.setCurso(curso);
                }
                if (aula.getQuestoes() != null) {
                    for (int j = 0; j < aula.getQuestoes().size(); j++) {
                        AtividadeQuestao questao = aula.getQuestoes().get(j);
                        if (questao.getAula() == null) {
                            questao.setAula(aula);
                        }
                        
                        aula.getQuestoes().set(j, questao);
                    }
                }
                
                curso.getAulas().set(i, aula);
            }
        }
        
        if (curso.getId() == null) {
            this.cursoDAO.salvar(curso);
        } else {
            this.cursoDAO.atualizar(curso);
        }
    }
    
    public void excluir(Integer id) {
        if (id != null) {
            Curso curso = this.carregar(id);
            if (curso != null) {
                this.cursoDAO.excluir(curso);
            }
        }
    }
    
    public Curso carregar(Integer id) {
        return this.cursoDAO.carregar(id);
    }
    
}
