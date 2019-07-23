package com.edicursos.edicursos.rn;

import com.edicursos.edicursos.dao.ContaDAO;
import com.edicursos.edicursos.model.Conta;
import com.edicursos.edicursos.util.DAOFactory;

/**
 *
 * @author edipo
 */
public class ContaRN {
    
    private ContaDAO contaDAO;

    public ContaRN() {
        this.contaDAO = DAOFactory.criarContaDAO();
    }
    
    public Conta entrar(String email, String senha) {
        return this.contaDAO.entrar(email, senha);
    }
    
}
