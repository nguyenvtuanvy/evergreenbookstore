package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO<E> {
	private static EntityManagerFactory entityManagerFactory;

	public JpaDAO() {
	}

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookshop");
	}

	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();

		entityManager.close();
		return entity;
	}

	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();

		entityManager.close();
		return entity;
	}

	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		E entity = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);
		}

		entityManager.close();
		return entity;
	}

	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Object refenrence = entityManager.getReference(type, id);
		entityManager.remove(refenrence);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<E> findwithnamequery(String queryname) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryname);
		List<E> result = query.getResultList();

		entityManager.close();
		return result;
	}

	public List<E> findwithnamequery(String queryname, int firstresult, int maxresult) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryname);
		query.setFirstResult(firstresult);
		query.setMaxResults(maxresult);
		List<E> result = query.getResultList();

		entityManager.close();
		return result;
	}
	
	public List<Object[]> findwithnamequeryObject(String queryname, int firstresult, int maxresult) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryname);
		query.setFirstResult(firstresult);
		query.setMaxResults(maxresult);
		List<Object[]> result = query.getResultList();
		
		entityManager.close();
		return result;
	}

	public List<E> findwithnamequery(String queryname, String paramName, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryname);
		query.setParameter(paramName, paramValue);

		List<E> result = query.getResultList();

		entityManager.close();
		return result;
	}

	public List<E> findwithnamequery(String queryname, Map<String, Object> parameters) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryname);

		Set<Entry<String, Object>> setparameters = parameters.entrySet();

		for (Entry<String, Object> entry : setparameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		List<E> result = query.getResultList();

		entityManager.close();
		return result;
	}

	public long countwithnamequery(String queryname) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryname);

		long result = (long) query.getSingleResult();
		entityManager.close();

		return result;
	}

	public long countwithnamequery(String queryname, String paramName, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryname);
		query.setParameter(paramName, paramValue);

		long result = (long) query.getSingleResult();
		entityManager.close();

		return result;
	}

	public void close() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}
