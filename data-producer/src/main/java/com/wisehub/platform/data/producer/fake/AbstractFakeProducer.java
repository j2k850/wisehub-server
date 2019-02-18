package com.wisehub.platform.data.producer.fake;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisehub.platform.data.model.Account;
import com.wisehub.platform.data.model.AccountByProductStatus;
import com.wisehub.platform.data.model.AccountStatus;
import com.wisehub.platform.data.model.AccountType;
import com.wisehub.platform.data.model.ProductByUser;
import com.wisehub.platform.data.model.UDTAddress;
import com.wisehub.platform.data.model.UDTFullName;
import com.wisehub.platform.data.model.UDTProductStatus;
import com.wisehub.platform.data.model.User;
import com.wisehub.platform.data.producer.client.DashboardRegisterViewModel;
import com.wisehub.platform.data.producer.client.TransactionClientI;
import com.wisehub.platform.data.producer.fake.model.CategoriesType;
import com.wisehub.platform.data.producer.fake.model.TransactionFakeModel;
import com.wisehub.platform.data.producer.fake.model.TransactionType;
import com.wisehub.platform.data.producer.fake.profile.CustomerProfile;
import com.wisehub.platform.data.producer.util.CSVUtils;

public abstract class AbstractFakeProducer implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractFakeProducer.class);

	
	public abstract CustomerProfile getCustomerProfile();
	

	@Autowired
	TransactionClientI clientI;
	
	
	FileWriter writer;
	
	@Autowired
	private ObjectMapper mapper;

	private String generateStaticInfo() {
		String shortName = new String(getCustomerProfile().getName().substring(0, 1) + getCustomerProfile().getSurname()).toLowerCase();
		shortName = shortName.replaceAll("\\s",".");
		LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		LOG.info("Create dashboard user : " + shortName);
		LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
		String email = shortName + "@wisehub.io";
		DashboardRegisterViewModel viewModel = new DashboardRegisterViewModel();
		viewModel.setEmail(email);
		viewModel.setUsername(getCustomerProfile().getName() + " " + getCustomerProfile().getSurname());
		viewModel.setPassword(shortName + "1234");

		if (getCustomerProfile().isGenerateStatic()){
			clientI.postRegister(viewModel);
			try {
				String writeValueAsString = mapper.writeValueAsString(viewModel);
				LOG.info("Create dashboard user json : " + writeValueAsString);
			} catch (JsonProcessingException e1) {
			}
		}

		
		String token = clientI.postAuth(viewModel);
		
		if (getCustomerProfile().isGenerateStatic()){
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			LOG.info("Create customer : bvn =" + getCustomerProfile().getBnv().toString());
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");		
			User user = new User();
			user.setBvn(new Long(getCustomerProfile().getBnv().toString()));
			user.setEmail(email);
			UDTFullName name = new UDTFullName();
			name.setFirstName(getCustomerProfile().getName());
			name.setLastName(getCustomerProfile().getSurname());
			Map<String, UDTAddress> addresses = new HashMap<>();
			UDTAddress value = new UDTAddress();
			value.setCity(getCustomerProfile().getCity());
			value.setCountry(getCustomerProfile().getCountry());
			Set<String> phones = new HashSet<>();
			phones.add(getCustomerProfile().getPhone());
			value.setPhones(phones);
			value.setPostalCode(getCustomerProfile().getPostalcode());
			value.setRegion(getCustomerProfile().getRegion());
			value.setStreet(getCustomerProfile().getStreet());
			addresses.put(getCustomerProfile().getCity(), value);
			user.setAddresses(addresses);
			
			try {
				String writeValueAsString = mapper.writeValueAsString(user);
				LOG.info("Create User json : " + writeValueAsString);
			} catch (JsonProcessingException e1) {
			}
			
			clientI.postCustomer(token, user);
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		
		User[] newUser = clientI.getByBnv(token, getCustomerProfile().getBnv().toString());
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		if (getCustomerProfile().isGenerateStatic()) {
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			LOG.info("Create sales account : account_number" + getCustomerProfile().getAccountNumber().toString());
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			Account account = new Account();
			account.setBranchId(UUID.fromString(getCustomerProfile().getBranch()));
			account.setName(getCustomerProfile().getName() + " " + getCustomerProfile().getSurname());
			account.setNumber(new Long(getCustomerProfile().getAccountNumber().toString()));

			AccountStatus byName = AccountStatus.byName(getCustomerProfile().getAccountStatus());
			account.setStatus(byName == null ? AccountStatus.DISABLED : byName);
			AccountType accountType = AccountType.byName(getCustomerProfile().getAccountType());
			account.setType(accountType == null ? AccountType.UNKNOWN : accountType);
			account.setUserId(newUser[0].getId());

			try {
				String writeValueAsString = mapper.writeValueAsString(account);
				LOG.info("Create account json : " + writeValueAsString);
			} catch (JsonProcessingException e1) {
			}

			clientI.postSaleAccount(token, account);
		}
		Account[] newAccount = clientI.getAccountByUserId(token, newUser[0].getId().toString(), getCustomerProfile().getAccountNumber());
		
		
		if (getCustomerProfile().isGenerateStatic()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			LOG.info("Create product by user");
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			UDTProductStatus status = new UDTProductStatus();
			status.setStatus(getCustomerProfile().getAccountByProductStatus());
			Map<String, UDTProductStatus> productStatus = new HashMap<>();
			productStatus.put(getCustomerProfile().getAccountByProductStatus(), status);

			ProductByUser productByUser = new ProductByUser();
			productByUser.setProductId(UUID.fromString(getCustomerProfile().getProduct()));
			productByUser.setProductStatus(productStatus);
			productByUser.setUserId(newUser[0].getId());

			try {
				String writeValueAsString = mapper.writeValueAsString(productByUser);
				LOG.info("Create productByUser json : " + writeValueAsString);
			} catch (JsonProcessingException e1) {
			}

			clientI.postProductByUser(token, productByUser);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			LOG.info("Create sales  account by user");
			LOG.info("- - - - - - - - - - - - - - - - - - - - - - - -");
			AccountByProductStatus accountByProductStatus = new AccountByProductStatus();
			accountByProductStatus.setAccountId(newAccount[0].getId());
			accountByProductStatus.setProductId(UUID.fromString(getCustomerProfile().getBranch()));
			accountByProductStatus.setProductStatus(productStatus);

			try {
				String writeValueAsString = mapper.writeValueAsString(accountByProductStatus);
				LOG.info("Create accountByProductStatus json : " + writeValueAsString);
			} catch (JsonProcessingException e1) {
			}

			clientI.postAccountByProductStatus(token, accountByProductStatus);
		}
		return token;
		
	}

	public void run(String... args) {
		
		String token = generateStaticInfo();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		if (!getCustomerProfile().isGenerateCSV() && getCustomerProfile().isGeneratePopulate()){
			
			clientI.init(token);
			
			String folder = buildFolderBaseForCustomer();
			
			File directory = new File(folder);
			File[] listOfFiles = directory.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  File file = listOfFiles[i];
			  if (file.isFile() && file.getName().endsWith(".csv") && file.getName().startsWith(String.valueOf(getCustomerProfile().getAccountNumber()))) {
			    List<String> content;
				try {
					LOG.debug("Opening File: " + file.getAbsolutePath());
					content = FileUtils.readLines(file);
					for (String csvLine : content) {
						clientI.postTxFromCSV(token, csvLine);
					}
				} catch (IOException e) {
					LOG.error(e.getMessage(),e);
				}
			  } 
			}
		} else if (getCustomerProfile().isGenerateCSV()){

			int from = getCustomerProfile().getFromMonth();
			int to = getCustomerProfile().getToMonth();

			String folder = buildFolderBaseForCustomer();
			checkFolder(folder);

			double balance = 0d;
			for (int i = from; i <= to; i++) {
				LocalDate localDate = LocalDate.of(2016, i, 1);
				try {
					String csvFile = folder + File.separator + getCustomerProfile().getAccountNumber() + "_month_" + i + ".csv";
					
					writer = new FileWriter(csvFile);

					double initialSaliry = getIncome();
					List<TransactionFakeModel> txs = this.generate(localDate, balance + initialSaliry);

					for (TransactionFakeModel transaction : txs) {
						String csvLine;
						try {
							if (transaction != null) {
								csvLine = CSVUtils.writeLine(writer, buildCSVValues(getCustomerProfile(), transaction));
								clientI.postTxFromCSV(token, csvLine);
								balance = transaction.getBalance().doubleValue();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (writer != null) {
							writer.flush();
							writer.close();
						}
					} catch (IOException e) {
					}

				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}

		}

	}

	private String buildFolderBaseForCustomer() {
		return getCustomerProfile().getBaseFolderCSV() + File.separator + getCustomerProfile().getBnv().toString();
	}


	private void checkFolder(String folder) {
		File theDir = new File(folder);
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    LOG.info("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdirs();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        LOG.info("DIR created");  
		    }
		}		
	}

	public abstract double getIncome();

	public abstract List<TransactionFakeModel> generate(LocalDate localDate, double open1ingBalance);

	protected List<TransactionFakeModel> buildFromFixedCost(boolean uncovered, LocalDate localDate, CustomerProfile customerProfile, TransactionFakeModel transactionFakeGen) {
		List<TransactionFakeModel> txFixed = new ArrayList<>();
		TransactionFakeModel prev = transactionFakeGen;
		for (CategoriesType category : customerProfile.getFixedCostCategories()) {
			double amount = ThreadLocalRandom.current().nextDouble(category.getMin(), category.getMax());
			
			TransactionFakeModel tx  = buildOperation(uncovered, localDate, prev, TransactionType.WITHDRAW, category, amount);
			
			if (tx != null && tx.getBalance() != null) {
				txFixed.add(tx);
				prev = tx;

				int nextDay = ThreadLocalRandom.current().nextInt(2);
				localDate = localDate.plusDays(nextDay);
			}
		}
		
		return txFixed;
	}
	
	protected TransactionFakeModel buildOperation(LocalDate localDate, TransactionFakeModel prevTransaction, TransactionType transactionType,
			CategoriesType categoriesType, double amount){
		return buildOperation(false, localDate, prevTransaction, transactionType, categoriesType, amount);
	}

	protected TransactionFakeModel buildOperation(boolean uncovered, LocalDate localDate, TransactionFakeModel prevTransaction, TransactionType transactionType,
			CategoriesType categoriesType, double amount) {

		int hours = ThreadLocalRandom.current().nextInt(8, 19);
		int mins = ThreadLocalRandom.current().nextInt(0, 59);

		LocalTime of = LocalTime.of(hours, mins);
		Date date = Date.from(localDate.atTime(of).toInstant(ZoneOffset.UTC));

		TransactionFakeModel transaction = new TransactionFakeModel();

		switch (transactionType.getValue()) {
		case 1: {
			if (amount < 0 || prevTransaction != null) {
				LOG.debug("Opening balance cannot be less than zero.");
				break;
			}
			LOG.debug("Opening balance :" + amount);

			transaction.setDescription(transactionType.getDescription());
			transaction.setDate(date);
			transaction.setDeposits(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setWithdrawals(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setBalance(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP));

		}
			break;

		case 2: {
			if (prevTransaction == null || prevTransaction.getBalance() == null) {
				LOG.debug("Account not found");
				break;
			}
			if (!uncovered && prevTransaction.getBalance().doubleValue() < amount) {
				LOG.debug("Insufficient BalanceFakeGen.");
				break;
			}
			LOG.debug("Balance before transaction:" + prevTransaction.getBalance().doubleValue());

			transaction.setDate(date);
			transaction.setDescription(categoriesType.getName());
			transaction.setDeposits(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setWithdrawals(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setBalance(new BigDecimal(prevTransaction.getBalance().doubleValue() - amount).setScale(2, BigDecimal.ROUND_HALF_UP));

			LOG.debug("BalanceFakeGen after transaction:" + prevTransaction.getBalance().toString());
		}
			break;
		case 3: {

			if (prevTransaction == null || prevTransaction.getBalance() == null) {
				LOG.debug("Account not found");
				break;
			}
			LOG.debug("Balance before transaction:" + prevTransaction.getBalance().doubleValue());

			transaction.setDescription(transactionType.getDescription());
			transaction.setDate(date);
			transaction.setDeposits(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setWithdrawals(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setBalance(new BigDecimal(prevTransaction.getBalance().doubleValue() + amount).setScale(2, BigDecimal.ROUND_HALF_UP));

			LOG.debug("BalanceFakeGen after transaction:" + prevTransaction.getBalance().toString());

		}
			break;
			
		case 4: {

			if (prevTransaction == null || prevTransaction.getBalance() == null) {
				LOG.debug("Account not found");
				break;
			}
			LOG.debug("Balance before transaction:" + prevTransaction.getBalance().doubleValue());

			transaction.setDescription(transactionType.getDescription());
			transaction.setDate(date);
			transaction.setDeposits(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setWithdrawals(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
			transaction.setBalance(new BigDecimal(prevTransaction.getBalance().doubleValue() + amount).setScale(2, BigDecimal.ROUND_HALF_UP));

			LOG.debug("BalanceFakeGen after transaction:" + prevTransaction.getBalance().toString());

		}
			break;

		default:
			LOG.debug("Invalid option");
			return null;
		}

		return transaction;

	}
	
    public static void shuffleList(List<CategoriesType> a) {
        int n = a.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }
    
    private static void swap(List<CategoriesType> a, int i, int change) {
    	CategoriesType helper = a.get(i);
        a.set(i, a.get(change));
        a.set(change, helper);
    }
    

	public List<String> buildCSVValues(CustomerProfile customerProfile, TransactionFakeModel tx) {
		List<String> line = new ArrayList<>();
		// bvn,account_number,date,description,deposits,withdrawals,balance
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		line.add(String.valueOf(customerProfile.getBnv()));
		line.add(String.valueOf(customerProfile.getAccountNumber()));
		line.add(simpleDateFormat.format(tx.getDate()));
		line.add(tx.getDescription());
		line.add(tx.getDeposits().toPlainString());
		line.add(tx.getWithdrawals().toPlainString());
		line.add(tx.getBalance().toPlainString());

		return line;
	}
}