package com.wisehub.platform.core.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;
import com.wisehub.platform.core.orm.dao.AccountDAO;
import com.wisehub.platform.core.orm.entity.Account;

@RunWith(Enclosed.class)
public class AccountDAOTest {
	public static class Selecting_All_Accounts_For_A_User {
		private Session session;
		private Query query;
		private Account account;
		private AccountDAO dao;

		@Before
		public void setup() throws Exception {
			this.account = mock(Account.class);
			this.query = mock(Query.class);
			when(query.list()).thenReturn(ImmutableList.of(account));
			when(query.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
			when(query.setParameterList(Mockito.anyString(), Mockito.anyCollection())).thenReturn(query);

			this.session = mock(Session.class);
			// when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);

			this.dao = null;

			// this.dao = new AccountDAO(new Provider<Session>() {
			// @Override
			// public Session get() {
			// return session;
			// }
			// });
		}

		@Test
		public void itCreatesANamedQuery() throws Exception {
			// dao.findAllAccountsByAccountKey("12345", EnumSet.of(AccountStatus.LOCKED));

			// verify(session).getNamedQuery("com.wisehub.platform.core.dao.entity.Account.findAllByAccountKey");
		}

		@Test
		public void itScopesTheQueryToTheUsersAccounts() throws Exception {
			// dao.findAllAccountsByAccountKey("12345", EnumSet.of(AccountStatus.LOCKED));

			// verify(query).setString("accountKey", "12345");
		}

		@Test
		public void itScopesTheQueryToTheGivenStatuses() throws Exception {
			// dao.findAllAccountsByAccountKey("12345", EnumSet.of(AccountStatus.LOCKED));

			// verify(query).setParameterList("statuses",
			// ImmutableList.of(AccountStatus.LOCKED.getValue()));
		}

		@Test
		public void itExecutesTheQuery() throws Exception {
			// dao.findAllAccountsByAccountKey("12345", EnumSet.of(AccountStatus.LOCKED));

			// verify(query).list();
		}

		@Test
		public void itReturnsAnAccountListWithTheReturnedAccounts() throws Exception {
			// final List<Account> accountList = dao.findAllAccountsByAccountKey("12345",
			// EnumSet.of(AccountStatus.LOCKED));

			// assertThat(accountList.size(), is(1));
			// assertThat(accountList, hasItem(account));
		}
	}
}
