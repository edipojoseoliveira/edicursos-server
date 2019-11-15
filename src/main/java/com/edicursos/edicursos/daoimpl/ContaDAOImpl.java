package com.edicursos.edicursos.daoimpl;

import com.edicursos.edicursos.dao.ContaDAO;
import com.edicursos.edicursos.model.Conta;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author edipo
 */
public class ContaDAOImpl implements ContaDAO {
    
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Conta entrar(String email, String senha) {
        try {
            CriteriaBuilder builder = this.session.getCriteriaBuilder();
            CriteriaQuery<Conta> criteria = builder.createQuery(Conta.class);
            Root<Conta> root = criteria.from(Conta.class);
            Predicate predicate;
            
            predicate = builder.equal(root.get("email"), email);
            predicate = builder.and(predicate, builder.equal(root.get("senha"), senha));
            
            criteria.where(predicate);
            criteria.select(root);
            Conta conta = this.session.createQuery(criteria).setMaxResults(1).getSingleResult();
            return conta;
        } catch (Exception e) {
            System.out.println("Consulta falhou. Erro: " + e);
            return null;
        }
    }

	@Override
	public Conta carregarPorEmail(String email) {
		try {
			CriteriaBuilder builder = this.session.getCriteriaBuilder();
            CriteriaQuery<Conta> criteria = builder.createQuery(Conta.class);
            Root<Conta> root = criteria.from(Conta.class);
            Predicate predicate;
            
            predicate = builder.equal(root.get("email"), email);
            
            criteria.where(predicate);
            criteria.select(root);
            Conta conta = this.session.createQuery(criteria).setMaxResults(1).getSingleResult();
            return conta;
		} catch (Exception e) {
			System.out.println("Consulta falhou. Erro: " + e);
            return null;
		}
	}
    
}
