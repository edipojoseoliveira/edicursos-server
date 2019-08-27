package com.edicursos.edicursos.daoimpl;

import com.edicursos.edicursos.dao.CursoDAO;
import com.edicursos.edicursos.model.Curso;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author edipo
 */
public class CursoDAOImpl implements CursoDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }
    
    @Override
    public void salvar(Curso curso) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.save(curso);
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
    public void atualizar(Curso curso) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.update(curso);
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
    public void excluir(Curso curso) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.delete(curso);
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
    public Curso carregar(Integer id) {
        return this.session.get(Curso.class, id);
    }
    
}
