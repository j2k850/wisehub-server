package com.wisehub.platform.data.producer.fake;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.wisehub.platform.data.producer.fake.model.CategoriesType;
import com.wisehub.platform.data.producer.fake.model.TransactionFakeModel;
import com.wisehub.platform.data.producer.fake.model.TransactionType;
import com.wisehub.platform.data.producer.fake.profile.CustomerProfile;
import com.wisehub.platform.data.producer.fake.profile.RestaurantCustomerProfile;

@Profile(value="restaurant")
@Component
public class RestaurantCustomerFakeProducer extends AbstractFakeProducer implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantCustomerFakeProducer.class);

	@Autowired
	RestaurantCustomerProfile restaurantCustomerProfile;


	@Override
	public CustomerProfile getCustomerProfile() {
		return restaurantCustomerProfile;
	}
	
	@Override
	public double getIncome() {
		return ThreadLocalRandom.current().nextDouble(restaurantCustomerProfile.getSaleMin(), restaurantCustomerProfile.getSaleMax() + 1);
	}



	public List<TransactionFakeModel> generate(LocalDate localDate, double openingBalance) {

		List<TransactionFakeModel> txs = new ArrayList<>();
		int openDays = 14;
		
		LocalDate end = localDate.plusMonths(1l);
		TransactionFakeModel transactionFakeGen = buildOperation(localDate, null, TransactionType.OPEN, null, openingBalance);
		txs.add(transactionFakeGen);
		int maxOperation = 1;
		
		List<CategoriesType> savingCostCategories1 = new ArrayList<CategoriesType>(restaurantCustomerProfile.getSavingCostCategories());
		List<CategoriesType> variableCostCategories1 = new ArrayList<CategoriesType>(restaurantCustomerProfile.getVariableCostCategories());
		List<CategoriesType> categories = new ArrayList<CategoriesType>();
		categories.addAll(savingCostCategories1);
		categories.addAll(variableCostCategories1);
		
		shuffleList(categories);
		
		Map<CategoriesType,Integer> times = new HashMap<>();
		
		List<TransactionFakeModel> buildFromFixedCost = buildFromFixedCost(true, localDate, restaurantCustomerProfile, transactionFakeGen);
		if (!buildFromFixedCost.isEmpty()){
			txs.addAll(buildFromFixedCost);
			transactionFakeGen = txs.get(txs.size() -1);
			localDate = transactionFakeGen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		
		int nextDay = ThreadLocalRandom.current().nextInt(2);
		localDate = localDate.plusDays(nextDay);
		
		while (end.isAfter(localDate) && maxOperation < 140) {

			if (openDays > 0) { //Open Days - So income by sale

				double sale = ThreadLocalRandom.current().nextDouble(restaurantCustomerProfile.getSaleMin(), restaurantCustomerProfile.getSaleMax() + 1);

				TransactionFakeModel tx = buildOperation(localDate, transactionFakeGen, TransactionType.INCOME_BY_DAY, null, sale);
				if (tx != null && tx.getBalance() != null && tx.getDate() != null) {
					txs.add(tx);
					transactionFakeGen = tx;
					maxOperation++;
					openDays--;
				}

			}
			
			if (openDays == 11 ||  openDays == 4 ){
				
				double amount = restaurantCustomerProfile.getEmployees() * ThreadLocalRandom.current().nextDouble(CategoriesType.SALARIES.getMin(), CategoriesType.SALARIES.getMin() + 10000);
				TransactionFakeModel tx  = buildOperation(true, localDate, transactionFakeGen, TransactionType.WITHDRAW, CategoriesType.SALARIES, amount);
				if (tx != null && tx.getBalance() != null && tx.getDate() != null) {
					txs.add(tx);
					transactionFakeGen = txs.get(txs.size() -1);
					localDate = transactionFakeGen.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				}
				
			}
			
			int expenses = categories.size() < 2 ? categories.size() : 2;
			for (int j = 0; j < expenses && !categories.isEmpty(); j++) {
				int index = ThreadLocalRandom.current().nextInt(categories.size());
				CategoriesType category = categories.get(index);

				if (category != null) {
					double amount = ThreadLocalRandom.current().nextDouble(category.getMin(), category.getMax());
					TransactionFakeModel tx  = buildOperation(true, localDate, transactionFakeGen, TransactionType.WITHDRAW, category, amount);
					if (tx != null && tx.getBalance() != null && tx.getDate() != null) {
						txs.add(tx);
						transactionFakeGen = tx;
						if (category.getType() == 3){
							categories.remove(index);
						}else if (!times.containsKey(category)){
							times.put(category, 1);
						}else if (times.get(category).intValue() < category.getTimes() ){
							Integer val = times.get(category);
							val++;
							times.put(category, val);
						}else{
							categories.remove(index);
						}
						maxOperation++;
					}
				}
			}

			nextDay = ThreadLocalRandom.current().nextInt(3);
			localDate = localDate.plusDays(nextDay);

		}

		return txs;

	}


}