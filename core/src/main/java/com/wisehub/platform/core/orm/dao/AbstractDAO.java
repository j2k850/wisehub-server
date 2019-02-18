package com.wisehub.platform.core.orm.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class AbstractDAO<E> {

	private Session currentSession;
	private SessionFactory sessionFactory;
	private Transaction currentTransaction;

	/**
	 * Creates a new DAO with a given session provider.
	 *
	 * @param sessionFactory
	 *            a session provider
	 */
	// public AbstractDAO(SessionFactory sessionFactory) {
	// this.sessionFactory = checkNotNull(sessionFactory);
	// this.entityClass = Generics.getTypeParameter(getClass());
	// }

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	/**
	 * Returns a named {@link Query}.
	 *
	 * @param queryName
	 *            the name of the query
	 * @return the named query
	 * @see Session#getNamedQuery(String)
	 */
	public Query namedQuery(String queryName) throws HibernateException {
		return getCurrentSession().getNamedQuery(checkNotNull(queryName));
	}

	/**
	 * Convenience method to return a single instance that matches the criteria, or
	 * null if the criteria returns no results.
	 *
	 * @param criteria
	 *            the {@link Criteria} query to run
	 * @return the single result or {@code null}
	 * @throws HibernateException
	 *             if there is more than one matching result
	 * @see Criteria#uniqueResult()
	 */
	@SuppressWarnings("unchecked")
	public E uniqueResult(Criteria criteria) throws HibernateException {
		return (E) checkNotNull(criteria).uniqueResult();
	}

	/**
	 * Convenience method to return a single instance that matches the query, or
	 * null if the query returns no results.
	 *
	 * @param query
	 *            the query to run
	 * @return the single result or {@code null}
	 * @throws HibernateException
	 *             if there is more than one matching result
	 * @see Query#uniqueResult()
	 */
	@SuppressWarnings("unchecked")
	public E uniqueResult(Query query) throws HibernateException {
		return (E) checkNotNull(query).uniqueResult();
	}

	/**
	 * Get the results of a {@link Criteria} query.
	 *
	 * @param criteria
	 *            the {@link Criteria} query to run
	 * @return the list of matched query results
	 * @see Criteria#list()
	 */
	@SuppressWarnings("unchecked")
	public List<E> list(Criteria criteria) throws HibernateException {
		return checkNotNull(criteria).list();
	}

	/**
	 * Get the results of a query.
	 *
	 * @param query
	 *            the query to run
	 * @return the list of matched query results
	 * @see Query#list()
	 */
	@SuppressWarnings("unchecked")
	public List<E> list(Query query) throws HibernateException {
		return checkNotNull(query).list();
	}

	/**
	 * Return the persistent instance of {@code <E>} with the given identifier, or
	 * {@code null} if there is no such persistent instance. (If the instance, or a
	 * proxy for the instance, is already associated with the session, return that
	 * instance or proxy.)
	 *
	 * @param id
	 *            an identifier
	 * @return a persistent instance or {@code null}
	 * @throws HibernateException
	 * @see Session#get(Class, Serializable)
	 */
	@SuppressWarnings("unchecked")
	public E get(Serializable id) {
		// return (E) getCurrentSession().get(entityClass, checkNotNull(id));
		return null;
	}

	/**
	 * Either save or update the given instance, depending upon resolution of the
	 * unsaved-value checks (see the manual for discussion of unsaved-value
	 * checking).
	 * <p/>
	 * This operation cascades to associated instances if the association is mapped
	 * with <tt>cascade="save-update"</tt>.
	 *
	 * @param entity
	 *            a transient or detached instance containing new or updated state
	 * @throws HibernateException
	 * @see Session#saveOrUpdate(Object)
	 */
	public E persist(E entity) throws HibernateException {
		getCurrentSession().saveOrUpdate(checkNotNull(entity));
		return entity;
	}
}
