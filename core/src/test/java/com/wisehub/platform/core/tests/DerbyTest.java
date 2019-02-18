package com.wisehub.platform.core.tests;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;
import org.junit.Test;

public class DerbyTest {
	private static final Logger logger = Logger.getLogger(DerbyTest.class);

	@Test
	public void testDB() {
		final Session session = initHibernate();
		try {
			Transaction tx = session.beginTransaction();
			try {
				create(session);
				// list(session);

				tx.commit();
				tx = null;
			} finally {
				if (tx != null) {
					tx.rollback();
					tx = null;
				}
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	private void create(final Session session) {

		DateTime dt = new DateTime();
		/*
		 * 
		 * //Save countries /* Country nigeria = new Country("Nigeria", "NIG"); DateTime
		 * dt = new DateTime();
		 * 
		 * nigeria.setCreated(dt); nigeria.setLastMod(dt); session.save(nigeria);
		 * 
		 * City lagos = new City("Lagos", nigeria); lagos.setCreated(dt);
		 * lagos.setLastMod(dt); session.save(lagos);
		 * 
		 * Country ghana = new Country("Ghana", "GH"); dt = new DateTime();
		 * 
		 * ghana.setCreated(dt); ghana.setLastMod(dt); session.save(ghana);
		 * 
		 * City accra = new City("Accra", ghana); lagos.setCreated(dt);
		 * lagos.setLastMod(dt); session.save(accra);
		 * 
		 * Country usa = new Country("United States", "USA"); dt = new DateTime();
		 * 
		 * usa.setCreated(dt); usa.setLastMod(dt); session.save(usa);
		 * 
		 * City newYork = new City("New York City", usa); lagos.setCreated(dt);
		 * lagos.setLastMod(dt); session.save(newYork);
		 * 
		 */

		// Address
		/*
		 * Address owena = new Address("12 Owena Street", "973-887-0909" , lagos);
		 * owena.setAddress2("Packview Estate"); owena.setRegion("Ikoyi");
		 * owena.setPostalCode("221106"); owena.setCreated(dt); owena.setLastMod(dt);
		 * session.save(owena);
		 */

		// Financial Institution
		/*
		 * FinancialInstitution zenith = new
		 * FinancialInstitution("ZENITH BANK PLC",plot84); zenith.setFinCode("ZEN");
		 * zenith.setCreated(dt); zenith.setLastMod(dt); session.save(zenith);
		 * 
		 * //Branch Branch zenithHeadOffice = new Branch("Head Office",
		 * "enquiry@zenithbank.com",zenith,plot84); // zenithHeadOffice.setCreated(dt);
		 * // zenithHeadOffice.setLastMod(dt); session.save(zenithHeadOffice);
		 * 
		 * Branch zenithiBourdillion = new Branch("Bourdillion",
		 * "bourdillon@zenithbank.com",zenith,awolowo); //
		 * zenithiBourdillion.setCreated(dt); // zenithiBourdillion.setLastMod(dt);
		 * session.save(zenithiBourdillion);
		 * 
		 * //User /* User barbara = new User(new Address(), "Barbara", "Osaze",
		 * "barbara.iyayi@gmail.com", "barbara.iyayi@gmail.com", "barbara");
		 * barbara.setMiddleName("Osaze"); barbara.setNickName("Barb");
		 * barbara.setCreated(dt); barbara.setLastMod(dt); session.save(barbara);
		 */

		// Account

		/*
		 * 
		 * Account barbaraAcct = new Account("BARBARA OSAZE IYAYI", "5070366960",
		 * AccountStatus.ACTIVE, AccountType.CHECKING, zenithiBourdillion,
		 * barbara,Currency.getInstance(Locale.getDefault()));
		 * barbaraAcct.setAccountKey("iyayi"); barbaraAcct.setCreated(dt);
		 * barbaraAcct.setLastMod(dt); session.save(barbaraAcct);
		 * 
		 * // Category
		 * 
		 * Category test = new Category("Test"); test.setCreated(dt);
		 * test.setLastMod(dt); session.save(test);
		 * 
		 * Txaction transaction = new Txaction(barbaraAcct, barbara, new
		 * BigDecimal(100.00), null, test, new DateTime()); transaction.setCreated(dt);
		 * transaction.setLastMod(dt); session.save(transaction);
		 * 
		 */

	}

	/*
	 * private void list(final Session session) { Query q =
	 * session.createQuery("from " + Country.class.getName()); List list = q.list();
	 * logger.info("Query came back with " + list.size() + " results"); for (Object
	 * row : list) { logger.debug(row.toString()); }
	 * 
	 * Query q1 = session.createQuery("from " + City.class.getName()); List list1 =
	 * q1.list(); logger.info("Query came back with " + list1.size() + " results");
	 * for (Object row : list1) { logger.debug(row.toString()); }
	 * 
	 * Query q2 = session.createQuery("from " + Address.class.getName()); List list2
	 * = q2.list(); logger.info("Query came back with " + list2.size() +
	 * " results"); for (Object row : list2) { logger.debug(row.toString()); }
	 * 
	 * }
	 */

	public Session initHibernate() {
		final Configuration configuration = new Configuration().configure("config/hibernate-derby.cfg.xml");
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.Account.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.AccountBalance.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.Address.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.Branch.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.Category.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.FinancialInstitution.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.Txaction.class);
		configuration.addAnnotatedClass(com.wisehub.platform.core.orm.entity.User.class);

		logger.info("Connecting hibernate to URL=" + configuration.getProperty("hibernate.connection.url") + " as user="
				+ configuration.getProperty("hibernate.connection.username"));

		return configuration.buildSessionFactory().openSession();
	}
}
