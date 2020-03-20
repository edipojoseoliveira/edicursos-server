package com.edicursos.edicursos.daoimpl;

import com.edicursos.edicursos.dao.AlunoDAO;
import com.edicursos.edicursos.model.Aluno;
import com.edicursos.edicursos.model.Conta;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public void excluir(Aluno aluno) {
        Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.delete(aluno);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) 
                transaction.rollback();
            System.out.println("Transação falhou. Erro: " + e);
        } finally {
            this.session.close();
        }
    }

    public Aluno carregarPorConta(Conta conta) {
        try {
            CriteriaBuilder builder = this.session.getCriteriaBuilder();
            CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class);
            Root<Aluno> root = criteria.from(Aluno.class);
            Predicate predicate;
            
            predicate = builder.equal(root.get("conta"), conta);
            
            criteria.where(predicate);
            criteria.select(root);
            Aluno aluno = this.session.createQuery(criteria).setMaxResults(1).getSingleResult();
            return aluno;
        } catch (Exception e) {
            System.out.println("Consulta falhou. Erro: " + e);
            return null;
        }
    }
    
}
