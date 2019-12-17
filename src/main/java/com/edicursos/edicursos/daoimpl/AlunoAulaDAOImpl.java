package com.edicursos.edicursos.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edicursos.edicursos.dao.AlunoAulaDAO;
import com.edicursos.edicursos.model.AlunoAula;

public class AlunoAulaDAOImpl implements AlunoAulaDAO {
	
	private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

	@Override
	public void salvar(AlunoAula alunoAula) {
		Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.save(alunoAula);
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
	public void atualizar(AlunoAula alunoAula) {
		Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.update(alunoAula);
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
	public AlunoAula consultar(Integer idAluno, Integer idAula) {
		try {
			CriteriaBuilder builder = this.session.getCriteriaBuilder();
			CriteriaQuery<AlunoAula> criteria = builder.createQuery(AlunoAula.class);
			Root<AlunoAula> root = criteria.from(AlunoAula.class);
			Predicate predicate = builder.and();
			
            predicate = builder.and(predicate, builder.equal(root.get("aluno").get("id"), idAluno));
			predicate = builder.and(predicate, builder.equal(root.get("aula").get("id"), idAula));
            
            criteria.where(predicate);
            criteria.select(root);
            
            List<AlunoAula> listaAlunoAula = this.session.createQuery(criteria).getResultList();
            if (listaAlunoAula != null && !listaAlunoAula.isEmpty()) {
            	return listaAlunoAula.get(0);
            } else {
            	return null;
            }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
