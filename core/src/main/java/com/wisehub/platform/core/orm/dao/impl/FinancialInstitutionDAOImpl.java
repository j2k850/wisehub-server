package com.wisehub.platform.core.orm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wisehub.platform.core.orm.dao.FinancialInstitutionDAO;
import com.wisehub.platform.core.orm.entity.FinancialInstitution;
//import com.wisehub.platform.core.orm.entity.UserFinancialInstitution;
import com.wisehub.platform.core.orm.entity.UserFinancialInstitution;

@Repository("finInstDao")
public class FinancialInstitutionDAOImpl implements FinancialInstitutionDAO {

	private static Logger logger = LoggerFactory.getLogger(FinancialInstitutionDAOImpl.class.getName());

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(FinancialInstitution finInst) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(finInst);
		tx.commit();
		session.close();
	}

	@Override
	public UserFinancialInstitution getCredentials(Integer userId, Integer financialInstitutionId) {
		// TODO Auto-generated method stub
		return null;
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<FinancialInstitution> list() {
	// Session session = this.sessionFactory.openSession();
	// List<FinancialInstitution> finInstList = session.createQuery("from
	// FinancialInstitution").list();
	// session.close();
	// return finInstList;
	// }
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public UserFinancialInstitution getCredentials(Integer userId,
	 * Integer financialInstitutionId) throws HibernateException {
	 * 
	 * Session session = this.sessionFactory.openSession();
	 * 
	 * Query query = session.getNamedQuery(
	 * "com.wise.wisecommon.entities.FinancialInstitution.findCredentialsById")
	 * .setInteger("user_id", userId) .setInteger("fin_inst_id",
	 * financialInstitutionId);
	 * 
	 * UserFinancialInstitution userFinancialInstitution =
	 * (UserFinancialInstitution) query.uniqueResult();
	 * 
	 * // closing hibernate resources session.close();
	 * 
	 * return userFinancialInstitution;
	 * 
	 * }
	 */
}
