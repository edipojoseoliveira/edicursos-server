package com.edicursos.edicursos.util;

import com.edicursos.edicursos.dao.AlunoAulaDAO;
import com.edicursos.edicursos.dao.AlunoCursoDAO;
import com.edicursos.edicursos.dao.AlunoDAO;
import com.edicursos.edicursos.dao.ContaDAO;
import com.edicursos.edicursos.dao.CursoDAO;
import com.edicursos.edicursos.daoimpl.AlunoAulaDAOImpl;
import com.edicursos.edicursos.daoimpl.AlunoCursoDAOImpl;
import com.edicursos.edicursos.daoimpl.AlunoDAOImpl;
import com.edicursos.edicursos.daoimpl.ContaDAOImpl;
import com.edicursos.edicursos.daoimpl.CursoDAOImpl;

/**
 *
 * @author edipo
 */
public class DAOFactory {
    
    public static AlunoDAO criarAlunoDAO() {
        AlunoDAOImpl alunoDAOImpl = new AlunoDAOImpl();
        alunoDAOImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        return alunoDAOImpl;
    }
    
    public static ContaDAO criarContaDAO() {
        ContaDAOImpl contaDAOImpl = new ContaDAOImpl();
        contaDAOImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        return contaDAOImpl;
    }
    
    public static CursoDAO criarCursoDAO() {
        CursoDAOImpl cursoDAOImpl = new CursoDAOImpl();
        cursoDAOImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        return cursoDAOImpl;
    }
    
    public static AlunoCursoDAO criarAlunoCursoDAO() {
        AlunoCursoDAOImpl alunoCursoDAOImpl = new AlunoCursoDAOImpl();
        alunoCursoDAOImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        return alunoCursoDAOImpl;
    }
    
    public static AlunoAulaDAO criarAlunoAulaDAO() {
        AlunoAulaDAOImpl alunoAulaDAOImpl = new AlunoAulaDAOImpl();
        alunoAulaDAOImpl.setSession(HibernateUtil.getSessionFactory().openSession());
        return alunoAulaDAOImpl;
    }
    
}
