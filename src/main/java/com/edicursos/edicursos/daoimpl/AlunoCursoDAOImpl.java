package com.edicursos.edicursos.daoimpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.edicursos.edicursos.dao.AlunoCursoDAO;
import com.edicursos.edicursos.model.AlunoCurso;

public class AlunoCursoDAOImpl implements AlunoCursoDAO {

	private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
	public AlunoCurso consultar(Integer idCurso, Integer idAluno) {
		try {
			CriteriaBuilder builder = this.session.getCriteriaBuilder();
			CriteriaQuery<AlunoCurso> criteria = builder.createQuery(AlunoCurso.class);
			Root<AlunoCurso> root = criteria.from(AlunoCurso.class);
			Predicate predicate = builder.and();
			
			predicate = builder.and(predicate, builder.equal(root.get("curso").get("id"), idCurso));
            predicate = builder.and(predicate, builder.equal(root.get("aluno").get("id"), idAluno));
            
            criteria.where(predicate);
            criteria.select(root);
            
            List<AlunoCurso> listaAlunoCurso = this.session.createQuery(criteria).getResultList();
            if (listaAlunoCurso != null && !listaAlunoCurso.isEmpty()) {
            	return listaAlunoCurso.get(0);
            } else {
            	return null;
            }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
	public void salvar(AlunoCurso alunoCurso) {
		Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.save(alunoCurso);
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
	public void atualizar(AlunoCurso alunoCurso) {
		Transaction transaction = null;
        try {
            transaction = this.session.beginTransaction();
            this.session.update(alunoCurso);
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
	public AlunoCurso carregar(Integer id) {
		return this.session.get(AlunoCurso.class, id);
	}

}
