package com.edicursos.edicursos.util;

import com.edicursos.edicursos.dao.AlunoDAO;
import com.edicursos.edicursos.daoimpl.AlunoDAOImpl;

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
    
}
