package com.wisehub.platform.core.orm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
import com.wise.wisecommon.money.CurrencyExchangeRateMap;
import com.wise.wisecommon.money.ExchangeRateNotFoundException;
import com.wise.wisecommon.money.Monetary;
import com.wise.wisecommon.money.Money;
import com.wise.wisecommon.money.UnknownCurrencyCodeException;
*/

@Entity
@Table(name = "txaction")
@NamedQueries({

		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.Txaction.findFirstDatePosted", query = "SELECT MIN(t.datePosted) FROM Txaction t"
				+ " WHERE t.account IN (:accounts)"),
		@NamedQuery(name = "com.wisehub.platform.core.orm.entity.Txaction.findInDateRange", query = "SELECT t FROM Txaction t"
				+ " WHERE t.account IN (:accounts) AND t.datePosted >= :startDate AND t.datePosted < :endDate"
				+ " ORDER BY t.datePosted ASC, t.sequence DESC, t.created ASC") })

// public class Txaction implements Monetary, Comparable<Txaction> {
public class Txaction implements Comparable<Txaction> {

	private static Logger logger = LoggerFactory.getLogger(Txaction.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "tx_action_id")
	private Integer id;

	@Column(nullable = true, name = "debit_amount")
	private BigDecimal debitAmount;

	@Column(nullable = true, name = "credit_amount")
	private BigDecimal creditAmount;

	@Column(name = "date_posted")
	private DateTime datePosted;

	@Column(name = "orig_date_posted")
	private DateTime originalDatePosted;

	@Column(name = "created")
	private DateTime created;

	@Column(name = "last_mod")
	private DateTime lastMod;

	// @ManyToOne(fetch=FetchType.LAZY)
	// @JoinColumn(name="transfer_txaction_id", nullable=true)
	// @NotFound(action=NotFoundAction.IGNORE)
	// // FIXME coda@wesabe.com -- Apr 29, 2009: Make txactions.transfer_txaction_id
	// a foreign key.
	// // It hurts my brain that we have to tell our Hibernate to not complain when
	// // we try to load things. Oy.
	// private Txaction transferTxaction;

	@Column(nullable = true)
	private Integer sequence;

	@Column
	private Integer status = TxactionStatus.ACTIVE.getValue();

	@Column(name = "raw_name")
	private String rawName;

	@Column(name = "filtered_name")
	private String filteredName;

	@Column
	private String memo;

	// @Column
	// private String note;

	// @Column
	// private boolean tagged;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;
	/*
	 * @ManyToOne(fetch=FetchType.LAZY)
	 * 
	 * @JoinColumn(name="merchant_id") private Merchant merchant;
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	// @OneToMany(mappedBy="txaction")
	// @BatchSize(size=200)
	// @OrderBy("id")
	// private List<TaggedAmount> taggedAmounts = Lists.newLinkedList();

	// @Column(name="check_num")
	// private String checkNumber;

	// @ManyToMany
	// @JoinTable(
	// name="txaction_attachments",
	// joinColumns=@JoinColumn(name="txaction_id"),
	// inverseJoinColumns=@JoinColumn(name="attachment_id")
	// )
	// @BatchSize(size=200)
	// private Set<Attachment> attachments = Sets.newHashSet();

	public Txaction() {
	}

	public Txaction(Account account, User user, BigDecimal creditAmount, BigDecimal debitAmount, Category category,
			DateTime datePosted) {
		this.account = account;
		this.user = user;

		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
		this.category = category;
		this.datePosted = datePosted;
		this.originalDatePosted = datePosted;
	}

	public int getId() {
		return id;
	}

	public DateTime getDatePosted() {
		return datePosted;
	}

	public DateTime getOriginalDatePosted() {
		return originalDatePosted;
	}

	public Account getAccount() {
		return account;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	// public boolean isTagged() {
	// return tagged;
	// }

	// public void setTagged(boolean flag) {
	// this.tagged = flag;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wise.wisecommon.entities.entities.Monetary#getAmount()
	 */
	/*
	 * @Override public Money getAmount() throws UnknownCurrencyCodeException {
	 * return new Money(creditAmount, account.getCurrency()); }
	 * 
	 * /* (non-Javadoc)
	 * 
	 * @see
	 * com.wise.wisecommon.entities.entities.Monetary#getConvertedAmount(java.util.
	 * Currency, com.wesabe.api.util.money.CurrencyExchangeRateMap)
	 */
	/*
	 * @Override public Money getConvertedAmount(Currency target,
	 * CurrencyExchangeRateMap exchangeRates) throws ExchangeRateNotFoundException,
	 * UnknownCurrencyCodeException { // return
	 * getCreditAmount().convert(exchangeRates, target, datePosted); return null; }
	 */

	// public Txaction getTransferTxaction() {
	// return transferTxaction;
	// }

	// public void setTransferTxaction(Txaction transferTxaction) {
	// this.transferTxaction = transferTxaction;
	// }

	// public boolean isTransfer() {
	// return transferTxaction != null;
	// }

	// public boolean isPairedTransfer() {
	// return transferTxaction != null && !transferTxaction.equals(this);
	// }

	public TxactionStatus getStatus() {
		return TxactionStatus.byValue(status);
	}

	public boolean isDeleted() {
		return getStatus() == TxactionStatus.DELETED;
	}

	public void setStatus(TxactionStatus status) {
		this.status = Integer.valueOf(status.getValue());
	}

	// public List<TaggedAmount> getTaggedAmounts() {
	// return taggedAmounts;
	// }

	// public Money getAmountByFilteringTags(Set<Tag> filteredTags) {
	// final Money txactionAmount = getAmount().abs();
	// Money filteredAmount = new Money(BigDecimal.ZERO, account.getCurrency());
	// for (TaggedAmount taggedAmount : taggedAmounts) {
	// if (filteredTags.contains(taggedAmount.getTag())) {
	// filteredAmount = filteredAmount.add(taggedAmount.getAmount().abs());
	// }
	// }

	// if (txactionAmount.compareTo(filteredAmount) <= 0) {
	// return new Money(BigDecimal.ZERO, account.getCurrency());
	// }
	// return
	// txactionAmount.subtract(filteredAmount).multiply(getAmount().signum());
	// }

	// public Money getConvertedAmountByFilteringTags(Set<Tag> filteredTags,
	// Currency target,
	// CurrencyExchangeRateMap exchangeRates) throws ExchangeRateNotFoundException,
	// UnknownCurrencyCodeException {
	// return getAmountByFilteringTags(filteredTags).convert(exchangeRates, target,
	// datePosted);
	// }

	// public TaggedAmount addTag(Tag tag) {
	// return addTag(tag, null);
	// }

	// public TaggedAmount addTag(Tag tag, BigDecimal amount) {
	// final TaggedAmount taggedAmount = new TaggedAmount(this, tag, amount);
	// taggedAmounts.add(taggedAmount);
	// return taggedAmount;
	// }

	public boolean isDisabled() {
		return getStatus() == TxactionStatus.DISABLED;
	}

	// public boolean isUnedited() {
	// return (getMerchant() == null) || (!isTagged() && !isTransfer());
	// }

	@Override
	public int compareTo(Txaction other) {
		final int equal = 0;

		int result = getDatePosted().compareTo(other.getDatePosted());

		if ((result == equal) && (getSequence() != null) && (other.getSequence() != null)) {
			result = other.getSequence().compareTo(getSequence());
		}

		if ((result == equal) && (getCreated() != null) && (other.getCreated() != null)) {
			result = getCreated().compareTo(other.getCreated());
		}

		return result;
	}

	public String getUneditedName() {
		String uneditedName = "";
		if ((rawName != null) && (rawName.trim().length() != 0)) {
			uneditedName += rawName;
		}
		if ((memo != null) && (memo.trim().length() != 0)) {
			uneditedName += (uneditedName.length() == 0 ? "" : " / ") + memo;
		}
		return uneditedName;
	}

	public String getFilteredName() {
		return filteredName;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public void setDatePosted(DateTime datePosted) {
		this.datePosted = datePosted;
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

	@Override
	public String toString() {
		return "<Txaction creditAmount=" + creditAmount + ", debitAmount=" + debitAmount + ">";
	}

}
