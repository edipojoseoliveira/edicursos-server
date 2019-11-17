package com.edicursos.edicursos.dao;

import com.edicursos.edicursos.model.Conta;

/**
 *
 * @author edipo
 */
public interface ContaDAO {
	public void salvar(Conta conta);
	public void atualizar(Conta conta);
    public Conta entrar(String email, String senha);
    public Conta carregarPorEmail(String email);
}
