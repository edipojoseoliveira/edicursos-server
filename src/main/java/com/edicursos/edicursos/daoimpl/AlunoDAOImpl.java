package com.edicursos.edicursos.daoimpl;

import com.edicursos.edicursos.dao.AlunoDAO;
import com.edicursos.edicursos.model.Aluno;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author edipo
 */
public class AlunoDAOImpl implements AlunoDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }
    
    @Override
    public void salvar(Aluno aluno) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.save(aluno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) 
                transaction.rollback();
            System.out.println("Transação falhou. Erro: " + e);
        } finally {
            this.session.close();
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.update(aluno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) 
                transaction.rollback();
            System.out.println("Transação falhou. Erro: " + e);
        } finally {
            this.session.close();
        }
    }

    @Override
    public void excluir(Integer id) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.delete(id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) 
                transaction.rollback();
            System.out.println("Transação falhou. Erro: " + e);
        } finally {
            this.session.close();
        }
    }
    
}
