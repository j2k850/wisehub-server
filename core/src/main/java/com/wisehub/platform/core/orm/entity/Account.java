package com.wisehub.platform.core.orm.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
// import org.hibernate.validator.InvalidStateException;
// import org.hibernate.validator.InvalidValue;
// import org.hibernate.validator.NotEmpty;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
//import com.wise.wisecommon.money.Money;
//import com.wise.wisecommon.money.UnknownCurrencyCodeException;

@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Account")
@NamedQueries({
		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.Account.findByAccountKeyAndRelativeId", query = "SELECT a FROM Account a"
				+
				// " WHERE a.accountKey = :accountKey AND a.relativeId = :accountId"
				" WHERE a.accountKey = :accountKey "),
		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.Account.findAllByAccountKey", query = "SELECT a FROM Account a"
				+ " WHERE a.accountKey = :accountKey AND a.status IN (:statuses)"
		// " ORDER BY a.relativeId ASC"
		),
		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.Account.findAllStaleAccountBeforeDate", query = "SELECT a FROM Account a, AccountBalance b"
				+ " WHERE a.account_id = b.account_balance_id  " + " AND b.lastMod < :lastMod "
				+ " ORDER BY b.lastMod ASC, a.created ASC"), })

public class Account extends EntityModel {

	private static Logger logger = LoggerFactory.getLogger(Account.class.getName());

	// private static final CurrencyCodeParser CURRENCY_CODE_PARSER = new
	// CurrencyCodeParser();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id", nullable = false)
	private Integer account_id;

	@Column(nullable = false, name = "account_name")
	private String accountName;

	@Column(nullable = false, name = "account_number")
	private String accountNumber;

	@Column(name = "account_key", nullable = false, unique = false, length = 64)
	private String accountKey;

	@Column(name = "currency")
	private String currencyCode = Currency.getInstance(Locale.getDefault()).getCurrencyCode();

	@Column(nullable = false)
	private int status = AccountStatus.ACTIVE.getValue();

	@Column(nullable = false, unique = true, length = 64)
	// private String guid = UUID.generateRandom(64).toString();
	private String guid = UUID.randomUUID().toString();

	// @Column(name="id_for_user")
	// @Column(name="user_id")
	// private Integer relativeId;

	@Column(name = "position")
	private Integer position = 0;

	@Transient
	private BigDecimal balance;

	@Column(nullable = false, name = "account_type_id")
	private int accountTypeId = AccountType.UNKNOWN.getValue();

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	@OneToMany(mappedBy = "account")
	@BatchSize(size = 10)
	private Set<AccountBalance> accountBalances = Sets.newHashSet();

	@OneToMany(mappedBy = "account")
	@BatchSize(size = 200)
	private Set<Txaction> txactions = Sets.newHashSet();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
	private Branch branch;

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public Account() {
	}

	public Account(String accountName, String accountNumber, AccountStatus accountStatus, AccountType accountType,
			Branch branch, User user, Currency currency) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.accountTypeId = accountType.getValue();
		this.status = accountStatus.getValue();
		this.branch = branch;
		this.user = user;
		this.currencyCode = currency.getCurrencyCode();
	}

	public Integer getId() {
		return account_id;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public String getAccountKey() {
		return accountKey;
	}

	// public Integer getAccountType() {
	// return AccountType.byValue(this.accountTypeId);
	// }

	// public void setAccountType(AccountType accountType) {
	// this.accountTypeId = accountType.getValue();
	// }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(AccountStatus status) {
		this.status = status.getValue();
	}

	public AccountStatus getStatus() {
		return AccountStatus.byValue(status);
	}

	/*
	 * public Currency getCurrency() throws UnknownCurrencyCodeException { return
	 * CURRENCY_CODE_PARSER.parse(currencyCode); }
	 */

	public void setCurrency(Currency currency) {
		this.currencyCode = currency.getCurrencyCode();
	}

	public String getGuid() {
		return new String(guid);
	}

	// public GUID getGuid() {
	// return new GUID(guid);
	// }

	// public void setRelativeId(int relativeId) {
	// this.relativeId = Integer.valueOf(relativeId);
	// }

	// public Integer getRelativeId() {
	// return relativeId;
	// }

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Branch getBranch() {
		return branch;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Account) {
			Account that = (Account) obj;

			return Objects.equal(getAccountKey(), that.getAccountKey())
					// && Objects.equal(getCurrency(), that.getCurrency())
					&& Objects.equal(getGuid(), that.getGuid()) && Objects.equal(getId(), getId())
					&& Objects.equal(getAccountName(), that.getAccountName())
					&& Objects.equal(getStatus(), that.getStatus());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(accountKey, currencyCode, guid, account_id, accountName, Integer.valueOf(status));
	}

	/*
	 * public Money getBalance() { if (!canHaveBalance()) { return null; }
	 * 
	 * if (hasCachedBalance()) { return new Money(balance, getCurrency()); }
	 * 
	 * return calculateBalance(); }
	 */

	/*
	 * public void setBalance(BigDecimal balance) { accountBalances.add(new
	 * AccountBalance(this, balance, new DateTime())); calculateBalance(); }
	 */

	/*
	 * public void setBalance(Money balance) throws Exception { if
	 * (!balance.getCurrency().equals(getCurrency())) { // throw new
	 * InvalidStateException(new InvalidValue[] { // throw new Exception(new
	 * InvalidValue[] { // new
	 * InvalidValue("currency for new balance ("+balance.getCurrency()
	 * +") does not match account currency ("+getCurrency()+")", getClass(),
	 * "balance", balance, this) // }); throw new
	 * Exception("currency for new balance ("+balance.getCurrency()
	 * +") does not match account currency ("+getCurrency()+")"+ " " + getClass() +
	 * "balance="+ balance); }
	 * 
	 * setBalance(balance.getValue()); }
	 */

	private boolean hasCachedBalance() {
		return balance != null;
	}

	/*
	 * public boolean hasBalance() { return canHaveBalance() && (getBalance() !=
	 * null); }
	 */

	private boolean canHaveBalance() {
		return getAccountType().hasBalance();
	}

	public void enableBalance() {
		if (getAccountType().hasBalance()) {
			// nothing to do
			return;
		}

		setAccountType(AccountType.MANUAL);
		// give us some sort of balance if we don't have one
		// if (!hasBalance()) {
		// setBalance(new BigDecimal("0.0"));
		// }
	}

	public void disableBalance() throws Exception {
		if (!getAccountType().hasBalance()) {
			// nothing to do
			return;
		}

		if (getAccountType().hasUploads()) {
			// throw new InvalidStateException(new InvalidValue[] {
			// new InvalidValue("cannot disable balances on accounts with uploads",
			// getClass(), "accountTypeId", AccountType.CASH, this)
			// });
			throw new Exception("cannot disable balances on accounts with uploads " + getClass() + "accountTypeId="
					+ AccountType.CASH);
		}

		setAccountType(AccountType.CASH);
	}
	/*
	 * private Money calculateBalance() { final AccountBalance accountBalance =
	 * getMostRecentAccountBalance(); if (accountBalance == null) { // FIXME
	 * coda@wesabe.com -- Apr 28, 2009: We *really* don't expect getBalance() to
	 * return null. return null; }
	 * 
	 * Money balance = accountBalance.getBalance(); for (Txaction txaction :
	 * getTransactionsSince(accountBalance.getDate())) { balance =
	 * balance.add(txaction.getAmount()); }
	 * 
	 * this.balance = balance.getValue(); return balance; }
	 */

	public DateTime getBalanceDate() {
		final AccountBalance accountBalance = getMostRecentAccountBalance();
		if (accountBalance == null) {
			return null;
		}

		return accountBalance.getDate();
	}

	public Set<Txaction> getTxactions() {
		return txactions;
	}

	private Set<Txaction> getTransactionsSince(final DateTime date) {
		return Sets.filter(getTxactions(), new Predicate<Txaction>() {
			@Override
			public boolean apply(final Txaction txaction) {
				return !txaction.isDeleted() && !txaction.isDisabled() && txaction.getDatePosted().isAfter(date);
			}
		});
	}

	private AccountBalance getMostRecentAccountBalance() {
		AccountBalance mostRecentAccountBalance = null;

		for (AccountBalance accountBalance : getAccountBalances()) {
			if ((mostRecentAccountBalance == null)
					|| ((accountBalance.getDate() != null) && (mostRecentAccountBalance.getDate() != null)
							&& mostRecentAccountBalance.getDate().isBefore(accountBalance.getDate()))) {
				mostRecentAccountBalance = accountBalance;
			}
		}

		return mostRecentAccountBalance;
	}

	public Set<AccountBalance> getAccountBalances() {
		return accountBalances;
	}

	public boolean isActive() {
		return getStatus().equals(AccountStatus.ACTIVE);
	}

	public boolean isArchived() {
		return getStatus().equals(AccountStatus.ARCHIVED);
	}

	public AccountType getAccountType() {
		return AccountType.byValue(accountTypeId);
	}

	public void setAccountType(AccountType accountType) {
		this.accountTypeId = accountType.getValue();
	}

	public Integer getPosition() {
		return position;
	}

	public static Account ofType(AccountType accountType) {
		Account account = new Account();
		account.setAccountType(accountType);
		return account;
	}

	// REVIEW: 2009-05-13 <andre@wesabe.com> -- This should probably check for the
	// max
	// of [last balance date, last ssu job date, last transaction date] or Today if
	// those
	// are in the future.
	public DateTime getLastActivityDate() {
		AccountBalance mostRecentAccountBalance = getMostRecentAccountBalance();

		if (mostRecentAccountBalance == null) {
			return null;
		}

		return mostRecentAccountBalance.getCreated();
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getLastMod() {
		return lastMod;
	}

	public void setLastMod(DateTime lastMod) {
		this.lastMod = lastMod;
	}

}
