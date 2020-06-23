package com.bruno.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruno.domain.Cliente;

@Component
public class ClienteRepositoryImpl implements ClienteRepositoryCustom{
	
	@Autowired
	private EntityManager em;

	@Override
	public Optional<Cliente> buscarClientePorEmail(String email) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);

		Root<Cliente> cliente = query.from(Cliente.class);
		Predicate emailCliente = null;
		if (email != null) {
			emailCliente = builder.equal(cliente.get("email"), email);
		}
		query.where(emailCliente);
		query.distinct(true);

		TypedQuery<Cliente> clienteQuery = em.createQuery(query);
		clienteQuery.setFirstResult(0);
		clienteQuery.setMaxResults(1);

		Cliente cliQueryResult = clienteQuery.getResultList().get(0);

		if (cliQueryResult != null) {
			return Optional.of(cliQueryResult);
		}

		return Optional.empty();

		/*
		 * Root<Book> book = cq.from(Book.class); Predicate authorNamePredicate =
		 * cb.equal(book.get("author"), authorName); Predicate titlePredicate =
		 * cb.like(book.get("title"), "%" + title + "%"); cq.where(authorNamePredicate,
		 * titlePredicate);
		 */

	}
	

}
