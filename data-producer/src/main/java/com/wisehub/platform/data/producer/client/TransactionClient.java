package com.wisehub.platform.data.producer.client;

import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountByProductStatus;
import com.wisehub.platform.data.model.ProductByUser;
import com.wisehub.platform.data.model.Transaction;
import com.wisehub.platform.data.model.User;

@Component
@Profile(value = { "dev", "prod" })
public class TransactionClient implements TransactionClientI {

	private static final Logger log = LoggerFactory.getLogger(TransactionClient.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${wisehub.platform.api.host}")
	private String domain;

	@Value("${wisehub.platform.api.path}")
	private String appPath;

	@Value("${wisehub.platform.api.username}")
	private String username;

	@Value("${wisehub.platform.api.password}")
	private String password;

	@Value("${wisehub.platform.api.tx.service.path}")
	private String servicePath;

	private Map<String, User> customerMap;
	private Map<Long, Account> accountMap;

	public void init(String token) {
		log.debug("initialize");

		getCustomers(token);
		getAccounts(token);
	}

	private void getAccounts(String token) {
		log.debug("initialize accounts");
		accountMap = new HashMap<>();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		URI uri = URI.create(domain + appPath + "/accounts");

		HttpEntity<Account[]> request = new HttpEntity<Account[]>(headers);

		ResponseEntity<Account[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, Account[].class);
		Account[] accounts = response.getBody();

		for (Account item : accounts) {
			if (item.getNumber() != null) {
				accountMap.put(item.getNumber().longValue(), item);
			}
		}

	}

	private void getCustomers(String token) {
		log.debug("initialize customers");
		customerMap = new HashMap<>();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		URI uri = URI.create(domain + appPath + "/users");

		HttpEntity<User[]> request = new HttpEntity<User[]>(headers);

		ResponseEntity<User[]> response = restTemplate.exchange(uri, HttpMethod.GET, request, User[].class);
		User[] users = response.getBody();

		for (User user : users) {
			customerMap.put(user.getBvn().toString(), user);
		}

	}

	@Override
	public void postTxFromCSV(String token, String content) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("received <" + content + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");

		Transaction tx = this.transformTransaction(content);

		if (tx != null) {
			HttpHeaders httoHeaders = new HttpHeaders();
			httoHeaders.add("Authorization", token);
			URI uri = URI.create(domain + appPath + servicePath);

			HttpEntity<Transaction> request = new HttpEntity<Transaction>(tx, httoHeaders);

			ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);

			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			log.info("######          API Response Details           #####");
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			log.info("status code: " + exchange.getStatusCodeValue());
			log.info("body: " + exchange.getBody());
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");

		}

	}

	@Override
	public void postCustomer(String token, User user) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("received <" + user + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");

		HttpHeaders httoHeaders = new HttpHeaders();
		httoHeaders.add("Authorization", token);
		URI uri = URI.create(domain + appPath + "/users");

		HttpEntity<User> request = new HttpEntity<User>(user, httoHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("######          API Response Details           #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("status code: " + exchange.getStatusCodeValue());
		log.info("body: " + exchange.getBody());
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		getCustomers(token);

	}

	@Override
	public void postSaleAccount(String token, Account account) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("received <" + account + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");


		HttpHeaders httoHeaders = new HttpHeaders();
		httoHeaders.add("Authorization", token);
		URI uri = URI.create(domain + appPath + "/accounts");

		HttpEntity<Account> request = new HttpEntity<Account>(account, httoHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("######          API Response Details           #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("status code: " + exchange.getStatusCodeValue());
		log.info("body: " + exchange.getBody());
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		getAccounts(token);

	}

	/**
	 * Converter from csv line to Transaction Model
	 * 
	 * @param cvs
	 * @return Transaction
	 */

	private Transaction transformTransaction(String cvs) {

		String[] split = cvs.split(",");
		// 0bvn,1account_number,2date,3description,4deposits,5withdrawals,6balance

		User user = customerMap.get(split[0]);
		if (user == null || user.getId() == null) {
			log.error("There is none customer with this bvn {}", split[0]);
			return null;
		}

		Account account = accountMap.get(Long.valueOf(split[1]));
		if (account == null || account.getId() == null) {
			log.error("There is none account with this account_number {}", split[1]);
			return null;
		}

		if (!account.getUserId().equals(user.getId())) {
			log.error("The user is no matching with account {}");
			return null;
		}

		Transaction transaction = new Transaction();

		// TODO: REMOVE

		transaction.setUserId(user.getId());
		transaction.setAccountId(account.getId());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse;
		try {
			parse = simpleDateFormat.parse(split[2]);
			LocalDate date = parse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			transaction.setDatePosted(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		transaction.setDescription(split[3]);

		transaction.setDeposits(new BigDecimal(Double.valueOf(split[4])).setScale(2, BigDecimal.ROUND_HALF_UP));
		transaction.setWithdrawals(new BigDecimal(Double.valueOf(split[5])).setScale(2, BigDecimal.ROUND_HALF_UP));
		transaction.setBalance(new BigDecimal(Double.valueOf(split[6])).setScale(2, BigDecimal.ROUND_HALF_UP));

		return transaction;
	}

	@Override
	public void postRegister(DashboardRegisterViewModel viewModel) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("Register <" + viewModel + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");


		HttpHeaders httoHeaders = new HttpHeaders();
		URI uri = URI.create(domain + appPath + "/register");

		try {
			HttpEntity<DashboardRegisterViewModel> request = new HttpEntity<DashboardRegisterViewModel>(viewModel, httoHeaders);
			ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			log.info("######          API Response Details           #####");
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			log.info("status code: " + exchange.getStatusCodeValue());
			log.info("body: " + exchange.getBody());
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		} catch (org.springframework.web.client.HttpClientErrorException e) {
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			log.error("status code: " + e.getMessage());
			log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		}

	}

	@Override
	public String postAuth(DashboardRegisterViewModel viewModel) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("Authenticate <" + viewModel + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");

		HttpHeaders httoHeaders = new HttpHeaders();
		URI uri = URI.create(domain + appPath + "/auth");

		HttpEntity<DashboardRegisterViewModel> request = new HttpEntity<DashboardRegisterViewModel>(viewModel, httoHeaders);
		ResponseEntity<JwtAPIAuthenticationResponse> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, JwtAPIAuthenticationResponse.class);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("######          API Response Details           #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("status code: " + exchange.getStatusCodeValue());
		log.info("body: " + exchange.getBody());
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		return exchange.getBody().getToken();
	}

	@Override
	public User[] getByBnv(String token, String bvn) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		Map<String, String> params = new HashMap<String, String>();
		params.put("bvn", bvn);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(domain + appPath + "/users")
		        .queryParam("bvn", bvn);
		
		HttpEntity<User[]> request = new HttpEntity<User[]>(headers);

		ResponseEntity<User[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, User[].class);

		return response.getBody();
	}

	@Override
	public void postProductByUser(String token, ProductByUser productByUser) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("received <" + productByUser + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");


		HttpHeaders httoHeaders = new HttpHeaders();
		httoHeaders.add("Authorization", token);
		URI uri = URI.create(domain + appPath + "/productByUser");

		HttpEntity<ProductByUser> request = new HttpEntity<ProductByUser>(productByUser, httoHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("######          API Response Details           #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("status code: " + exchange.getStatusCodeValue());
		log.info("body: " + exchange.getBody());
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		getAccounts(token);

	}

	@Override
	public void postAccountByProductStatus(String token, AccountByProductStatus accountByProductStatus) {
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("received <" + accountByProductStatus + ">");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");


		HttpHeaders httoHeaders = new HttpHeaders();
		httoHeaders.add("Authorization", token);
		URI uri = URI.create(domain + appPath + "/accountByProductStatus");

		HttpEntity<AccountByProductStatus> request = new HttpEntity<AccountByProductStatus>(accountByProductStatus, httoHeaders);
		ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("######          API Response Details           #####");
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		log.info("status code: " + exchange.getStatusCodeValue());
		log.info("body: " + exchange.getBody());
		log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		getAccounts(token);

	}

	@Override
	public Account[] getAccountByUserId(String token, String userId, Long accountNumber) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(domain + appPath + "/accounts")
		        .queryParam("userId", userId)
		        .queryParam("accountNumber", accountNumber)
		        ;
		
		HttpEntity<Account[]> request = new HttpEntity<Account[]>(headers);

		ResponseEntity<Account[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, Account[].class);

		return response.getBody();
	}

}