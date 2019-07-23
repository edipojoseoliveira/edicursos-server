package com.edicursos.edicursos.util;

import com.edicursos.edicursos.dao.AlunoDAO;
import com.edicursos.edicursos.dao.ContaDAO;
import com.edicursos.edicursos.daoimpl.AlunoDAOImpl;
import com.edicursos.edicursos.daoimpl.ContaDAOImpl;

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
    
}
