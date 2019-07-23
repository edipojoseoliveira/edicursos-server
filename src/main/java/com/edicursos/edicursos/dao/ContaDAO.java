package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.Conta;

/**
 *
 * @author edipo
 */
public interface ContaDAO {
    public Conta entrar(String email, String senha);
}
